package esto.es.java.web.rest;

import esto.es.java.PlayasApp;

import esto.es.java.domain.Situaciones_riesgos;
import esto.es.java.repository.Situaciones_riesgosRepository;
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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static esto.es.java.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the Situaciones_riesgosResource REST controller.
 *
 * @see Situaciones_riesgosResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlayasApp.class)
public class Situaciones_riesgosResourceIntTest {

    private static final Integer DEFAULT_ID_REGISTRO_RIESGO = 1;
    private static final Integer UPDATED_ID_REGISTRO_RIESGO = 2;

    private static final Integer DEFAULT_TIPO_VARIABLE = 1;
    private static final Integer UPDATED_TIPO_VARIABLE = 2;

    private static final String DEFAULT_DIRECCION = "AAAAAAAAAA";
    private static final String UPDATED_DIRECCION = "BBBBBBBBBB";

    private static final String DEFAULT_OLEAJE_TAMANO = "AAAAAAAAAA";
    private static final String UPDATED_OLEAJE_TAMANO = "BBBBBBBBBB";

    private static final String DEFAULT_VIENO_VELOCIDAD = "AAAAAAAAAA";
    private static final String UPDATED_VIENO_VELOCIDAD = "BBBBBBBBBB";

    private static final String DEFAULT_MAREA_ALTURA = "AAAAAAAAAA";
    private static final String UPDATED_MAREA_ALTURA = "BBBBBBBBBB";

    private static final Integer DEFAULT_MAREA_RANGO = 1;
    private static final Integer UPDATED_MAREA_RANGO = 2;

    private static final Integer DEFAULT_MAREA_MOMENTO = 1;
    private static final Integer UPDATED_MAREA_MOMENTO = 2;

    private static final String DEFAULT_PROBABILIDAD = "AAAAAAAAAA";
    private static final String UPDATED_PROBABILIDAD = "BBBBBBBBBB";

    private static final String DEFAULT_SEVERIDAD = "AAAAAAAAAA";
    private static final String UPDATED_SEVERIDAD = "BBBBBBBBBB";

    private static final String DEFAULT_EVALUACION = "AAAAAAAAAA";
    private static final String UPDATED_EVALUACION = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_FECHA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FECHA = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private Situaciones_riesgosRepository situaciones_riesgosRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restSituaciones_riesgosMockMvc;

    private Situaciones_riesgos situaciones_riesgos;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final Situaciones_riesgosResource situaciones_riesgosResource = new Situaciones_riesgosResource(situaciones_riesgosRepository);
        this.restSituaciones_riesgosMockMvc = MockMvcBuilders.standaloneSetup(situaciones_riesgosResource)
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
    public static Situaciones_riesgos createEntity(EntityManager em) {
        Situaciones_riesgos situaciones_riesgos = new Situaciones_riesgos()
            .id_registro_riesgo(DEFAULT_ID_REGISTRO_RIESGO)
            .tipo_variable(DEFAULT_TIPO_VARIABLE)
            .direccion(DEFAULT_DIRECCION)
            .oleaje_tamano(DEFAULT_OLEAJE_TAMANO)
            .vieno_velocidad(DEFAULT_VIENO_VELOCIDAD)
            .marea_altura(DEFAULT_MAREA_ALTURA)
            .marea_rango(DEFAULT_MAREA_RANGO)
            .marea_momento(DEFAULT_MAREA_MOMENTO)
            .probabilidad(DEFAULT_PROBABILIDAD)
            .severidad(DEFAULT_SEVERIDAD)
            .evaluacion(DEFAULT_EVALUACION)
            .fecha(DEFAULT_FECHA);
        return situaciones_riesgos;
    }

    @Before
    public void initTest() {
        situaciones_riesgos = createEntity(em);
    }

