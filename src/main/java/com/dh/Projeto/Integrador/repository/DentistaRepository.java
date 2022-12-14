package com.dh.Projeto.Integrador.repository;

import com.dh.Projeto.Integrador.model.Dentista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DentistaRepository extends JpaRepository<Dentista, Integer> {

    Optional<Dentista> findByMatricula(String matricula);
}
