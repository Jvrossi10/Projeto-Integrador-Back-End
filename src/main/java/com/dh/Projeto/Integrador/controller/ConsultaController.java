package com.dh.Projeto.Integrador.controller;

import com.dh.Projeto.Integrador.exceptions.BadRequestException;
import com.dh.Projeto.Integrador.exceptions.ResourceNotFoundException;
import com.dh.Projeto.Integrador.model.Consulta;
import com.dh.Projeto.Integrador.service.ConsultaService;
import com.dh.Projeto.Integrador.service.DentistaService;
import com.dh.Projeto.Integrador.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private final ConsultaService consultaService;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    DentistaService dentistaService;

    public ConsultaController(ConsultaService consultaService) {
        this.consultaService = consultaService;
    }

    @PostMapping
    public ResponseEntity<Consulta> salvar(@RequestBody Consulta consulta) throws BadRequestException {
        try {
            return ResponseEntity.ok(consultaService.salvar(consulta));
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @GetMapping("/todos")
    public List<Consulta> buscarTodos() throws ResourceNotFoundException {
        try {
            return consultaService.listarTodas();
        } catch (Exception e) {
            throw new ResourceNotFoundException("Não foi possível listar consultas.");
        }
    }

    @DeleteMapping("/delete/{id}")
    public void deletar(@PathVariable Integer id) throws ResourceNotFoundException {
        try {
            consultaService.deletar(id);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Não foi encontrada a consulta pelo Id informado.");
        }
    }

    @PatchMapping
    public Consulta atualizar(@RequestBody Consulta consulta) throws ResourceNotFoundException {
        try {
            return consultaService.atualizar(consulta);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Não foi possível atualizar a consulta.");
        }
    }

    @GetMapping("/buscarporid/{id}")
    public Optional<Consulta> buscarPorId(@PathVariable Integer id) throws ResourceNotFoundException {
        try {
            return consultaService.buscarPorId(id);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Não foi possível encontrar a consulta pelo Id informado.");
        }
    }

    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<String> processarErrorBadResquest(BadRequestException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}




