package com.wsda.bikegarage.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "pezzi_richiesti", schema = "bikegarage")
public class PezziRichiesti {
    @EmbeddedId
    private PezziRichiestiId id;

    @MapsId("idRicambio")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_ricambio", nullable = false)
    private com.wsda.bikegarage.entities.Ricambi idRicambio;

    @MapsId("idRiparazione")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_riparazione", nullable = false)
    private com.wsda.bikegarage.entities.Riparazione idRiparazione;

    @Column(name = "`quantità`", nullable = false)
    private Integer quantità;

    public PezziRichiestiId getId() {
        return id;
    }

    public void setId(PezziRichiestiId id) {
        this.id = id;
    }

    public com.wsda.bikegarage.entities.Ricambi getIdRicambio() {
        return idRicambio;
    }

    public void setIdRicambio(com.wsda.bikegarage.entities.Ricambi idRicambio) {
        this.idRicambio = idRicambio;
    }

    public com.wsda.bikegarage.entities.Riparazione getIdRiparazione() {
        return idRiparazione;
    }

    public void setIdRiparazione(com.wsda.bikegarage.entities.Riparazione idRiparazione) {
        this.idRiparazione = idRiparazione;
    }

    public Integer getQuantità() {
        return quantità;
    }

    public void setQuantità(Integer quantità) {
        this.quantità = quantità;
    }

}