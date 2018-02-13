package esto.es.java.web.rest;

import com.codahale.metrics.annotation.Timed;
import esto.es.java.domain.Situaciones_riesgos;

import esto.es.java.repository.Situaciones_riesgosRepository;
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
 * REST controller for managing Situaciones_riesgos.
 */
@RestController
@RequestMapping("/api")
public class Situaciones_riesgosResource {

    private final Logger log = LoggerFactory.getLogger(Situaciones_riesgosResource.class);

    private static final String ENTITY_NAME = "situaciones_riesgos";

    private final Situaciones_riesgosRepository situaciones_riesgosRepository;

    public Situaciones_riesgosResource(Situaciones_riesgosRepository situaciones_riesgosRepository) {
        this.situaciones_riesgosRepository = situaciones_riesgosRepository;
    }

    /**
     * POST  /situaciones-riesgos : Create a new situaciones_riesgos.
     *
     * @param situaciones_riesgos the situaciones_riesgos to create
     * @return the ResponseEntity with status 201 (Created) and with body the new situaciones_riesgos, or with status 400 (Bad Request) if the situaciones_riesgos has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/situaciones-riesgos")
    @Timed
    public ResponseEntity<Situaciones_riesgos> createSituaciones_riesgos(@RequestBody Situaciones_riesgos situaciones_riesgos) throws URISyntaxException {
        log.debug("REST request to save Situaciones_riesgos : {}", situaciones_riesgos);
        if (situaciones_riesgos.getId() != null) {
            throw new BadRequestAlertException("A new situaciones_riesgos cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Situaciones_riesgos result = situaciones_riesgosRepository.save(situaciones_riesgos);
        return ResponseEntity.created(new URI("/api/situaciones-riesgos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /situaciones-riesgos : Updates an existing situaciones_riesgos.
     *
     * @param situaciones_riesgos the situaciones_riesgos to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated situaciones_riesgos,
     * or with status 400 (Bad Request) if the situaciones_riesgos is not valid,
     * or with status 500 (Internal Server Error) if the situaciones_riesgos couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/situaciones-riesgos")
    @Timed
    public ResponseEntity<Situaciones_riesgos> updateSituaciones_riesgos(@RequestBody Situaciones_riesgos situaciones_riesgos) throws URISyntaxException {
        log.debug("REST request to update Situaciones_riesgos : {}", situaciones_riesgos);
        if (situaciones_riesgos.getId() == null) {
            return createSituaciones_riesgos(situaciones_riesgos);
        }
        Situaciones_riesgos result = situaciones_riesgosRepository.save(situaciones_riesgos);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, situaciones_riesgos.getId().toString()))
            .body(result);
    }

    /**
     * GET  /situaciones-riesgos : get all the situaciones_riesgos.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of situaciones_riesgos in body
     */
    @GetMapping("/situaciones-riesgos")
    @Timed
    public List<Situaciones_riesgos> getAllSituaciones_riesgos() {
        log.debug("REST request to get all Situaciones_riesgos");
        return situaciones_riesgosRepository.findAll();
        }

    /**
     * GET  /situaciones-riesgos/:id : get the "id" situaciones_riesgos.
     *
     * @param id the id of the situaciones_riesgos to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the situaciones_riesgos, or with status 404 (Not Found)
     */
    @GetMapping("/situaciones-riesgos/{id}")
    @Timed
    public ResponseEntity<Situaciones_riesgos> getSituaciones_riesgos(@PathVariable Long id) {
        log.debug("REST request to get Situaciones_riesgos : {}", id);
        Situaciones_riesgos situaciones_riesgos = situaciones_riesgosRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(situaciones_riesgos));
    }

    /**
     * DELETE  /situaciones-riesgos/:id : delete the "id" situaciones_riesgos.
     *
     * @param id the id of the situaciones_riesgos to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/situaciones-riesgos/{id}")
    @Timed
    public ResponseEntity<Void> deleteSituaciones_riesgos(@PathVariable Long id) {
        log.debug("REST request to delete Situaciones_riesgos : {}", id);
        situaciones_riesgosRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
