package es.uvigo.dagss.recetas.services;

import es.uvigo.dagss.recetas.daos.CitaDao;
import es.uvigo.dagss.recetas.entidades.Cita;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CitaService {

    @Autowired
    private CitaDao citaDao;

    public List<Cita> getAll() {
        return citaDao.findAll();
    }

    public Optional<Cita> getById(long id) {
        return citaDao.findById(id);
    }

    public Cita create(Cita cita) {
        return citaDao.save(cita);
    }

    public Cita update(Cita cita) {
        return citaDao.save(cita);
    }

    public void delete(long id) {
        citaDao.deleteById(id);
    }
}

