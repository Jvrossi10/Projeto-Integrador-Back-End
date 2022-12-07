package com.dh.Projeto.Integrador.service;

import com.dh.Projeto.Integrador.repository.UsuarioRepository;
import com.dh.Projeto.Integrador.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario salvar(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> buscarTodos(){
        return usuarioRepository.findAll();
    }

        
}
