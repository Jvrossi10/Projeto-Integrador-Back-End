package com.dh.Projeto.Integrador.Repository;

import com.dh.Projeto.Integrador.model.Dentista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DentistaRepository extends JpaRepository<Dentista, Integer> {

}
