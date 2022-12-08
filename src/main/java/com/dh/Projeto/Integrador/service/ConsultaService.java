package com.dh.Projeto.Integrador.service;

import com.dh.Projeto.Integrador.model.Consulta;
import com.dh.Projeto.Integrador.model.Dentista;
import com.dh.Projeto.Integrador.model.Usuario;
import com.dh.Projeto.Integrador.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ConsultaService {
    @Autowired
    private final ConsultaRepository consultaRepository;

    public ConsultaService(ConsultaRepository consultaRepository) {
        this.consultaRepository = consultaRepository;
    }

    public Consulta salvar(Consulta consulta) {
        return consultaRepository.save(consulta);
    }

    public List<Consulta> listarTodas() {
        return consultaRepository.findAll();
    }

    public void deletar(Integer id) {
        consultaRepository.deleteById(id);
    }

    public void atualizar(Integer id, Dentista dentista, Usuario usuario, LocalDateTime dataRegistro, LocalDateTime dataConsulta) {
        Consulta consulta = null;
        consultaRepository.findById(id);
        consulta.setDentista(dentista);
        consulta.setUsuario(usuario);
        consulta.setDataRegistro(dataRegistro);
        consulta.setDataConsulta(dataConsulta);
        consultaRepository.save(consulta);

    }
}
