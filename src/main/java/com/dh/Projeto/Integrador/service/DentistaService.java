package com.dh.Projeto.Integrador.service;

import com.dh.Projeto.Integrador.exceptions.ResourceNotFoundException;
import com.dh.Projeto.Integrador.logger.Logger;
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

    @Autowired
    Logger logger;

    public DentistaService(DentistaRepository dentistaRepository) {
        this.dentistaRepository = dentistaRepository;
    }

    public Dentista salvar(Dentista dentista) {
        logger.info("Salvando novo dentista.");
        return dentistaRepository.save(dentista);
    }

    public List<Dentista> buscarTodos(){
        logger.info("Buscando todos os dentistas.");
        return dentistaRepository.findAll();
    }

    public void deletar(Integer id){
        logger.info("Deletando dentista.");
        dentistaRepository.deleteById(id);
    }

    public Dentista atualizar(Dentista dentista) throws ResourceNotFoundException {
        logger.info("Atualizando dentista.");
        if(dentistaRepository.findById(dentista.getId_dentista()).isEmpty()) {
            throw new ResourceNotFoundException("Não foi possível encontrar o dentista informado.");
        }
        return dentistaRepository.save(dentista);
    }

    public Optional<Dentista> buscarPorId(Integer id) throws ResourceNotFoundException {
        logger.info("Buscando dentista pelo id: " +id);
        dentistaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Não foi possível encontrar o dentista pelo Id informado."));
        return dentistaRepository.findById(id);
    }
}
