package com.example.agendamentoapp.controller;

import com.example.agendamentoapp.entity.Agendamento;
import com.example.agendamentoapp.enums.StatusEntrada;
import com.example.agendamentoapp.service.AgendamentoService;
import com.example.agendamentoapp.service.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agendamentos")
public class AgendamentoRestController {

    private final AgendamentoService agendamentoService;

    public AgendamentoRestController(AgendamentoService agendamentoService) {
        this.agendamentoService = agendamentoService;

    }

    @GetMapping
    public List<Agendamento> listarCaminhoes() {
        return agendamentoService.listarCaminhoes();
    }

    @PostMapping
    public Agendamento agendarCaminhao(@RequestBody Agendamento agendamento) {
        return agendamentoService.agendarCaminhao(agendamento);
    }

    @PutMapping("/{id}/status")
    public Agendamento atualizarStatusCaminhao(@PathVariable Long id, @RequestParam StatusEntrada novoStatus) {
        return agendamentoService.atualizarStatusCaminhao(id, novoStatus);
    }

    @DeleteMapping("/{id}")
    public void deletarAgendamento(@PathVariable Long id) {
        agendamentoService.deletarAgendamento(id);
    }

}
