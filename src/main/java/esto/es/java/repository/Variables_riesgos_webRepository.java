package esto.es.java.repository;

import esto.es.java.domain.Variables_riesgos_web;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Variables_riesgos_web entity.
 */
@SuppressWarnings("unused")
@Repository
public interface Variables_riesgos_webRepository extends JpaRepository<Variables_riesgos_web, Long> {

}
