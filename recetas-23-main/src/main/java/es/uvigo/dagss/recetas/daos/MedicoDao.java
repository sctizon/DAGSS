package es.uvigo.dagss.recetas.daos;

import es.uvigo.dagss.recetas.entidades.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MedicoDao extends JpaRepository<Medico, Long> {
    @Query("SELECT m FROM Medico m WHERE(:term is null or LOWER(m.name) LIKE LOWER(CONCAT('%', :term, '%'))) AND (:term2 is null or LOWER(m.center.address.provincia) LIKE LOWER(CONCAT('%', :term, '%')))")
    List<Medico> findByNameAndCentroSaludProvincia(@Param("term") String term, @Param("term2") String term2);
}
