package es.uvigo.dagss.recetas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uvigo.dagss.recetas.daos.FarmaciaDao;
import es.uvigo.dagss.recetas.entidades.Farmacia;

@Service
public class FarmaciaService {
    
@Autowired
    private FarmaciaDao farmaciaDao;

    public List<Farmacia> getAll() {
        return farmaciaDao.findAll().stream()
                .filter(usuario -> usuario instanceof Farmacia) // Filtra solo Administradores
                .filter(usuario -> ((Farmacia) usuario).getActivo()) // Filtra solo usuarios activos
                .map(usuario -> (Farmacia) usuario) // Convierte de Usuario a Administrador
                .toList();
    }

    public Optional<Farmacia> findById(Long id) {
        Optional<Farmacia> farmacia = farmaciaDao.findById(id);

        if(farmacia.isPresent() && !farmacia.get().getActivo()){
            return Optional.empty();
        }
        return farmacia;
    }

    public Farmacia create(Farmacia farmacia) {
        return farmaciaDao.save(farmacia);
    }

    public Farmacia update(Farmacia farmacia) {
        return farmaciaDao.save(farmacia);

    }

    public void delete(Farmacia farmacia) {
        farmacia.desactivar();
        farmaciaDao.save(farmacia);
    }

}
