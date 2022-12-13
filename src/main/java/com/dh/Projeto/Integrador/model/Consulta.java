package com.dh.Projeto.Integrador.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "consultas")
public class Consulta {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id_consulta;

    @ManyToOne
    private Dentista dentista;
    @ManyToOne
    private Usuario usuario;
    private LocalDateTime dataRegistro;
    private LocalDateTime dataConsulta;

    public Consulta() {
    }

    public Integer getId_consulta() {
        return id_consulta;
    }

    public void setId_consulta(Integer id_consulta) {
        this.id_consulta = id_consulta;
    }

    public Dentista getDentista() {
        return dentista;
    }

    public void setDentista(Dentista dentista) {
        this.dentista = dentista;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDateTime getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(LocalDateTime dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public LocalDateTime getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(LocalDateTime dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    @Override
    public String toString() {
        return "Consulta{" +
                "id=" + id_consulta +
                ", dentista=" + dentista +
                ", usuario=" + usuario +
                ", dataRegistro=" + dataRegistro +
                ", dataConsulta=" + dataConsulta +
                '}';
    }
}
