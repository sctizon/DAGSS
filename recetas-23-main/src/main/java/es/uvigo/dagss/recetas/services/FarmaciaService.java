package es.uvigo.dagss.recetas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uvigo.dagss.recetas.entidades.Farmacia;
import es.uvigo.dagss.recetas.repositories.FarmaciaRepository;

@Service
public class FarmaciaService {
    
@Autowired
    private FarmaciaRepository farmaciaRepository;

    public List<Farmacia> getAll() {
        return farmaciaRepository.findAll().stream()
                .filter(usuario -> usuario instanceof Farmacia) // Filtra solo Administradores
                .filter(usuario -> ((Farmacia) usuario).getActivo()) // Filtra solo usuarios activos
                .map(usuario -> (Farmacia) usuario) // Convierte de Usuario a Administrador
                .toList();
    }

    public Optional<Farmacia> findById(Long id) {
        Optional<Farmacia> farmacia = farmaciaRepository.findById(id);

        if(farmacia.isPresent() && !farmacia.get().getActivo()){
            return Optional.empty();
        }
        return farmacia;
    }

    public Farmacia create(Farmacia farmacia) {
        return farmaciaRepository.save(farmacia);
    }

    public Farmacia update(Farmacia farmacia) {
        return farmaciaRepository.save(farmacia);

    }

    public void delete(Farmacia farmacia) {
        farmacia.desactivar();
        farmaciaRepository.save(farmacia);
    }

}
