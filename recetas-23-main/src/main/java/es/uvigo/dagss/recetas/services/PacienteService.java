package es.uvigo.dagss.recetas.services;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import es.uvigo.dagss.recetas.daos.CentroSaludDao;
import es.uvigo.dagss.recetas.daos.MedicoDao;
import es.uvigo.dagss.recetas.entidades.CentroSalud;
import es.uvigo.dagss.recetas.entidades.Medico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uvigo.dagss.recetas.daos.PacienteDao;
import es.uvigo.dagss.recetas.entidades.Paciente;

@Service
public class PacienteService {

    @Autowired
    private PacienteDao pacienteDao;
    private CentroSaludDao centroSaludDao;
    private MedicoDao medicoDao;

    public List<Paciente> getAll() {
        return pacienteDao.findAll().stream()
                .filter(usuario -> usuario instanceof Paciente) // Filtra solo Administradores
                .filter(usuario -> ((Paciente) usuario).getActivo()) // Filtra solo usuarios activos
                .map(usuario -> (Paciente) usuario) // Convierte de Usuario a Administrador
                .toList();
    }

    public Optional<Paciente> findById(Long id) {
        Optional<Paciente> paciente = pacienteDao.findById(id);
        if(paciente.isPresent() && !paciente.get().getActivo()){
            return Optional.empty();
        }
        return paciente;
    }

    public Paciente create(Paciente paciente) {
        List<Paciente> allPacientes = pacienteDao.findAll();
        if(allPacientes.contains(paciente)) {
            return null;
        }
        Random random = new Random();
        List<CentroSalud> selectedCentro = centroSaludDao.findByNameAndAddressProvincia(null, paciente.getAddress().getProvincia());
        if(!selectedCentro.isEmpty()) {
            CentroSalud centroSaludRandom = selectedCentro.get(random.nextInt(selectedCentro.size()));
            List<Medico> selectedMedico = medicoDao.findByNameAndCentroSaludProvincia(null, centroSaludRandom.getAddress().getProvincia());
            if(!selectedMedico.isEmpty()) {
                paciente.setRegisteredDoctor(selectedMedico.get(random.nextInt(selectedMedico.size())));
                paciente.setCenter(centroSaludRandom);
                return pacienteDao.save(paciente);
            }
        }
        return null;
    }

    public Paciente update(Paciente paciente) {
        return pacienteDao.save(paciente);
    }

    public void delete(Paciente paciente) {
        paciente.desactivar();
        pacienteDao.save(paciente);
    }
}
