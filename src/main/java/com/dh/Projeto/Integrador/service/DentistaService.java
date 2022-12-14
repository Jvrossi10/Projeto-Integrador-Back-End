package com.dh.Projeto.Integrador.service;

import com.dh.Projeto.Integrador.exceptions.ResourceNotFoundException;
import com.dh.Projeto.Integrador.logger.Logger;
import com.dh.Projeto.Integrador.model.Dentista;
import com.dh.Projeto.Integrador.repository.DentistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DentistaService implements UserDetailsService {

    @Autowired
    private final DentistaRepository dentistaRepository;

    @Autowired
    Logger logger;

    @Autowired
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
        if(dentistaRepository.findById(dentista.getId()).isEmpty()) {
            throw new ResourceNotFoundException("Não foi possível encontrar o dentista informado.");
        }
        return dentistaRepository.save(dentista);
    }

    public Optional<Dentista> buscarPorId(Integer id) throws ResourceNotFoundException {
        logger.info("Buscando dentista pelo id: " +id);
        dentistaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Não foi possível encontrar o dentista pelo Id informado."));
        return dentistaRepository.findById(id);
    }
    @Override
    public UserDetails loadUserByUsername(String matricula) throws UsernameNotFoundException {
        return dentistaRepository.findByMatricula(matricula).orElseThrow(() -> new UsernameNotFoundException("Dentista não encontrado"));
    }

}
