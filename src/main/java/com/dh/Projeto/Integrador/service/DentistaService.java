package com.dh.Projeto.Integrador.service;

import com.dh.Projeto.Integrador.model.Dentista;
import com.dh.Projeto.Integrador.repository.DentistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DentistaService {
    @Autowired
    private final DentistaRepository dentistaRepository;

    public DentistaService(DentistaRepository dentistaRepository) {
        this.dentistaRepository = dentistaRepository;
    }

    public Dentista salvar(Dentista dentista) {
        return dentistaRepository.save(dentista);
    }

    public List<Dentista> buscarTodos(){
        return dentistaRepository.findAll();
    }

    public void deletar(Integer id){
        dentistaRepository.deleteById(id);
    }
}
