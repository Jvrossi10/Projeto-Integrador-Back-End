package com.dh.Projeto.Integrador.service;

import com.dh.Projeto.Integrador.exceptions.ResourceNotFoundException;
import com.dh.Projeto.Integrador.logger.Logger;
import com.dh.Projeto.Integrador.model.Consulta;
import com.dh.Projeto.Integrador.model.Dentista;
import com.dh.Projeto.Integrador.model.Usuario;
import com.dh.Projeto.Integrador.repository.ConsultaRepository;
import com.dh.Projeto.Integrador.repository.DentistaRepository;
import com.dh.Projeto.Integrador.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsultaService {
    @Autowired
    private final ConsultaRepository consultaRepository;

    @Autowired
    Logger logger;

    @Autowired
    DentistaRepository dentistaRepository;

    @Autowired
    UsuarioRepository usuarioRepository;


    public ConsultaService(ConsultaRepository consultaRepository) {
        this.consultaRepository = consultaRepository;
    }

    public Consulta salvar(Consulta consulta) throws ResourceNotFoundException {
        logger.info("Salvando nova consulta.");
        Dentista dentista = consulta.getDentista();
        Optional<Dentista> idDentista = dentistaRepository.findById(dentista.getId());
        Usuario usuario = consulta.getUsuario();
        Optional<Usuario> idUsuario = usuarioRepository.findById(usuario.getId());

        if(idDentista.isEmpty() || idUsuario.isEmpty()) {
            throw new ResourceNotFoundException("Usuário e/ou dentista não encontrado(s).");
        }
        return consultaRepository.save(consulta);
    }

    public List<Consulta> listarTodas() {
        logger.info("Mostrando todas as consultas.");
        return consultaRepository.findAll();
    }

    public void deletar(Integer id) throws ResourceNotFoundException {
        logger.info("Deletando consulta.");
        consultaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Não foi possível excluir a consulta de Id " +id));
        consultaRepository.deleteById(id);
    }

    public Consulta atualizar(Consulta consulta) throws ResourceNotFoundException{
        logger.info("Atualizando consulta.");
        if(consultaRepository.findById(consulta.getId()).isEmpty()) {
            throw new ResourceNotFoundException("Não foi possível encontrar a consulta informada.");
        }
        return consultaRepository.save(consulta);
    }

    public Optional<Consulta> buscarPorId(Integer id) throws ResourceNotFoundException {
        logger.info("Buscando consulta pelo id: " +id);
        consultaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Não foi possível encontrar a consulta pelo Id informado."));
        return consultaRepository.findById(id);
    }
}
