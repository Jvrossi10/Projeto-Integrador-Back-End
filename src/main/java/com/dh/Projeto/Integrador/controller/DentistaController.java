package com.dh.Projeto.Integrador.Controller;

import com.dh.Projeto.Integrador.exceptions.BadRequestException;
import com.dh.Projeto.Integrador.exceptions.ResourceNotFoundException;
import com.dh.Projeto.Integrador.model.Dentista;
import com.dh.Projeto.Integrador.Service.DentistaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<String> processarErrorBadResquest(BadRequestException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
