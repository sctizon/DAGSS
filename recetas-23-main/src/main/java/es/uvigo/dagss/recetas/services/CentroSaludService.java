package es.uvigo.dagss.recetas.services;

import es.uvigo.dagss.recetas.daos.CentroSaludDao;
import es.uvigo.dagss.recetas.entidades.CentroSalud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CentroSaludService {

    @Autowired
    private CentroSaludDao centroSaludDao;

    public List<CentroSalud> getAll() {
        return centroSaludDao.findAll();
    }

    public Optional<CentroSalud> getById(long id) {
        return centroSaludDao.findById(id);
    }

    public CentroSalud create(CentroSalud centroSalud) {
        return centroSaludDao.save(centroSalud);
    }

    public CentroSalud update(CentroSalud centroSalud) {
        return centroSaludDao.save(centroSalud);
    }

    public void delete(long id) {
        centroSaludDao.deleteById(id);
    }
}

