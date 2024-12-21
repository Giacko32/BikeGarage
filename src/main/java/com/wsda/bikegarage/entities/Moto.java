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
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente idCliente;

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

    public Cliente getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Cliente idCliente) {
        this.idCliente = idCliente;
    }

}