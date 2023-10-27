package com.example.agendamentoapp.controller;

import com.example.agendamentoapp.client.ViaCepResponse;
import com.example.agendamentoapp.service.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/viacep")
public class ViaCepController {

    @Autowired
    private final ViaCepService viaCepService;

    public ViaCepController(ViaCepService viaCepService) {
        this.viaCepService = viaCepService;
    }

    @GetMapping("/")
    public List<ViaCepResponse> getEnderecosPorCEPs() {
        List<String> ceps = Arrays.asList("07776901", "13480010", "89226435");
        List<ViaCepResponse> resultados = new ArrayList<>();
        for (String cep : ceps) {
            resultados.add(viaCepService.getViaCepInfo(cep));
        }
        return resultados;
    }
}

