package esto.es.java.repository;

import esto.es.java.domain.Riesgos;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Riesgos entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RiesgosRepository extends JpaRepository<Riesgos, Long> {

}
