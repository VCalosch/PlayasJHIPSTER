package esto.es.java.repository;

import esto.es.java.domain.Situaciones_riesgos;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Situaciones_riesgos entity.
 */
@SuppressWarnings("unused")
@Repository
public interface Situaciones_riesgosRepository extends JpaRepository<Situaciones_riesgos, Long> {

}
