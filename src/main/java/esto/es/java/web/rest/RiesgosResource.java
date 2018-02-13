package esto.es.java.web.rest;

import com.codahale.metrics.annotation.Timed;
import esto.es.java.domain.Riesgos;

import esto.es.java.repository.RiesgosRepository;
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
 * REST controller for managing Riesgos.
 */
@RestController
@RequestMapping("/api")
public class RiesgosResource {

    private final Logger log = LoggerFactory.getLogger(RiesgosResource.class);

    private static final String ENTITY_NAME = "riesgos";

    private final RiesgosRepository riesgosRepository;

    public RiesgosResource(RiesgosRepository riesgosRepository) {
        this.riesgosRepository = riesgosRepository;
    }

    /**
     * POST  /riesgos : Create a new riesgos.
     *
     * @param riesgos the riesgos to create
     * @return the ResponseEntity with status 201 (Created) and with body the new riesgos, or with status 400 (Bad Request) if the riesgos has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/riesgos")
    @Timed
    public ResponseEntity<Riesgos> createRiesgos(@RequestBody Riesgos riesgos) throws URISyntaxException {
        log.debug("REST request to save Riesgos : {}", riesgos);
        if (riesgos.getId() != null) {
            throw new BadRequestAlertException("A new riesgos cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Riesgos result = riesgosRepository.save(riesgos);
        return ResponseEntity.created(new URI("/api/riesgos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /riesgos : Updates an existing riesgos.
     *
     * @param riesgos the riesgos to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated riesgos,
     * or with status 400 (Bad Request) if the riesgos is not valid,
     * or with status 500 (Internal Server Error) if the riesgos couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/riesgos")
    @Timed
    public ResponseEntity<Riesgos> updateRiesgos(@RequestBody Riesgos riesgos) throws URISyntaxException {
        log.debug("REST request to update Riesgos : {}", riesgos);
        if (riesgos.getId() == null) {
            return createRiesgos(riesgos);
        }
        Riesgos result = riesgosRepository.save(riesgos);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, riesgos.getId().toString()))
            .body(result);
    }

    /**
     * GET  /riesgos : get all the riesgos.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of riesgos in body
     */
    @GetMapping("/riesgos")
    @Timed
    public List<Riesgos> getAllRiesgos() {
        log.debug("REST request to get all Riesgos");
        return riesgosRepository.findAll();
        }

    /**
     * GET  /riesgos/:id : get the "id" riesgos.
     *
     * @param id the id of the riesgos to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the riesgos, or with status 404 (Not Found)
     */
    @GetMapping("/riesgos/{id}")
    @Timed
    public ResponseEntity<Riesgos> getRiesgos(@PathVariable Long id) {
        log.debug("REST request to get Riesgos : {}", id);
        Riesgos riesgos = riesgosRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(riesgos));
    }

    /**
     * DELETE  /riesgos/:id : delete the "id" riesgos.
     *
     * @param id the id of the riesgos to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/riesgos/{id}")
    @Timed
    public ResponseEntity<Void> deleteRiesgos(@PathVariable Long id) {
        log.debug("REST request to delete Riesgos : {}", id);
        riesgosRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
