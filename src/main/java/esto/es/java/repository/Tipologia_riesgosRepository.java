package esto.es.java.repository;

import esto.es.java.domain.Tipologia_riesgos;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Tipologia_riesgos entity.
 */
@SuppressWarnings("unused")
@Repository
public interface Tipologia_riesgosRepository extends JpaRepository<Tipologia_riesgos, Long> {

}
