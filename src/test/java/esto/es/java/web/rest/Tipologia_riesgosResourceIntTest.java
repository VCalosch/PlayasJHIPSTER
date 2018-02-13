package esto.es.java.web.rest;

import esto.es.java.PlayasApp;

import esto.es.java.domain.Tipologia_riesgos;
import esto.es.java.repository.Tipologia_riesgosRepository;
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
 * Test class for the Tipologia_riesgosResource REST controller.
 *
 * @see Tipologia_riesgosResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlayasApp.class)
public class Tipologia_riesgosResourceIntTest {

    private static final String DEFAULT_DETALLE_TOPOLOGIA = "AAAAAAAAAA";
    private static final String UPDATED_DETALLE_TOPOLOGIA = "BBBBBBBBBB";

    private static final Integer DEFAULT_ID_RIESGO = 1;
    private static final Integer UPDATED_ID_RIESGO = 2;

    @Autowired
    private Tipologia_riesgosRepository tipologia_riesgosRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restTipologia_riesgosMockMvc;

    private Tipologia_riesgos tipologia_riesgos;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final Tipologia_riesgosResource tipologia_riesgosResource = new Tipologia_riesgosResource(tipologia_riesgosRepository);
        this.restTipologia_riesgosMockMvc = MockMvcBuilders.standaloneSetup(tipologia_riesgosResource)
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
    public static Tipologia_riesgos createEntity(EntityManager em) {
        Tipologia_riesgos tipologia_riesgos = new Tipologia_riesgos()
            .detalle_topologia(DEFAULT_DETALLE_TOPOLOGIA)
            .id_Riesgo(DEFAULT_ID_RIESGO);
        return tipologia_riesgos;
    }

    @Before
    public void initTest() {
        tipologia_riesgos = createEntity(em);
    }

