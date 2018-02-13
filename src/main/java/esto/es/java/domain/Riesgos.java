package esto.es.java.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Riesgos.
 */
@Entity
@Table(name = "riesgos")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Riesgos implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "detalle_riesgo")
    private String detalle_riesgo;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDetalle_riesgo() {
        return detalle_riesgo;
    }

    public Riesgos detalle_riesgo(String detalle_riesgo) {
        this.detalle_riesgo = detalle_riesgo;
        return this;
    }

    public void setDetalle_riesgo(String detalle_riesgo) {
        this.detalle_riesgo = detalle_riesgo;
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
        Riesgos riesgos = (Riesgos) o;
        if (riesgos.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), riesgos.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Riesgos{" +
            "id=" + getId() +
            ", detalle_riesgo='" + getDetalle_riesgo() + "'" +
            "}";
    }
}
