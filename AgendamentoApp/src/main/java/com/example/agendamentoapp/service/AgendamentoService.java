package com.example.agendamentoapp.service;

import com.example.agendamentoapp.entity.Agendamento;
import com.example.agendamentoapp.enums.StatusEntrada;
import com.example.agendamentoapp.exceptions.ResourceNotFoundException;
import com.example.agendamentoapp.repository.AgendamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;

    public AgendamentoService(AgendamentoRepository agendamentoRepository) {
        this.agendamentoRepository = agendamentoRepository;
    }

    public List<Agendamento> listarCaminhoes() {
        return agendamentoRepository.findAll();
    }

    public Agendamento agendarCaminhao(Agendamento agendamento) {
        agendamento.setStatus(StatusEntrada.PENDENTE);
        return agendamentoRepository.save(agendamento);
    }

    public Agendamento atualizarStatusCaminhao(Long id, StatusEntrada novoStatus) {
        Agendamento agendamento = agendamentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Caminhao não encontrado para o ID :: " + id));

        agendamento.setStatus(novoStatus);
        return agendamentoRepository.save(agendamento);
    }

    public void deletarAgendamento(Long id) {
        agendamentoRepository.deleteById(id);
    }
}