    @Test
    @Transactional
    public void createSituaciones_riesgos() throws Exception {
        int databaseSizeBeforeCreate = situaciones_riesgosRepository.findAll().size();

        // Create the Situaciones_riesgos
        restSituaciones_riesgosMockMvc.perform(post("/api/situaciones-riesgos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(situaciones_riesgos)))
            .andExpect(status().isCreated());

        // Validate the Situaciones_riesgos in the database
        List<Situaciones_riesgos> situaciones_riesgosList = situaciones_riesgosRepository.findAll();
        assertThat(situaciones_riesgosList).hasSize(databaseSizeBeforeCreate + 1);
        Situaciones_riesgos testSituaciones_riesgos = situaciones_riesgosList.get(situaciones_riesgosList.size() - 1);
        assertThat(testSituaciones_riesgos.getId_registro_riesgo()).isEqualTo(DEFAULT_ID_REGISTRO_RIESGO);
        assertThat(testSituaciones_riesgos.getTipo_variable()).isEqualTo(DEFAULT_TIPO_VARIABLE);
        assertThat(testSituaciones_riesgos.getDireccion()).isEqualTo(DEFAULT_DIRECCION);
        assertThat(testSituaciones_riesgos.getOleaje_tamano()).isEqualTo(DEFAULT_OLEAJE_TAMANO);
        assertThat(testSituaciones_riesgos.getVieno_velocidad()).isEqualTo(DEFAULT_VIENO_VELOCIDAD);
        assertThat(testSituaciones_riesgos.getMarea_altura()).isEqualTo(DEFAULT_MAREA_ALTURA);
        assertThat(testSituaciones_riesgos.getMarea_rango()).isEqualTo(DEFAULT_MAREA_RANGO);
        assertThat(testSituaciones_riesgos.getMarea_momento()).isEqualTo(DEFAULT_MAREA_MOMENTO);
        assertThat(testSituaciones_riesgos.getProbabilidad()).isEqualTo(DEFAULT_PROBABILIDAD);
        assertThat(testSituaciones_riesgos.getSeveridad()).isEqualTo(DEFAULT_SEVERIDAD);
        assertThat(testSituaciones_riesgos.getEvaluacion()).isEqualTo(DEFAULT_EVALUACION);
        assertThat(testSituaciones_riesgos.getFecha()).isEqualTo(DEFAULT_FECHA);
    }

    @Test
    @Transactional
    public void createSituaciones_riesgosWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = situaciones_riesgosRepository.findAll().size();

        // Create the Situaciones_riesgos with an existing ID
        situaciones_riesgos.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSituaciones_riesgosMockMvc.perform(post("/api/situaciones-riesgos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(situaciones_riesgos)))
            .andExpect(status().isBadRequest());

        // Validate the Situaciones_riesgos in the database
        List<Situaciones_riesgos> situaciones_riesgosList = situaciones_riesgosRepository.findAll();
        assertThat(situaciones_riesgosList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllSituaciones_riesgos() throws Exception {
        // Initialize the database
        situaciones_riesgosRepository.saveAndFlush(situaciones_riesgos);

        // Get all the situaciones_riesgosList
        restSituaciones_riesgosMockMvc.perform(get("/api/situaciones-riesgos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(situaciones_riesgos.getId().intValue())))
            .andExpect(jsonPath("$.[*].id_registro_riesgo").value(hasItem(DEFAULT_ID_REGISTRO_RIESGO)))
            .andExpect(jsonPath("$.[*].tipo_variable").value(hasItem(DEFAULT_TIPO_VARIABLE)))
            .andExpect(jsonPath("$.[*].direccion").value(hasItem(DEFAULT_DIRECCION.toString())))
            .andExpect(jsonPath("$.[*].oleaje_tamano").value(hasItem(DEFAULT_OLEAJE_TAMANO.toString())))
            .andExpect(jsonPath("$.[*].vieno_velocidad").value(hasItem(DEFAULT_VIENO_VELOCIDAD.toString())))
            .andExpect(jsonPath("$.[*].marea_altura").value(hasItem(DEFAULT_MAREA_ALTURA.toString())))
            .andExpect(jsonPath("$.[*].marea_rango").value(hasItem(DEFAULT_MAREA_RANGO)))
            .andExpect(jsonPath("$.[*].marea_momento").value(hasItem(DEFAULT_MAREA_MOMENTO)))
            .andExpect(jsonPath("$.[*].probabilidad").value(hasItem(DEFAULT_PROBABILIDAD.toString())))
            .andExpect(jsonPath("$.[*].severidad").value(hasItem(DEFAULT_SEVERIDAD.toString())))
            .andExpect(jsonPath("$.[*].evaluacion").value(hasItem(DEFAULT_EVALUACION.toString())))
            .andExpect(jsonPath("$.[*].fecha").value(hasItem(DEFAULT_FECHA.toString())));
    }

    @Test
    @Transactional
    public void getSituaciones_riesgos() throws Exception {
        // Initialize the database
        situaciones_riesgosRepository.saveAndFlush(situaciones_riesgos);

        // Get the situaciones_riesgos
        restSituaciones_riesgosMockMvc.perform(get("/api/situaciones-riesgos/{id}", situaciones_riesgos.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(situaciones_riesgos.getId().intValue()))
            .andExpect(jsonPath("$.id_registro_riesgo").value(DEFAULT_ID_REGISTRO_RIESGO))
            .andExpect(jsonPath("$.tipo_variable").value(DEFAULT_TIPO_VARIABLE))
            .andExpect(jsonPath("$.direccion").value(DEFAULT_DIRECCION.toString()))
            .andExpect(jsonPath("$.oleaje_tamano").value(DEFAULT_OLEAJE_TAMANO.toString()))
            .andExpect(jsonPath("$.vieno_velocidad").value(DEFAULT_VIENO_VELOCIDAD.toString()))
            .andExpect(jsonPath("$.marea_altura").value(DEFAULT_MAREA_ALTURA.toString()))
            .andExpect(jsonPath("$.marea_rango").value(DEFAULT_MAREA_RANGO))
            .andExpect(jsonPath("$.marea_momento").value(DEFAULT_MAREA_MOMENTO))
            .andExpect(jsonPath("$.probabilidad").value(DEFAULT_PROBABILIDAD.toString()))
            .andExpect(jsonPath("$.severidad").value(DEFAULT_SEVERIDAD.toString()))
            .andExpect(jsonPath("$.evaluacion").value(DEFAULT_EVALUACION.toString()))
            .andExpect(jsonPath("$.fecha").value(DEFAULT_FECHA.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingSituaciones_riesgos() throws Exception {
        // Get the situaciones_riesgos
        restSituaciones_riesgosMockMvc.perform(get("/api/situaciones-riesgos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSituaciones_riesgos() throws Exception {
        // Initialize the database
        situaciones_riesgosRepository.saveAndFlush(situaciones_riesgos);
        int databaseSizeBeforeUpdate = situaciones_riesgosRepository.findAll().size();

        // Update the situaciones_riesgos
        Situaciones_riesgos updatedSituaciones_riesgos = situaciones_riesgosRepository.findOne(situaciones_riesgos.getId());
        // Disconnect from session so that the updates on updatedSituaciones_riesgos are not directly saved in db
        em.detach(updatedSituaciones_riesgos);
        updatedSituaciones_riesgos
            .id_registro_riesgo(UPDATED_ID_REGISTRO_RIESGO)
            .tipo_variable(UPDATED_TIPO_VARIABLE)
            .direccion(UPDATED_DIRECCION)
            .oleaje_tamano(UPDATED_OLEAJE_TAMANO)
            .vieno_velocidad(UPDATED_VIENO_VELOCIDAD)
            .marea_altura(UPDATED_MAREA_ALTURA)
            .marea_rango(UPDATED_MAREA_RANGO)
            .marea_momento(UPDATED_MAREA_MOMENTO)
            .probabilidad(UPDATED_PROBABILIDAD)
            .severidad(UPDATED_SEVERIDAD)
            .evaluacion(UPDATED_EVALUACION)
            .fecha(UPDATED_FECHA);

        restSituaciones_riesgosMockMvc.perform(put("/api/situaciones-riesgos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedSituaciones_riesgos)))
            .andExpect(status().isOk());

        // Validate the Situaciones_riesgos in the database
        List<Situaciones_riesgos> situaciones_riesgosList = situaciones_riesgosRepository.findAll();
        assertThat(situaciones_riesgosList).hasSize(databaseSizeBeforeUpdate);
        Situaciones_riesgos testSituaciones_riesgos = situaciones_riesgosList.get(situaciones_riesgosList.size() - 1);
        assertThat(testSituaciones_riesgos.getId_registro_riesgo()).isEqualTo(UPDATED_ID_REGISTRO_RIESGO);
        assertThat(testSituaciones_riesgos.getTipo_variable()).isEqualTo(UPDATED_TIPO_VARIABLE);
        assertThat(testSituaciones_riesgos.getDireccion()).isEqualTo(UPDATED_DIRECCION);
        assertThat(testSituaciones_riesgos.getOleaje_tamano()).isEqualTo(UPDATED_OLEAJE_TAMANO);
        assertThat(testSituaciones_riesgos.getVieno_velocidad()).isEqualTo(UPDATED_VIENO_VELOCIDAD);
        assertThat(testSituaciones_riesgos.getMarea_altura()).isEqualTo(UPDATED_MAREA_ALTURA);
        assertThat(testSituaciones_riesgos.getMarea_rango()).isEqualTo(UPDATED_MAREA_RANGO);
        assertThat(testSituaciones_riesgos.getMarea_momento()).isEqualTo(UPDATED_MAREA_MOMENTO);
        assertThat(testSituaciones_riesgos.getProbabilidad()).isEqualTo(UPDATED_PROBABILIDAD);
        assertThat(testSituaciones_riesgos.getSeveridad()).isEqualTo(UPDATED_SEVERIDAD);
        assertThat(testSituaciones_riesgos.getEvaluacion()).isEqualTo(UPDATED_EVALUACION);
        assertThat(testSituaciones_riesgos.getFecha()).isEqualTo(UPDATED_FECHA);
    }

    @Test
    @Transactional
    public void updateNonExistingSituaciones_riesgos() throws Exception {
        int databaseSizeBeforeUpdate = situaciones_riesgosRepository.findAll().size();

        // Create the Situaciones_riesgos

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restSituaciones_riesgosMockMvc.perform(put("/api/situaciones-riesgos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(situaciones_riesgos)))
            .andExpect(status().isCreated());

        // Validate the Situaciones_riesgos in the database
        List<Situaciones_riesgos> situaciones_riesgosList = situaciones_riesgosRepository.findAll();
        assertThat(situaciones_riesgosList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteSituaciones_riesgos() throws Exception {
        // Initialize the database
        situaciones_riesgosRepository.saveAndFlush(situaciones_riesgos);
        int databaseSizeBeforeDelete = situaciones_riesgosRepository.findAll().size();

        // Get the situaciones_riesgos
        restSituaciones_riesgosMockMvc.perform(delete("/api/situaciones-riesgos/{id}", situaciones_riesgos.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Situaciones_riesgos> situaciones_riesgosList = situaciones_riesgosRepository.findAll();
        assertThat(situaciones_riesgosList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Situaciones_riesgos.class);
        Situaciones_riesgos situaciones_riesgos1 = new Situaciones_riesgos();
        situaciones_riesgos1.setId(1L);
        Situaciones_riesgos situaciones_riesgos2 = new Situaciones_riesgos();
        situaciones_riesgos2.setId(situaciones_riesgos1.getId());
        assertThat(situaciones_riesgos1).isEqualTo(situaciones_riesgos2);
        situaciones_riesgos2.setId(2L);
        assertThat(situaciones_riesgos1).isNotEqualTo(situaciones_riesgos2);
        situaciones_riesgos1.setId(null);
        assertThat(situaciones_riesgos1).isNotEqualTo(situaciones_riesgos2);
    }
}
