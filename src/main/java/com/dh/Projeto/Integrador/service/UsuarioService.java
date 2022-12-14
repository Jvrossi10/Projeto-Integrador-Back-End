package com.dh.Projeto.Integrador.service;

import com.dh.Projeto.Integrador.Repository.UsuarioRepository;
import com.dh.Projeto.Integrador.logger.Logger;
import com.dh.Projeto.Integrador.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private final UsuarioRepository usuarioRepository;

    @Autowired
    Logger logger;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario salvar(Usuario usuario){
        logger.info("Salvando novo usuário.");
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> buscarTodos(){
        logger.info("Buscando todos os usuários.");
        return usuarioRepository.findAll();
    }

    public void deletar(Integer id) {
        logger.info("Deletando usuário.");
        usuarioRepository.deleteById(id);
    }

    public Usuario atualizar(Usuario usuario) {
        logger.info("Atualizando usuário.");
        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> buscarPorId(Integer id) {
        logger.info("Buscando usuário pelo id: " +id);
        return usuarioRepository.findById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return usuarioRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Usuario não encontrado!"));    }
}
