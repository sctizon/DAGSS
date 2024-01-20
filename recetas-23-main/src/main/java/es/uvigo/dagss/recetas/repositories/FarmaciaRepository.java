package es.uvigo.dagss.recetas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import es.uvigo.dagss.recetas.entidades.Farmacia;

public interface FarmaciaRepository extends JpaRepository<Farmacia, Long> {
    
}
