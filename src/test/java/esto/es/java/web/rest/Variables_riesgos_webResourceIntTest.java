package esto.es.java.web.rest;

import esto.es.java.PlayasApp;

import esto.es.java.domain.Variables_riesgos_web;
import esto.es.java.repository.Variables_riesgos_webRepository;
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
 * Test class for the Variables_riesgos_webResource REST controller.
 *
 * @see Variables_riesgos_webResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlayasApp.class)
public class Variables_riesgos_webResourceIntTest {

    private static final Integer DEFAULT_ID_REGISTRO_RIESGO = 1;
    private static final Integer UPDATED_ID_REGISTRO_RIESGO = 2;

    private static final String DEFAULT_TIPO_VARIABLE = "AAAAAAAAAA";
    private static final String UPDATED_TIPO_VARIABLE = "BBBBBBBBBB";

    private static final String DEFAULT_DIRECCION = "AAAAAAAAAA";
    private static final String UPDATED_DIRECCION = "BBBBBBBBBB";

    private static final String DEFAULT_OLEAJE_TIPO = "AAAAAAAAAA";
    private static final String UPDATED_OLEAJE_TIPO = "BBBBBBBBBB";

    private static final Float DEFAULT_OLEAJE_TAMANO = 1F;
    private static final Float UPDATED_OLEAJE_TAMANO = 2F;

    private static final Float DEFAULT_VIENTO_VELOCIDAD = 1F;
    private static final Float UPDATED_VIENTO_VELOCIDAD = 2F;

    private static final String DEFAULT_MAREA_RANGO = "AAAAAAAAAA";
    private static final String UPDATED_MAREA_RANGO = "BBBBBBBBBB";

    private static final String DEFAULT_MAREA_MOMENTO = "AAAAAAAAAA";
    private static final String UPDATED_MAREA_MOMENTO = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_FECHA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FECHA = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private Variables_riesgos_webRepository variables_riesgos_webRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restVariables_riesgos_webMockMvc;

    private Variables_riesgos_web variables_riesgos_web;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final Variables_riesgos_webResource variables_riesgos_webResource = new Variables_riesgos_webResource(variables_riesgos_webRepository);
        this.restVariables_riesgos_webMockMvc = MockMvcBuilders.standaloneSetup(variables_riesgos_webResource)
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
    public static Variables_riesgos_web createEntity(EntityManager em) {
        Variables_riesgos_web variables_riesgos_web = new Variables_riesgos_web()
            .id_Registro_Riesgo(DEFAULT_ID_REGISTRO_RIESGO)
            .tipo_Variable(DEFAULT_TIPO_VARIABLE)
            .direccion(DEFAULT_DIRECCION)
            .oleaje_Tipo(DEFAULT_OLEAJE_TIPO)
            .oleaje_tamano(DEFAULT_OLEAJE_TAMANO)
            .viento_velocidad(DEFAULT_VIENTO_VELOCIDAD)
            .marea_Rango(DEFAULT_MAREA_RANGO)
            .marea_Momento(DEFAULT_MAREA_MOMENTO)
            .fecha(DEFAULT_FECHA);
        return variables_riesgos_web;
    }

    @Before
    public void initTest() {
        variables_riesgos_web = createEntity(em);
    }

