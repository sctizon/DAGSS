package es.uvigo.dagss.recetas.daos;

import es.uvigo.dagss.recetas.entidades.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministradorDao extends JpaRepository<Administrador, Long> {
    
}
