package com.dh.Projeto.Integrador.Controller;

import com.dh.Projeto.Integrador.Service.UsuarioService;
import com.dh.Projeto.Integrador.exceptions.BadRequestException;
import com.dh.Projeto.Integrador.exceptions.ResourceNotFoundException;
import com.dh.Projeto.Integrador.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<String> processarErrorBadResquest(BadRequestException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