    @Test
    @Transactional
    public void createVariables_riesgos_web() throws Exception {
        int databaseSizeBeforeCreate = variables_riesgos_webRepository.findAll().size();

        // Create the Variables_riesgos_web
        restVariables_riesgos_webMockMvc.perform(post("/api/variables-riesgos-webs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(variables_riesgos_web)))
            .andExpect(status().isCreated());

        // Validate the Variables_riesgos_web in the database
        List<Variables_riesgos_web> variables_riesgos_webList = variables_riesgos_webRepository.findAll();
        assertThat(variables_riesgos_webList).hasSize(databaseSizeBeforeCreate + 1);
        Variables_riesgos_web testVariables_riesgos_web = variables_riesgos_webList.get(variables_riesgos_webList.size() - 1);
        assertThat(testVariables_riesgos_web.getId_Registro_Riesgo()).isEqualTo(DEFAULT_ID_REGISTRO_RIESGO);
        assertThat(testVariables_riesgos_web.getTipo_Variable()).isEqualTo(DEFAULT_TIPO_VARIABLE);
        assertThat(testVariables_riesgos_web.getDireccion()).isEqualTo(DEFAULT_DIRECCION);
        assertThat(testVariables_riesgos_web.getOleaje_Tipo()).isEqualTo(DEFAULT_OLEAJE_TIPO);
        assertThat(testVariables_riesgos_web.getOleaje_tamano()).isEqualTo(DEFAULT_OLEAJE_TAMANO);
        assertThat(testVariables_riesgos_web.getViento_velocidad()).isEqualTo(DEFAULT_VIENTO_VELOCIDAD);
        assertThat(testVariables_riesgos_web.getMarea_Rango()).isEqualTo(DEFAULT_MAREA_RANGO);
        assertThat(testVariables_riesgos_web.getMarea_Momento()).isEqualTo(DEFAULT_MAREA_MOMENTO);
        assertThat(testVariables_riesgos_web.getFecha()).isEqualTo(DEFAULT_FECHA);
    }

    @Test
    @Transactional
    public void createVariables_riesgos_webWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = variables_riesgos_webRepository.findAll().size();

        // Create the Variables_riesgos_web with an existing ID
        variables_riesgos_web.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restVariables_riesgos_webMockMvc.perform(post("/api/variables-riesgos-webs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(variables_riesgos_web)))
            .andExpect(status().isBadRequest());

        // Validate the Variables_riesgos_web in the database
        List<Variables_riesgos_web> variables_riesgos_webList = variables_riesgos_webRepository.findAll();
        assertThat(variables_riesgos_webList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllVariables_riesgos_webs() throws Exception {
        // Initialize the database
        variables_riesgos_webRepository.saveAndFlush(variables_riesgos_web);

        // Get all the variables_riesgos_webList
        restVariables_riesgos_webMockMvc.perform(get("/api/variables-riesgos-webs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(variables_riesgos_web.getId().intValue())))
            .andExpect(jsonPath("$.[*].id_Registro_Riesgo").value(hasItem(DEFAULT_ID_REGISTRO_RIESGO)))
            .andExpect(jsonPath("$.[*].tipo_Variable").value(hasItem(DEFAULT_TIPO_VARIABLE.toString())))
            .andExpect(jsonPath("$.[*].direccion").value(hasItem(DEFAULT_DIRECCION.toString())))
            .andExpect(jsonPath("$.[*].oleaje_Tipo").value(hasItem(DEFAULT_OLEAJE_TIPO.toString())))
            .andExpect(jsonPath("$.[*].oleaje_tamano").value(hasItem(DEFAULT_OLEAJE_TAMANO.doubleValue())))
            .andExpect(jsonPath("$.[*].viento_velocidad").value(hasItem(DEFAULT_VIENTO_VELOCIDAD.doubleValue())))
            .andExpect(jsonPath("$.[*].marea_Rango").value(hasItem(DEFAULT_MAREA_RANGO.toString())))
            .andExpect(jsonPath("$.[*].marea_Momento").value(hasItem(DEFAULT_MAREA_MOMENTO.toString())))
            .andExpect(jsonPath("$.[*].fecha").value(hasItem(DEFAULT_FECHA.toString())));
    }

    @Test
    @Transactional
    public void getVariables_riesgos_web() throws Exception {
        // Initialize the database
        variables_riesgos_webRepository.saveAndFlush(variables_riesgos_web);

        // Get the variables_riesgos_web
        restVariables_riesgos_webMockMvc.perform(get("/api/variables-riesgos-webs/{id}", variables_riesgos_web.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(variables_riesgos_web.getId().intValue()))
            .andExpect(jsonPath("$.id_Registro_Riesgo").value(DEFAULT_ID_REGISTRO_RIESGO))
            .andExpect(jsonPath("$.tipo_Variable").value(DEFAULT_TIPO_VARIABLE.toString()))
            .andExpect(jsonPath("$.direccion").value(DEFAULT_DIRECCION.toString()))
            .andExpect(jsonPath("$.oleaje_Tipo").value(DEFAULT_OLEAJE_TIPO.toString()))
            .andExpect(jsonPath("$.oleaje_tamano").value(DEFAULT_OLEAJE_TAMANO.doubleValue()))
            .andExpect(jsonPath("$.viento_velocidad").value(DEFAULT_VIENTO_VELOCIDAD.doubleValue()))
            .andExpect(jsonPath("$.marea_Rango").value(DEFAULT_MAREA_RANGO.toString()))
            .andExpect(jsonPath("$.marea_Momento").value(DEFAULT_MAREA_MOMENTO.toString()))
            .andExpect(jsonPath("$.fecha").value(DEFAULT_FECHA.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingVariables_riesgos_web() throws Exception {
        // Get the variables_riesgos_web
        restVariables_riesgos_webMockMvc.perform(get("/api/variables-riesgos-webs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateVariables_riesgos_web() throws Exception {
        // Initialize the database
        variables_riesgos_webRepository.saveAndFlush(variables_riesgos_web);
        int databaseSizeBeforeUpdate = variables_riesgos_webRepository.findAll().size();

        // Update the variables_riesgos_web
        Variables_riesgos_web updatedVariables_riesgos_web = variables_riesgos_webRepository.findOne(variables_riesgos_web.getId());
        // Disconnect from session so that the updates on updatedVariables_riesgos_web are not directly saved in db
        em.detach(updatedVariables_riesgos_web);
        updatedVariables_riesgos_web
            .id_Registro_Riesgo(UPDATED_ID_REGISTRO_RIESGO)
            .tipo_Variable(UPDATED_TIPO_VARIABLE)
            .direccion(UPDATED_DIRECCION)
            .oleaje_Tipo(UPDATED_OLEAJE_TIPO)
            .oleaje_tamano(UPDATED_OLEAJE_TAMANO)
            .viento_velocidad(UPDATED_VIENTO_VELOCIDAD)
            .marea_Rango(UPDATED_MAREA_RANGO)
            .marea_Momento(UPDATED_MAREA_MOMENTO)
            .fecha(UPDATED_FECHA);

        restVariables_riesgos_webMockMvc.perform(put("/api/variables-riesgos-webs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedVariables_riesgos_web)))
            .andExpect(status().isOk());

        // Validate the Variables_riesgos_web in the database
        List<Variables_riesgos_web> variables_riesgos_webList = variables_riesgos_webRepository.findAll();
        assertThat(variables_riesgos_webList).hasSize(databaseSizeBeforeUpdate);
        Variables_riesgos_web testVariables_riesgos_web = variables_riesgos_webList.get(variables_riesgos_webList.size() - 1);
        assertThat(testVariables_riesgos_web.getId_Registro_Riesgo()).isEqualTo(UPDATED_ID_REGISTRO_RIESGO);
        assertThat(testVariables_riesgos_web.getTipo_Variable()).isEqualTo(UPDATED_TIPO_VARIABLE);
        assertThat(testVariables_riesgos_web.getDireccion()).isEqualTo(UPDATED_DIRECCION);
        assertThat(testVariables_riesgos_web.getOleaje_Tipo()).isEqualTo(UPDATED_OLEAJE_TIPO);
        assertThat(testVariables_riesgos_web.getOleaje_tamano()).isEqualTo(UPDATED_OLEAJE_TAMANO);
        assertThat(testVariables_riesgos_web.getViento_velocidad()).isEqualTo(UPDATED_VIENTO_VELOCIDAD);
        assertThat(testVariables_riesgos_web.getMarea_Rango()).isEqualTo(UPDATED_MAREA_RANGO);
        assertThat(testVariables_riesgos_web.getMarea_Momento()).isEqualTo(UPDATED_MAREA_MOMENTO);
        assertThat(testVariables_riesgos_web.getFecha()).isEqualTo(UPDATED_FECHA);
    }

    @Test
    @Transactional
    public void updateNonExistingVariables_riesgos_web() throws Exception {
        int databaseSizeBeforeUpdate = variables_riesgos_webRepository.findAll().size();

        // Create the Variables_riesgos_web

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restVariables_riesgos_webMockMvc.perform(put("/api/variables-riesgos-webs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(variables_riesgos_web)))
            .andExpect(status().isCreated());

        // Validate the Variables_riesgos_web in the database
        List<Variables_riesgos_web> variables_riesgos_webList = variables_riesgos_webRepository.findAll();
        assertThat(variables_riesgos_webList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteVariables_riesgos_web() throws Exception {
        // Initialize the database
        variables_riesgos_webRepository.saveAndFlush(variables_riesgos_web);
        int databaseSizeBeforeDelete = variables_riesgos_webRepository.findAll().size();

        // Get the variables_riesgos_web
        restVariables_riesgos_webMockMvc.perform(delete("/api/variables-riesgos-webs/{id}", variables_riesgos_web.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Variables_riesgos_web> variables_riesgos_webList = variables_riesgos_webRepository.findAll();
        assertThat(variables_riesgos_webList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Variables_riesgos_web.class);
        Variables_riesgos_web variables_riesgos_web1 = new Variables_riesgos_web();
        variables_riesgos_web1.setId(1L);
        Variables_riesgos_web variables_riesgos_web2 = new Variables_riesgos_web();
        variables_riesgos_web2.setId(variables_riesgos_web1.getId());
        assertThat(variables_riesgos_web1).isEqualTo(variables_riesgos_web2);
        variables_riesgos_web2.setId(2L);
        assertThat(variables_riesgos_web1).isNotEqualTo(variables_riesgos_web2);
        variables_riesgos_web1.setId(null);
        assertThat(variables_riesgos_web1).isNotEqualTo(variables_riesgos_web2);
    }
}
