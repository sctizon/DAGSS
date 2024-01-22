package es.uvigo.dagss.recetas.services;

import es.uvigo.dagss.recetas.entidades.Medicina;
import es.uvigo.dagss.recetas.daos.MedicinaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicinaService {

    @Autowired
    private MedicinaDao medicinaDao;

    public List<Medicina> getAll() {
        return medicinaDao.findAll();
    }

    public Optional<Medicina> getById(long id) {
        return medicinaDao.findById(id);
    }

    public Medicina create(Medicina medicina) {
        return medicinaDao.save(medicina);
    }

    public Medicina update(Medicina medicina) {
        return medicinaDao.save(medicina);
    }

    public void delete(long id) {
        medicinaDao.deleteById(id);
    }
}

