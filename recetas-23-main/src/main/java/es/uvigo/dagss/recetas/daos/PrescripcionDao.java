package es.uvigo.dagss.recetas.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import es.uvigo.dagss.recetas.entidades.Prescripcion;

public interface PrescripcionDao extends JpaRepository<Prescripcion, Long> {
    
}
