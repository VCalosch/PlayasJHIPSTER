package esto.es.java.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Tipologia_riesgos.
 */
@Entity
@Table(name = "tipologia_riesgos")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Tipologia_riesgos implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "detalle_topologia")
    private String detalle_topologia;

    @Column(name = "id_riesgo")
    private Integer id_Riesgo;

    @ManyToOne
    private Riesgos tipories;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDetalle_topologia() {
        return detalle_topologia;
    }

    public Tipologia_riesgos detalle_topologia(String detalle_topologia) {
        this.detalle_topologia = detalle_topologia;
        return this;
    }

    public void setDetalle_topologia(String detalle_topologia) {
        this.detalle_topologia = detalle_topologia;
    }

    public Integer getId_Riesgo() {
        return id_Riesgo;
    }

    public Tipologia_riesgos id_Riesgo(Integer id_Riesgo) {
        this.id_Riesgo = id_Riesgo;
        return this;
    }

    public void setId_Riesgo(Integer id_Riesgo) {
        this.id_Riesgo = id_Riesgo;
    }

    public Riesgos getTipories() {
        return tipories;
    }

    public Tipologia_riesgos tipories(Riesgos riesgos) {
        this.tipories = riesgos;
        return this;
    }

    public void setTipories(Riesgos riesgos) {
        this.tipories = riesgos;
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
        Tipologia_riesgos tipologia_riesgos = (Tipologia_riesgos) o;
        if (tipologia_riesgos.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), tipologia_riesgos.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Tipologia_riesgos{" +
            "id=" + getId() +
            ", detalle_topologia='" + getDetalle_topologia() + "'" +
            ", id_Riesgo=" + getId_Riesgo() +
            "}";
    }
}
