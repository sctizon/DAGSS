package es.uvigo.dagss.recetas.daos;

import es.uvigo.dagss.recetas.entidades.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteDao extends JpaRepository<Paciente, Long> {
    
}
