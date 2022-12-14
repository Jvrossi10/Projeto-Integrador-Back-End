package com.dh.Projeto.Integrador.controller;

import com.dh.Projeto.Integrador.service.UsuarioService;
import com.dh.Projeto.Integrador.exceptions.BadRequestException;
import com.dh.Projeto.Integrador.exceptions.ResourceNotFoundException;
import com.dh.Projeto.Integrador.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario) throws BadRequestException {
        return ResponseEntity.ok(usuarioService.salvar(usuario));
    }


    @GetMapping("/todos")
    public List<Usuario> buscarTodos() throws ResourceNotFoundException {
        try {
            return usuarioService.buscarTodos();
        }catch (Exception e){
            throw new ResourceNotFoundException("Nenhum usuário encontrado.");
        }
    }

    @DeleteMapping("/delete/{id}")
    public void deletar(@PathVariable Integer id) throws ResourceNotFoundException {
        try {
            usuarioService.deletar(id);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Nenhum usuário encontrado.");
        }
    }

    @PatchMapping
    public Usuario atualizar(@RequestBody Usuario usuario) throws ResourceNotFoundException {
        try {
            return usuarioService.atualizar(usuario);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Não foi possível atualizar o usuário.");
        }
    }

    @GetMapping("/buscarporid/{id}")
    public Optional<Usuario> buscarPorId(@PathVariable Integer id) throws ResourceNotFoundException {
        try {
            return usuarioService.buscarPorId(id);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Não foi possível encontrar o usuário pelo Id informado.");
        }
    }

    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<String> processarErrorBadResquest(BadRequestException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
