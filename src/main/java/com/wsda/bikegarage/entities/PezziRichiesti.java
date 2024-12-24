package com.wsda.bikegarage.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "pezzi_richiesti")
public class PezziRichiesti {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_pezric", nullable = false)
    private Integer id;

    @Column(name = "quantita", nullable = false)
    private Integer quantita;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_riparazione", nullable = false)
    private Riparazione idRiparazione;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_ricambio", nullable = false)
    private Ricambi idRicambio;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantita() {
        return quantita;
    }

    public void setQuantita(Integer quantita) {
        this.quantita = quantita;
    }

    public Riparazione getIdRiparazione() {
        return idRiparazione;
    }

    public void setIdRiparazione(Riparazione idRiparazione) {
        this.idRiparazione = idRiparazione;
    }

    public Ricambi getIdRicambio() {
        return idRicambio;
    }

    public void setIdRicambio(Ricambi idRicambio) {
        this.idRicambio = idRicambio;
    }

}