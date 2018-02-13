package esto.es.java.web.rest;

import com.codahale.metrics.annotation.Timed;
import esto.es.java.domain.Tipologia_riesgos;

import esto.es.java.repository.Tipologia_riesgosRepository;
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
 * REST controller for managing Tipologia_riesgos.
 */
@RestController
@RequestMapping("/api")
public class Tipologia_riesgosResource {

    private final Logger log = LoggerFactory.getLogger(Tipologia_riesgosResource.class);

    private static final String ENTITY_NAME = "tipologia_riesgos";

    private final Tipologia_riesgosRepository tipologia_riesgosRepository;

    public Tipologia_riesgosResource(Tipologia_riesgosRepository tipologia_riesgosRepository) {
        this.tipologia_riesgosRepository = tipologia_riesgosRepository;
    }

    /**
     * POST  /tipologia-riesgos : Create a new tipologia_riesgos.
     *
     * @param tipologia_riesgos the tipologia_riesgos to create
     * @return the ResponseEntity with status 201 (Created) and with body the new tipologia_riesgos, or with status 400 (Bad Request) if the tipologia_riesgos has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/tipologia-riesgos")
    @Timed
    public ResponseEntity<Tipologia_riesgos> createTipologia_riesgos(@RequestBody Tipologia_riesgos tipologia_riesgos) throws URISyntaxException {
        log.debug("REST request to save Tipologia_riesgos : {}", tipologia_riesgos);
        if (tipologia_riesgos.getId() != null) {
            throw new BadRequestAlertException("A new tipologia_riesgos cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Tipologia_riesgos result = tipologia_riesgosRepository.save(tipologia_riesgos);
        return ResponseEntity.created(new URI("/api/tipologia-riesgos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /tipologia-riesgos : Updates an existing tipologia_riesgos.
     *
     * @param tipologia_riesgos the tipologia_riesgos to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated tipologia_riesgos,
     * or with status 400 (Bad Request) if the tipologia_riesgos is not valid,
     * or with status 500 (Internal Server Error) if the tipologia_riesgos couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/tipologia-riesgos")
    @Timed
    public ResponseEntity<Tipologia_riesgos> updateTipologia_riesgos(@RequestBody Tipologia_riesgos tipologia_riesgos) throws URISyntaxException {
        log.debug("REST request to update Tipologia_riesgos : {}", tipologia_riesgos);
        if (tipologia_riesgos.getId() == null) {
            return createTipologia_riesgos(tipologia_riesgos);
        }
        Tipologia_riesgos result = tipologia_riesgosRepository.save(tipologia_riesgos);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, tipologia_riesgos.getId().toString()))
            .body(result);
    }

    /**
     * GET  /tipologia-riesgos : get all the tipologia_riesgos.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of tipologia_riesgos in body
     */
    @GetMapping("/tipologia-riesgos")
    @Timed
    public List<Tipologia_riesgos> getAllTipologia_riesgos() {
        log.debug("REST request to get all Tipologia_riesgos");
        return tipologia_riesgosRepository.findAll();
        }

    /**
     * GET  /tipologia-riesgos/:id : get the "id" tipologia_riesgos.
     *
     * @param id the id of the tipologia_riesgos to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the tipologia_riesgos, or with status 404 (Not Found)
     */
    @GetMapping("/tipologia-riesgos/{id}")
    @Timed
    public ResponseEntity<Tipologia_riesgos> getTipologia_riesgos(@PathVariable Long id) {
        log.debug("REST request to get Tipologia_riesgos : {}", id);
        Tipologia_riesgos tipologia_riesgos = tipologia_riesgosRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(tipologia_riesgos));
    }

    /**
     * DELETE  /tipologia-riesgos/:id : delete the "id" tipologia_riesgos.
     *
     * @param id the id of the tipologia_riesgos to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/tipologia-riesgos/{id}")
    @Timed
    public ResponseEntity<Void> deleteTipologia_riesgos(@PathVariable Long id) {
        log.debug("REST request to delete Tipologia_riesgos : {}", id);
        tipologia_riesgosRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
