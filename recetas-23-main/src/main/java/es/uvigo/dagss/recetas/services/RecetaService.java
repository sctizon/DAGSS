package es.uvigo.dagss.recetas.services;

import es.uvigo.dagss.recetas.entidades.Receta;
import es.uvigo.dagss.recetas.daos.RecetaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecetaService {

    @Autowired
    private RecetaDao recetaDao;

    public List<Receta> getAll() {
        return recetaDao.findAll();
    }

    public Optional<Receta> getById(long id) {
        return recetaDao.findById(id);
    }

    public Receta create(Receta receta) {
        return recetaDao.save(receta);
    }

    public Receta update(Receta receta) {
        return recetaDao.save(receta);
    }

    public void delete(long id) {
        recetaDao.deleteById(id);
    }
}

