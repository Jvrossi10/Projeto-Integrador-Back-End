package com.dh.Projeto.Integrador.service;

import com.dh.Projeto.Integrador.exceptions.ResourceNotFoundException;
import com.dh.Projeto.Integrador.model.Dentista;
import com.dh.Projeto.Integrador.repository.DentistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Dentista atualizar(Dentista dentista) throws ResourceNotFoundException {
        if(dentistaRepository.findById(dentista.getId_dentista()).isEmpty()) {
            throw new ResourceNotFoundException("Não foi possível encontrar o dentista informado.");
        }
        return dentistaRepository.save(dentista);
    }

    public Optional<Dentista> buscarPorId(Integer id) throws ResourceNotFoundException {
        dentistaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Não foi possível encontrar o dentista pelo Id informado."));
        return dentistaRepository.findById(id);
    }
}
