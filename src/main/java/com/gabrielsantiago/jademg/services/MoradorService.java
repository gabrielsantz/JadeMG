package com.gabrielsantiago.jademg.services;

import com.gabrielsantiago.jademg.models.MoradorModel;
import com.gabrielsantiago.jademg.repositories.MoradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MoradorService {

    @Autowired
    private MoradorRepository moradorRepository;

    public List<MoradorModel> findAll() {
        return moradorRepository.findAll();
    }

    public Optional<MoradorModel> findById(Long id) {
        return moradorRepository.findById(id);
    }

    @Transactional
    public MoradorModel save(MoradorModel morador) {
        if (moradorRepository.existsByCpf(morador.getCpf())) {
            throw new RuntimeException("Já existe um morador cadastrado com este CPF");
        }
        return moradorRepository.save(morador);
    }

    @Transactional
    public MoradorModel update(Long id, MoradorModel morador) {
        if (!moradorRepository.existsById(id)) {
            throw new RuntimeException("Morador não encontrado");
        }
        
        Optional<MoradorModel> existingMorador = moradorRepository.findById(id);
        if (existingMorador.isPresent()) {
            MoradorModel current = existingMorador.get();
            if (!current.getCpf().equals(morador.getCpf()) && 
                moradorRepository.existsByCpf(morador.getCpf())) {
                throw new RuntimeException("Já existe um morador cadastrado com este CPF");
            }
        }
        
        morador.setId(id);
        return moradorRepository.save(morador);
    }

    @Transactional
    public void deleteById(Long id) {
        if (!moradorRepository.existsById(id)) {
            throw new RuntimeException("Morador não encontrado");
        }
        moradorRepository.deleteById(id);
    }
} 