package esto.es.java.web.rest;

import esto.es.java.PlayasApp;

import esto.es.java.domain.Riesgos;
import esto.es.java.repository.RiesgosRepository;
import esto.es.java.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static esto.es.java.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the RiesgosResource REST controller.
 *
 * @see RiesgosResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlayasApp.class)
public class RiesgosResourceIntTest {

    private static final String DEFAULT_DETALLE_RIESGO = "AAAAAAAAAA";
    private static final String UPDATED_DETALLE_RIESGO = "BBBBBBBBBB";

    @Autowired
    private RiesgosRepository riesgosRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restRiesgosMockMvc;

    private Riesgos riesgos;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RiesgosResource riesgosResource = new RiesgosResource(riesgosRepository);
        this.restRiesgosMockMvc = MockMvcBuilders.standaloneSetup(riesgosResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Riesgos createEntity(EntityManager em) {
        Riesgos riesgos = new Riesgos()
            .detalle_riesgo(DEFAULT_DETALLE_RIESGO);
        return riesgos;
    }

    @Before
    public void initTest() {
        riesgos = createEntity(em);
    }

    @Test
    @Transactional
    public void createRiesgos() throws Exception {
        int databaseSizeBeforeCreate = riesgosRepository.findAll().size();

        // Create the Riesgos
        restRiesgosMockMvc.perform(post("/api/riesgos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(riesgos)))
            .andExpect(status().isCreated());

        // Validate the Riesgos in the database
        List<Riesgos> riesgosList = riesgosRepository.findAll();
        assertThat(riesgosList).hasSize(databaseSizeBeforeCreate + 1);
        Riesgos testRiesgos = riesgosList.get(riesgosList.size() - 1);
        assertThat(testRiesgos.getDetalle_riesgo()).isEqualTo(DEFAULT_DETALLE_RIESGO);
    }

    @Test
    @Transactional
    public void createRiesgosWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = riesgosRepository.findAll().size();

        // Create the Riesgos with an existing ID
        riesgos.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRiesgosMockMvc.perform(post("/api/riesgos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(riesgos)))
            .andExpect(status().isBadRequest());

        // Validate the Riesgos in the database
        List<Riesgos> riesgosList = riesgosRepository.findAll();
        assertThat(riesgosList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllRiesgos() throws Exception {
        // Initialize the database
        riesgosRepository.saveAndFlush(riesgos);

        // Get all the riesgosList
        restRiesgosMockMvc.perform(get("/api/riesgos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(riesgos.getId().intValue())))
            .andExpect(jsonPath("$.[*].detalle_riesgo").value(hasItem(DEFAULT_DETALLE_RIESGO.toString())));
    }

    @Test
    @Transactional
    public void getRiesgos() throws Exception {
        // Initialize the database
        riesgosRepository.saveAndFlush(riesgos);

        // Get the riesgos
        restRiesgosMockMvc.perform(get("/api/riesgos/{id}", riesgos.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(riesgos.getId().intValue()))
            .andExpect(jsonPath("$.detalle_riesgo").value(DEFAULT_DETALLE_RIESGO.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingRiesgos() throws Exception {
        // Get the riesgos
        restRiesgosMockMvc.perform(get("/api/riesgos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRiesgos() throws Exception {
        // Initialize the database
        riesgosRepository.saveAndFlush(riesgos);
        int databaseSizeBeforeUpdate = riesgosRepository.findAll().size();

        // Update the riesgos
        Riesgos updatedRiesgos = riesgosRepository.findOne(riesgos.getId());
        // Disconnect from session so that the updates on updatedRiesgos are not directly saved in db
        em.detach(updatedRiesgos);
        updatedRiesgos
            .detalle_riesgo(UPDATED_DETALLE_RIESGO);

        restRiesgosMockMvc.perform(put("/api/riesgos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedRiesgos)))
            .andExpect(status().isOk());

        // Validate the Riesgos in the database
        List<Riesgos> riesgosList = riesgosRepository.findAll();
        assertThat(riesgosList).hasSize(databaseSizeBeforeUpdate);
        Riesgos testRiesgos = riesgosList.get(riesgosList.size() - 1);
        assertThat(testRiesgos.getDetalle_riesgo()).isEqualTo(UPDATED_DETALLE_RIESGO);
    }

    @Test
    @Transactional
    public void updateNonExistingRiesgos() throws Exception {
        int databaseSizeBeforeUpdate = riesgosRepository.findAll().size();

        // Create the Riesgos

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restRiesgosMockMvc.perform(put("/api/riesgos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(riesgos)))
            .andExpect(status().isCreated());

        // Validate the Riesgos in the database
        List<Riesgos> riesgosList = riesgosRepository.findAll();
        assertThat(riesgosList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteRiesgos() throws Exception {
        // Initialize the database
        riesgosRepository.saveAndFlush(riesgos);
        int databaseSizeBeforeDelete = riesgosRepository.findAll().size();

        // Get the riesgos
        restRiesgosMockMvc.perform(delete("/api/riesgos/{id}", riesgos.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Riesgos> riesgosList = riesgosRepository.findAll();
        assertThat(riesgosList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Riesgos.class);
        Riesgos riesgos1 = new Riesgos();
        riesgos1.setId(1L);
        Riesgos riesgos2 = new Riesgos();
        riesgos2.setId(riesgos1.getId());
        assertThat(riesgos1).isEqualTo(riesgos2);
        riesgos2.setId(2L);
        assertThat(riesgos1).isNotEqualTo(riesgos2);
        riesgos1.setId(null);
        assertThat(riesgos1).isNotEqualTo(riesgos2);
    }
}
