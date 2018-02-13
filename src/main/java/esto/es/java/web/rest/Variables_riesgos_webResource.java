package esto.es.java.web.rest;

import com.codahale.metrics.annotation.Timed;
import esto.es.java.domain.Variables_riesgos_web;

import esto.es.java.repository.Variables_riesgos_webRepository;
import esto.es.java.web.rest.errors.BadRequestAlertException;
import esto.es.java.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Variables_riesgos_web.
 */
@RestController
@RequestMapping("/api")
public class Variables_riesgos_webResource {

    private final Logger log = LoggerFactory.getLogger(Variables_riesgos_webResource.class);

    private static final String ENTITY_NAME = "variables_riesgos_web";

    private final Variables_riesgos_webRepository variables_riesgos_webRepository;

    public Variables_riesgos_webResource(Variables_riesgos_webRepository variables_riesgos_webRepository) {
        this.variables_riesgos_webRepository = variables_riesgos_webRepository;
    }

    /**
     * POST  /variables-riesgos-webs : Create a new variables_riesgos_web.
     *
     * @param variables_riesgos_web the variables_riesgos_web to create
     * @return the ResponseEntity with status 201 (Created) and with body the new variables_riesgos_web, or with status 400 (Bad Request) if the variables_riesgos_web has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/variables-riesgos-webs")
    @Timed
    public ResponseEntity<Variables_riesgos_web> createVariables_riesgos_web(@RequestBody Variables_riesgos_web variables_riesgos_web) throws URISyntaxException {
        log.debug("REST request to save Variables_riesgos_web : {}", variables_riesgos_web);
        if (variables_riesgos_web.getId() != null) {
            throw new BadRequestAlertException("A new variables_riesgos_web cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Variables_riesgos_web result = variables_riesgos_webRepository.save(variables_riesgos_web);
        return ResponseEntity.created(new URI("/api/variables-riesgos-webs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /variables-riesgos-webs : Updates an existing variables_riesgos_web.
     *
     * @param variables_riesgos_web the variables_riesgos_web to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated variables_riesgos_web,
     * or with status 400 (Bad Request) if the variables_riesgos_web is not valid,
     * or with status 500 (Internal Server Error) if the variables_riesgos_web couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/variables-riesgos-webs")
    @Timed
    public ResponseEntity<Variables_riesgos_web> updateVariables_riesgos_web(@RequestBody Variables_riesgos_web variables_riesgos_web) throws URISyntaxException {
        log.debug("REST request to update Variables_riesgos_web : {}", variables_riesgos_web);
        if (variables_riesgos_web.getId() == null) {
            return createVariables_riesgos_web(variables_riesgos_web);
        }
        Variables_riesgos_web result = variables_riesgos_webRepository.save(variables_riesgos_web);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, variables_riesgos_web.getId().toString()))
            .body(result);
    }

    /**
     * GET  /variables-riesgos-webs : get all the variables_riesgos_webs.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of variables_riesgos_webs in body
     */
    @GetMapping("/variables-riesgos-webs")
    @Timed
    public List<Variables_riesgos_web> getAllVariables_riesgos_webs() {
        log.debug("REST request to get all Variables_riesgos_webs");
        return variables_riesgos_webRepository.findAll();
        }

    /**
     * GET  /variables-riesgos-webs/:id : get the "id" variables_riesgos_web.
     *
     * @param id the id of the variables_riesgos_web to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the variables_riesgos_web, or with status 404 (Not Found)
     */
    @GetMapping("/variables-riesgos-webs/{id}")
    @Timed
    public ResponseEntity<Variables_riesgos_web> getVariables_riesgos_web(@PathVariable Long id) {
        log.debug("REST request to get Variables_riesgos_web : {}", id);
        Variables_riesgos_web variables_riesgos_web = variables_riesgos_webRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(variables_riesgos_web));
    }

    /**
     * DELETE  /variables-riesgos-webs/:id : delete the "id" variables_riesgos_web.
     *
     * @param id the id of the variables_riesgos_web to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/variables-riesgos-webs/{id}")
    @Timed
    public ResponseEntity<Void> deleteVariables_riesgos_web(@PathVariable Long id) {
        log.debug("REST request to delete Variables_riesgos_web : {}", id);
        variables_riesgos_webRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
