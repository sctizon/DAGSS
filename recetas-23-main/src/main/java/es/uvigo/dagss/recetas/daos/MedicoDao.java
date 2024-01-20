package es.uvigo.dagss.recetas.daos;

import es.uvigo.dagss.recetas.entidades.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoDao extends JpaRepository<Medico, Long> {
    
}
