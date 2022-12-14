package com.dh.Projeto.Integrador.Repository;

import com.dh.Projeto.Integrador.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository <Usuario, Integer> {

    Optional<Usuario> findByEmail(String email);

}