    @Test
    @Transactional
    public void createTipologia_riesgos() throws Exception {
        int databaseSizeBeforeCreate = tipologia_riesgosRepository.findAll().size();

        // Create the Tipologia_riesgos
        restTipologia_riesgosMockMvc.perform(post("/api/tipologia-riesgos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipologia_riesgos)))
            .andExpect(status().isCreated());

        // Validate the Tipologia_riesgos in the database
        List<Tipologia_riesgos> tipologia_riesgosList = tipologia_riesgosRepository.findAll();
        assertThat(tipologia_riesgosList).hasSize(databaseSizeBeforeCreate + 1);
        Tipologia_riesgos testTipologia_riesgos = tipologia_riesgosList.get(tipologia_riesgosList.size() - 1);
        assertThat(testTipologia_riesgos.getDetalle_topologia()).isEqualTo(DEFAULT_DETALLE_TOPOLOGIA);
        assertThat(testTipologia_riesgos.getId_Riesgo()).isEqualTo(DEFAULT_ID_RIESGO);
    }

    @Test
    @Transactional
    public void createTipologia_riesgosWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tipologia_riesgosRepository.findAll().size();

        // Create the Tipologia_riesgos with an existing ID
        tipologia_riesgos.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTipologia_riesgosMockMvc.perform(post("/api/tipologia-riesgos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipologia_riesgos)))
            .andExpect(status().isBadRequest());

        // Validate the Tipologia_riesgos in the database
        List<Tipologia_riesgos> tipologia_riesgosList = tipologia_riesgosRepository.findAll();
        assertThat(tipologia_riesgosList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllTipologia_riesgos() throws Exception {
        // Initialize the database
        tipologia_riesgosRepository.saveAndFlush(tipologia_riesgos);

        // Get all the tipologia_riesgosList
        restTipologia_riesgosMockMvc.perform(get("/api/tipologia-riesgos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tipologia_riesgos.getId().intValue())))
            .andExpect(jsonPath("$.[*].detalle_topologia").value(hasItem(DEFAULT_DETALLE_TOPOLOGIA.toString())))
            .andExpect(jsonPath("$.[*].id_Riesgo").value(hasItem(DEFAULT_ID_RIESGO)));
    }

    @Test
    @Transactional
    public void getTipologia_riesgos() throws Exception {
        // Initialize the database
        tipologia_riesgosRepository.saveAndFlush(tipologia_riesgos);

        // Get the tipologia_riesgos
        restTipologia_riesgosMockMvc.perform(get("/api/tipologia-riesgos/{id}", tipologia_riesgos.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(tipologia_riesgos.getId().intValue()))
            .andExpect(jsonPath("$.detalle_topologia").value(DEFAULT_DETALLE_TOPOLOGIA.toString()))
            .andExpect(jsonPath("$.id_Riesgo").value(DEFAULT_ID_RIESGO));
    }

    @Test
    @Transactional
    public void getNonExistingTipologia_riesgos() throws Exception {
        // Get the tipologia_riesgos
        restTipologia_riesgosMockMvc.perform(get("/api/tipologia-riesgos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTipologia_riesgos() throws Exception {
        // Initialize the database
        tipologia_riesgosRepository.saveAndFlush(tipologia_riesgos);
        int databaseSizeBeforeUpdate = tipologia_riesgosRepository.findAll().size();

        // Update the tipologia_riesgos
        Tipologia_riesgos updatedTipologia_riesgos = tipologia_riesgosRepository.findOne(tipologia_riesgos.getId());
        // Disconnect from session so that the updates on updatedTipologia_riesgos are not directly saved in db
        em.detach(updatedTipologia_riesgos);
        updatedTipologia_riesgos
            .detalle_topologia(UPDATED_DETALLE_TOPOLOGIA)
            .id_Riesgo(UPDATED_ID_RIESGO);

        restTipologia_riesgosMockMvc.perform(put("/api/tipologia-riesgos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedTipologia_riesgos)))
            .andExpect(status().isOk());

        // Validate the Tipologia_riesgos in the database
        List<Tipologia_riesgos> tipologia_riesgosList = tipologia_riesgosRepository.findAll();
        assertThat(tipologia_riesgosList).hasSize(databaseSizeBeforeUpdate);
        Tipologia_riesgos testTipologia_riesgos = tipologia_riesgosList.get(tipologia_riesgosList.size() - 1);
        assertThat(testTipologia_riesgos.getDetalle_topologia()).isEqualTo(UPDATED_DETALLE_TOPOLOGIA);
        assertThat(testTipologia_riesgos.getId_Riesgo()).isEqualTo(UPDATED_ID_RIESGO);
    }

    @Test
    @Transactional
    public void updateNonExistingTipologia_riesgos() throws Exception {
        int databaseSizeBeforeUpdate = tipologia_riesgosRepository.findAll().size();

        // Create the Tipologia_riesgos

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restTipologia_riesgosMockMvc.perform(put("/api/tipologia-riesgos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipologia_riesgos)))
            .andExpect(status().isCreated());

        // Validate the Tipologia_riesgos in the database
        List<Tipologia_riesgos> tipologia_riesgosList = tipologia_riesgosRepository.findAll();
        assertThat(tipologia_riesgosList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteTipologia_riesgos() throws Exception {
        // Initialize the database
        tipologia_riesgosRepository.saveAndFlush(tipologia_riesgos);
        int databaseSizeBeforeDelete = tipologia_riesgosRepository.findAll().size();

        // Get the tipologia_riesgos
        restTipologia_riesgosMockMvc.perform(delete("/api/tipologia-riesgos/{id}", tipologia_riesgos.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Tipologia_riesgos> tipologia_riesgosList = tipologia_riesgosRepository.findAll();
        assertThat(tipologia_riesgosList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Tipologia_riesgos.class);
        Tipologia_riesgos tipologia_riesgos1 = new Tipologia_riesgos();
        tipologia_riesgos1.setId(1L);
        Tipologia_riesgos tipologia_riesgos2 = new Tipologia_riesgos();
        tipologia_riesgos2.setId(tipologia_riesgos1.getId());
        assertThat(tipologia_riesgos1).isEqualTo(tipologia_riesgos2);
        tipologia_riesgos2.setId(2L);
        assertThat(tipologia_riesgos1).isNotEqualTo(tipologia_riesgos2);
        tipologia_riesgos1.setId(null);
        assertThat(tipologia_riesgos1).isNotEqualTo(tipologia_riesgos2);
    }
}
