package es.uvigo.dagss.recetas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uvigo.dagss.recetas.entidades.Paciente;
import es.uvigo.dagss.recetas.repositories.PacienteRepository;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    public List<Paciente> getAll() {
        return pacienteRepository.findAll().stream()
                .filter(usuario -> usuario instanceof Paciente) // Filtra solo Administradores
                .filter(usuario -> ((Paciente) usuario).getActivo()) // Filtra solo usuarios activos
                .map(usuario -> (Paciente) usuario) // Convierte de Usuario a Administrador
                .toList();
    }

    public Optional<Paciente> findById(Long id) {
        Optional<Paciente> paciente = pacienteRepository.findById(id);

        if(paciente.isPresent() && !paciente.get().getActivo()){
            return Optional.empty();
        }
        return paciente;
    }

    public Paciente create(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    public Paciente update(Paciente paciente) {
        return pacienteRepository.save(paciente);

    }

    public void delete(Paciente paciente) {
        paciente.desactivar();
        pacienteRepository.save(paciente);
    }
    
}
