package com.dh.Projeto.Integrador.repository;

import com.dh.Projeto.Integrador.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository <Usuario, Integer> {



}
