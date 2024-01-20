package es.uvigo.dagss.recetas.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import es.uvigo.dagss.recetas.entidades.Farmacia;

public interface FarmaciaDao extends JpaRepository<Farmacia, Long> {
    
}
