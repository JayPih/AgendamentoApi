package com.example.agendamentoapp.service;

import com.example.agendamentoapp.client.ViaCepClient;
import com.example.agendamentoapp.client.ViaCepResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ViaCepService {

    private final ViaCepClient viaCepClient;

    public ViaCepService(ViaCepClient viaCepClient) {
        this.viaCepClient = viaCepClient;
    }

    public ViaCepResponse getViaCepInfo(String cep) {
        return viaCepClient.getViaCepInfo(cep);
    }
}

