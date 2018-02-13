package esto.es.java.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A Variables_riesgos_web.
 */
@Entity
@Table(name = "variables_riesgos_web")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Variables_riesgos_web implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_registro_riesgo")
    private Integer id_Registro_Riesgo;

    @Column(name = "tipo_variable")
    private String tipo_Variable;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "oleaje_tipo")
    private String oleaje_Tipo;

    @Column(name = "oleaje_tamano")
    private Float oleaje_tamano;

    @Column(name = "viento_velocidad")
    private Float viento_velocidad;

    @Column(name = "marea_rango")
    private String marea_Rango;

    @Column(name = "marea_momento")
    private String marea_Momento;

    @Column(name = "fecha")
    private LocalDate fecha;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getId_Registro_Riesgo() {
        return id_Registro_Riesgo;
    }

    public Variables_riesgos_web id_Registro_Riesgo(Integer id_Registro_Riesgo) {
        this.id_Registro_Riesgo = id_Registro_Riesgo;
        return this;
    }

    public void setId_Registro_Riesgo(Integer id_Registro_Riesgo) {
        this.id_Registro_Riesgo = id_Registro_Riesgo;
    }

    public String getTipo_Variable() {
        return tipo_Variable;
    }

    public Variables_riesgos_web tipo_Variable(String tipo_Variable) {
        this.tipo_Variable = tipo_Variable;
        return this;
    }

    public void setTipo_Variable(String tipo_Variable) {
        this.tipo_Variable = tipo_Variable;
    }

    public String getDireccion() {
        return direccion;
    }

    public Variables_riesgos_web direccion(String direccion) {
        this.direccion = direccion;
        return this;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getOleaje_Tipo() {
        return oleaje_Tipo;
    }

    public Variables_riesgos_web oleaje_Tipo(String oleaje_Tipo) {
        this.oleaje_Tipo = oleaje_Tipo;
        return this;
    }

    public void setOleaje_Tipo(String oleaje_Tipo) {
        this.oleaje_Tipo = oleaje_Tipo;
    }

    public Float getOleaje_tamano() {
        return oleaje_tamano;
    }

    public Variables_riesgos_web oleaje_tamano(Float oleaje_tamano) {
        this.oleaje_tamano = oleaje_tamano;
        return this;
    }

    public void setOleaje_tamano(Float oleaje_tamano) {
        this.oleaje_tamano = oleaje_tamano;
    }

    public Float getViento_velocidad() {
        return viento_velocidad;
    }

    public Variables_riesgos_web viento_velocidad(Float viento_velocidad) {
        this.viento_velocidad = viento_velocidad;
        return this;
    }

    public void setViento_velocidad(Float viento_velocidad) {
        this.viento_velocidad = viento_velocidad;
    }

    public String getMarea_Rango() {
        return marea_Rango;
    }

    public Variables_riesgos_web marea_Rango(String marea_Rango) {
        this.marea_Rango = marea_Rango;
        return this;
    }

    public void setMarea_Rango(String marea_Rango) {
        this.marea_Rango = marea_Rango;
    }

    public String getMarea_Momento() {
        return marea_Momento;
    }

    public Variables_riesgos_web marea_Momento(String marea_Momento) {
        this.marea_Momento = marea_Momento;
        return this;
    }

    public void setMarea_Momento(String marea_Momento) {
        this.marea_Momento = marea_Momento;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public Variables_riesgos_web fecha(LocalDate fecha) {
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
        Variables_riesgos_web variables_riesgos_web = (Variables_riesgos_web) o;
        if (variables_riesgos_web.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), variables_riesgos_web.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Variables_riesgos_web{" +
            "id=" + getId() +
            ", id_Registro_Riesgo=" + getId_Registro_Riesgo() +
            ", tipo_Variable='" + getTipo_Variable() + "'" +
            ", direccion='" + getDireccion() + "'" +
            ", oleaje_Tipo='" + getOleaje_Tipo() + "'" +
            ", oleaje_tamano=" + getOleaje_tamano() +
            ", viento_velocidad=" + getViento_velocidad() +
            ", marea_Rango='" + getMarea_Rango() + "'" +
            ", marea_Momento='" + getMarea_Momento() + "'" +
            ", fecha='" + getFecha() + "'" +
            "}";
    }
}
