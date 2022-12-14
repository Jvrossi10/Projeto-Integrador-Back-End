package com.dh.Projeto.Integrador.controller;

import com.dh.Projeto.Integrador.exceptions.BadRequestException;
import com.dh.Projeto.Integrador.exceptions.ResourceNotFoundException;
import com.dh.Projeto.Integrador.model.Dentista;
import com.dh.Projeto.Integrador.service.DentistaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/dentistas")
public class DentistaController {
    private final DentistaService dentistaService;

    public DentistaController(DentistaService dentistaService) {
        this.dentistaService = dentistaService;
    }

    @PostMapping
    public ResponseEntity<Dentista> salvar(@RequestBody Dentista dentista) throws BadRequestException {
        return ResponseEntity.ok(dentistaService.salvar(dentista));
    }

    @GetMapping("/todos")
    public List<Dentista> listarTodos() throws ResourceNotFoundException {
        try {
            return dentistaService.buscarTodos();
        }catch (Exception e){
            throw new ResourceNotFoundException("Nenhum dentista encontrado.");
        }
    }

    @DeleteMapping("/delete/{id}")
    public void deletar(@PathVariable Dentista dentista) throws ResourceNotFoundException {
        try {
            dentistaService.deletar(dentista.getId());
        } catch (Exception e) {
            throw new ResourceNotFoundException("Dentista não encontrado.");
        }
    }

    @PatchMapping
    public Dentista atualizar(@RequestBody Dentista dentista) throws ResourceNotFoundException {
        try {
            return dentistaService.atualizar(dentista);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Dentista não encontrado.");
        }
    }

    @GetMapping("/buscarporid/{id}")
    public Optional<Dentista> buscarPorId(@PathVariable Integer id) throws ResourceNotFoundException {
        try {
            return dentistaService.buscarPorId(id);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Não foi possível encontrar o dentista pelo Id informado.");
        }
    }

    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<String> processarErrorBadResquest(BadRequestException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
