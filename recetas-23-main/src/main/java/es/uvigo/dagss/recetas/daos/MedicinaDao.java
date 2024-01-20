package es.uvigo.dagss.recetas.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import es.uvigo.dagss.recetas.entidades.Medicina;

public interface MedicinaDao extends JpaRepository<Medicina, Long> {
    
}
