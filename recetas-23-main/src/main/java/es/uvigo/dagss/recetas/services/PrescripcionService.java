package es.uvigo.dagss.recetas.services;

import es.uvigo.dagss.recetas.entidades.Prescripcion;
import es.uvigo.dagss.recetas.daos.PrescripcionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrescripcionService {

    @Autowired
    private PrescripcionDao prescripcionDao;

    public List<Prescripcion> getAll() {
        return prescripcionDao.findAll();
    }

    public Optional<Prescripcion> getById(long id) {
        return prescripcionDao.findById(id);
    }

    public Prescripcion create(Prescripcion prescripcion) {
        return prescripcionDao.save(prescripcion);
    }

    public Prescripcion update(Prescripcion prescripcion) {
        return prescripcionDao.save(prescripcion);
    }

    public void delete(long id) {
        prescripcionDao.deleteById(id);
    }
}

