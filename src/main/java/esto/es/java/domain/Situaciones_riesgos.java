package esto.es.java.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A Situaciones_riesgos.
 */
@Entity
@Table(name = "situaciones_riesgos")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Situaciones_riesgos implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_registro_riesgo")
    private Integer id_registro_riesgo;

    @Column(name = "tipo_variable")
    private Integer tipo_variable;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "oleaje_tamano")
    private String oleaje_tamano;

    @Column(name = "vieno_velocidad")
    private String vieno_velocidad;

    @Column(name = "marea_altura")
    private String marea_altura;

    @Column(name = "marea_rango")
    private Integer marea_rango;

    @Column(name = "marea_momento")
    private Integer marea_momento;

    @Column(name = "probabilidad")
    private String probabilidad;

    @Column(name = "severidad")
    private String severidad;

    @Column(name = "evaluacion")
    private String evaluacion;

    @Column(name = "fecha")
    private LocalDate fecha;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getId_registro_riesgo() {
        return id_registro_riesgo;
    }

    public Situaciones_riesgos id_registro_riesgo(Integer id_registro_riesgo) {
        this.id_registro_riesgo = id_registro_riesgo;
        return this;
    }

    public void setId_registro_riesgo(Integer id_registro_riesgo) {
        this.id_registro_riesgo = id_registro_riesgo;
    }

    public Integer getTipo_variable() {
        return tipo_variable;
    }

    public Situaciones_riesgos tipo_variable(Integer tipo_variable) {
        this.tipo_variable = tipo_variable;
        return this;
    }

    public void setTipo_variable(Integer tipo_variable) {
        this.tipo_variable = tipo_variable;
    }

    public String getDireccion() {
        return direccion;
    }

    public Situaciones_riesgos direccion(String direccion) {
        this.direccion = direccion;
        return this;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getOleaje_tamano() {
        return oleaje_tamano;
    }

    public Situaciones_riesgos oleaje_tamano(String oleaje_tamano) {
        this.oleaje_tamano = oleaje_tamano;
        return this;
    }

    public void setOleaje_tamano(String oleaje_tamano) {
        this.oleaje_tamano = oleaje_tamano;
    }

    public String getVieno_velocidad() {
        return vieno_velocidad;
    }

    public Situaciones_riesgos vieno_velocidad(String vieno_velocidad) {
        this.vieno_velocidad = vieno_velocidad;
        return this;
    }

    public void setVieno_velocidad(String vieno_velocidad) {
        this.vieno_velocidad = vieno_velocidad;
    }

    public String getMarea_altura() {
        return marea_altura;
    }

    public Situaciones_riesgos marea_altura(String marea_altura) {
        this.marea_altura = marea_altura;
        return this;
    }

    public void setMarea_altura(String marea_altura) {
        this.marea_altura = marea_altura;
    }

    public Integer getMarea_rango() {
        return marea_rango;
    }

    public Situaciones_riesgos marea_rango(Integer marea_rango) {
        this.marea_rango = marea_rango;
        return this;
    }

    public void setMarea_rango(Integer marea_rango) {
        this.marea_rango = marea_rango;
    }

    public Integer getMarea_momento() {
        return marea_momento;
    }

    public Situaciones_riesgos marea_momento(Integer marea_momento) {
        this.marea_momento = marea_momento;
        return this;
    }

    public void setMarea_momento(Integer marea_momento) {
        this.marea_momento = marea_momento;
    }

    public String getProbabilidad() {
        return probabilidad;
    }

    public Situaciones_riesgos probabilidad(String probabilidad) {
        this.probabilidad = probabilidad;
        return this;
    }

    public void setProbabilidad(String probabilidad) {
        this.probabilidad = probabilidad;
    }

    public String getSeveridad() {
        return severidad;
    }

    public Situaciones_riesgos severidad(String severidad) {
        this.severidad = severidad;
        return this;
    }

    public void setSeveridad(String severidad) {
        this.severidad = severidad;
    }

    public String getEvaluacion() {
        return evaluacion;
    }

    public Situaciones_riesgos evaluacion(String evaluacion) {
        this.evaluacion = evaluacion;
        return this;
    }

    public void setEvaluacion(String evaluacion) {
        this.evaluacion = evaluacion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public Situaciones_riesgos fecha(LocalDate fecha) {
        this.fecha = fecha;
        return this;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Situaciones_riesgos situaciones_riesgos = (Situaciones_riesgos) o;
        if (situaciones_riesgos.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), situaciones_riesgos.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Situaciones_riesgos{" +
            "id=" + getId() +
            ", id_registro_riesgo=" + getId_registro_riesgo() +
            ", tipo_variable=" + getTipo_variable() +
            ", direccion='" + getDireccion() + "'" +
            ", oleaje_tamano='" + getOleaje_tamano() + "'" +
            ", vieno_velocidad='" + getVieno_velocidad() + "'" +
            ", marea_altura='" + getMarea_altura() + "'" +
            ", marea_rango=" + getMarea_rango() +
            ", marea_momento=" + getMarea_momento() +
            ", probabilidad='" + getProbabilidad() + "'" +
            ", severidad='" + getSeveridad() + "'" +
            ", evaluacion='" + getEvaluacion() + "'" +
            ", fecha='" + getFecha() + "'" +
            "}";
    }
}
