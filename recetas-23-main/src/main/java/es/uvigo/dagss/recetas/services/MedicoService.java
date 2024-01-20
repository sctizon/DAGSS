package es.uvigo.dagss.recetas.services;

import es.uvigo.dagss.recetas.daos.MedicoDao;
import es.uvigo.dagss.recetas.entidades.Medico;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicoService {
    
    @Autowired
    private MedicoDao medicoDao;

    public List<Medico> getAll() {
        return medicoDao.findAll().stream()
                .filter(usuario -> usuario instanceof Medico) // Filtra solo Administradores
                .filter(usuario -> ((Medico) usuario).getActivo()) // Filtra solo usuarios activos
                .map(usuario -> (Medico) usuario) // Convierte de Usuario a Administrador
                .toList();
    }

    public Optional<Medico> findById(Long id) {
        Optional<Medico> medico = medicoDao.findById(id);

        if(medico.isPresent() && !medico.get().getActivo()){
            return Optional.empty();
        }
        return medico;
    }

    public Medico create(Medico medico) {
        return medicoDao.save(medico);
    }

    public Medico update(Medico medico) {
        return medicoDao.save(medico);

    }

    public void delete(Medico medico) {
        medico.desactivar();
        medicoDao.save(medico);
    }

}
