package es.uvigo.dagss.recetas.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import es.uvigo.dagss.recetas.entidades.CentroSalud;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CentroSaludDao extends JpaRepository<CentroSalud, Long> {
    @Query("SELECT c FROM CentroSalud c WHERE(:term is null or LOWER(c.name) LIKE LOWER(CONCAT('%', :term, '%'))) AND (:term2 is null or LOWER(c.address.provincia) LIKE LOWER(CONCAT('%', :term, '%')))")
    List<CentroSalud> findByNameAndAddressProvincia(@Param("term") String term, @Param("term2") String term2);
    Optional<CentroSalud> findCentroSaludByName(String nombre);
    @Query("SELECT c FROM CentroSalud c WHERE(:term is null or LOWER(c.name) LIKE LOWER(CONCAT('%', :term, '%'))) AND (:term2 is null or LOWER(c.address.localidad) LIKE LOWER(CONCAT('%', :term, '%')))")
    List<CentroSalud> findByNameAndAddressLocalidad(@Param("term") String term, @Param("term2") String term2);
}
