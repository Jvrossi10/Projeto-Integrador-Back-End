package com.dh.Projeto.Integrador.controller;

import com.dh.Projeto.Integrador.service.UsuarioService;
import com.dh.Projeto.Integrador.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario){
        return ResponseEntity.ok(usuarioService.salvar(usuario));
    }


    @GetMapping("/todos")
    public List<Usuario> buscarTodos(){
        return usuarioService.buscarTodos();
    }
}
