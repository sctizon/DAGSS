package es.uvigo.dagss.recetas.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import es.uvigo.dagss.recetas.entidades.CentroSalud;

public interface CentroSaludDao extends JpaRepository<CentroSalud, Long> {
    
}
