package com.wsda.bikegarage.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.util.Objects;

@Embeddable
public class PezziRichiestiId implements java.io.Serializable {
    private static final long serialVersionUID = 8318160788120396791L;
    @Column(name = "id_ricambio", nullable = false)
    private Integer idRicambio;

    @Column(name = "id_riparazione", nullable = false)
    private Integer idRiparazione;

    public Integer getIdRicambio() {
        return idRicambio;
    }

    public void setIdRicambio(Integer idRicambio) {
        this.idRicambio = idRicambio;
    }

    public Integer getIdRiparazione() {
        return idRiparazione;
    }

    public void setIdRiparazione(Integer idRiparazione) {
        this.idRiparazione = idRiparazione;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PezziRichiestiId entity = (PezziRichiestiId) o;
        return Objects.equals(this.idRicambio, entity.idRicambio) &&
                Objects.equals(this.idRiparazione, entity.idRiparazione);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRicambio, idRiparazione);
    }

}