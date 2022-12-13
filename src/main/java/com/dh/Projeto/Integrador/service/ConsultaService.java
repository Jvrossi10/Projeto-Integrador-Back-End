package com.dh.Projeto.Integrador.service;

import com.dh.Projeto.Integrador.exceptions.ResourceNotFoundException;
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
    DentistaRepository dentistaRepository;

    @Autowired
    UsuarioRepository usuarioRepository;


    public ConsultaService(ConsultaRepository consultaRepository) {
        this.consultaRepository = consultaRepository;
    }

    public Consulta salvar(Consulta consulta) throws ResourceNotFoundException {
        Dentista dentista = consulta.getDentista();
        Optional<Dentista> idDentista = dentistaRepository.findById(dentista.getId_dentista());
        Usuario usuario = consulta.getUsuario();
        Optional<Usuario> idUsuario = usuarioRepository.findById(usuario.getId_usuario());

        if(idDentista.isEmpty() || idUsuario.isEmpty()) {
            throw new ResourceNotFoundException("Usuário e/ou dentista não encontrado(s).");
        }
        return consultaRepository.save(consulta);
    }

    public List<Consulta> listarTodas() {
        return consultaRepository.findAll();
    }

    public void deletar(Integer id) throws ResourceNotFoundException {

        consultaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Não foi possível excluir a consulta de Id " +id));
        consultaRepository.deleteById(id);
    }

    public Consulta atualizar(Consulta consulta) throws ResourceNotFoundException{
        if(consultaRepository.findById(consulta.getId_consulta()).isEmpty()) {
            throw new ResourceNotFoundException("Não foi possível encontrar a consulta informada.");
        }
        return consultaRepository.save(consulta);
    }

    public Optional<Consulta> buscarPorId(Integer id) throws ResourceNotFoundException {
        consultaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Não foi possível encontrar a consulta pelo Id informado."));
        return consultaRepository.findById(id);
    }
}
