package com.wsda.bikegarage.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "riparazione", schema = "bikegarage")
public class Riparazione {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_riparazione", nullable = false)
    private Integer id;

    @Column(name = "stato", nullable = false)
    private String stato;

    @Column(name = "lavorazioni")
    private String lavorazioni;

    @ColumnDefault("0")
    @Column(name = "Ore", nullable = false)
    private Integer ore;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_meccanico")
    private Impiegato idMeccanico;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "targa", nullable = false)
    private Moto targa;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public String getLavorazioni() {
        return lavorazioni;
    }

    public void setLavorazioni(String lavorazioni) {
        this.lavorazioni = lavorazioni;
    }

    public Integer getOre() {
        return ore;
    }

    public void setOre(Integer ore) {
        this.ore = ore;
    }

    public Impiegato getIdMeccanico() {
        return idMeccanico;
    }

    public void setIdMeccanico(Impiegato idMeccanico) {
        this.idMeccanico = idMeccanico;
    }

    public Moto getTarga() {
        return targa;
    }

    public void setTarga(Moto targa) {
        this.targa = targa;
    }

}