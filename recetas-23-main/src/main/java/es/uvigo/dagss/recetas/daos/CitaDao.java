package es.uvigo.dagss.recetas.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import es.uvigo.dagss.recetas.entidades.Cita;

public interface CitaDao extends JpaRepository<Cita, Long> {
    
}
