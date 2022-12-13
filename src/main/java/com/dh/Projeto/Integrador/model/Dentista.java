package com.dh.Projeto.Integrador.model;

import jakarta.persistence.*;

@Entity
@Table(name = "dentistas")
public class Dentista {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id_dentista;
    private String nome, sobrenome, matricula;

    public Dentista() {

    }

    public Integer getId_dentista() {
        return id_dentista;
    }

    public void setId_dentista(Integer id_dentista) {
        this.id_dentista = id_dentista;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    @Override
    public String toString() {
        return "Dentista{" +
                "id=" + id_dentista +
                ", nome='" + nome + '\'' +
                ", sobrenome='" + sobrenome + '\'' +
                ", matricula='" + matricula + '\'' +
                '}';
    }
}
