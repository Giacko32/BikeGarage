package com.wsda.bikegarage.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "moto", schema = "bikegarage")
public class Moto {
    @Id
    @Column(name = "targa", nullable = false, length = 7)
    private String targa;

    @Column(name = "modello", nullable = false)
    private String modello;

    @Column(name = "marca", nullable = false)
    private String marca;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_utente", nullable = false)
    private com.wsda.bikegarage.entities.Utente idUtente;

    public String getTarga() {
        return targa;
    }

    public void setTarga(String targa) {
        this.targa = targa;
    }

    public String getModello() {
        return modello;
    }

    public void setModello(String modello) {
        this.modello = modello;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public com.wsda.bikegarage.entities.Utente getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(com.wsda.bikegarage.entities.Utente idUtente) {
        this.idUtente = idUtente;
    }

}