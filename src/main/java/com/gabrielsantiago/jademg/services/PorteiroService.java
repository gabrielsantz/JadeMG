package com.gabrielsantiago.jademg.services;

import com.gabrielsantiago.jademg.models.PorteiroModel;
import com.gabrielsantiago.jademg.repositories.PorteiroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PorteiroService {

    @Autowired
    private PorteiroRepository porteiroRepository;

    public List<PorteiroModel> findAll() {
        return porteiroRepository.findAll();
    }

    public Optional<PorteiroModel> findById(Long id) {
        return porteiroRepository.findById(id);
    }

    @Transactional
    public PorteiroModel save(PorteiroModel porteiro) {
        if (porteiroRepository.existsByCpf(porteiro.getCpf())) {
            throw new RuntimeException("Já existe um porteiro cadastrado com este CPF");
        }
        return porteiroRepository.save(porteiro);
    }

    @Transactional
    public PorteiroModel update(Long id, PorteiroModel porteiro) {
        if (!porteiroRepository.existsById(id)) {
            throw new RuntimeException("Porteiro não encontrado");
        }
        
        Optional<PorteiroModel> existingPorteiro = porteiroRepository.findById(id);
        if (existingPorteiro.isPresent()) {
            PorteiroModel current = existingPorteiro.get();
            if (!current.getCpf().equals(porteiro.getCpf()) && 
                porteiroRepository.existsByCpf(porteiro.getCpf())) {
                throw new RuntimeException("Já existe um porteiro cadastrado com este CPF");
            }
        }
        
        porteiro.setId(id);
        return porteiroRepository.save(porteiro);
    }

    @Transactional
    public void deleteById(Long id) {
        if (!porteiroRepository.existsById(id)) {
            throw new RuntimeException("Porteiro não encontrado");
        }
        porteiroRepository.deleteById(id);
    }
} 