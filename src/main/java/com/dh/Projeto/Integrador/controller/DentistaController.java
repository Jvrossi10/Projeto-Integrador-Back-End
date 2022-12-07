package com.dh.Projeto.Integrador.controller;

import com.dh.Projeto.Integrador.model.Dentista;
import com.dh.Projeto.Integrador.service.DentistaService;
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
    public ResponseEntity<Dentista> salvar(@RequestBody Dentista dentista) {
        return ResponseEntity.ok(dentistaService.salvar(dentista));
    }

    @GetMapping("/todos")
    public List<Dentista> listarTodos() {
        return dentistaService.buscarTodos();
    }
}
