package es.uvigo.dagss.recetas.services;

import es.uvigo.dagss.recetas.entidades.Medico;
import es.uvigo.dagss.recetas.repositories.MedicoRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicoService {
    
    @Autowired
    private MedicoRepository medicoRepository;

    public List<Medico> getAll() {
        return medicoRepository.findAll().stream()
                .filter(usuario -> usuario instanceof Medico) // Filtra solo Administradores
                .filter(usuario -> ((Medico) usuario).getActivo()) // Filtra solo usuarios activos
                .map(usuario -> (Medico) usuario) // Convierte de Usuario a Administrador
                .toList();
    }

    public Optional<Medico> findById(Long id) {
        Optional<Medico> medico = medicoRepository.findById(id);

        if(medico.isPresent() && !medico.get().getActivo()){
            return Optional.empty();
        }
        return medico;
    }

    public Medico create(Medico medico) {
        return medicoRepository.save(medico);
    }

    public Medico update(Medico medico) {
        return medicoRepository.save(medico);

    }

    public void delete(Medico medico) {
        medico.desactivar();
        medicoRepository.save(medico);
    }

}
