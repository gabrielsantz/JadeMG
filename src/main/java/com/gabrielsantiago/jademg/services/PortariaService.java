package com.gabrielsantiago.jademg.services;

import com.gabrielsantiago.jademg.models.PortariaModel;
import com.gabrielsantiago.jademg.repositories.PortariaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PortariaService {

    @Autowired
    private PortariaRepository portariaRepository;

    public List<PortariaModel> findAll() {
        return portariaRepository.findAll();
    }

    public Optional<PortariaModel> findById(Long id) {
        return portariaRepository.findById(id);
    }

    @Transactional
    public PortariaModel save(PortariaModel portaria) {
        if (portariaRepository.existsByNome(portaria.getNome())) {
            throw new RuntimeException("Já existe uma portaria cadastrada com este nome");
        }
        return portariaRepository.save(portaria);
    }

    @Transactional
    public PortariaModel update(Long id, PortariaModel portaria) {
        if (!portariaRepository.existsById(id)) {
            throw new RuntimeException("Portaria não encontrada");
        }
        
        Optional<PortariaModel> existingPortaria = portariaRepository.findById(id);
        if (existingPortaria.isPresent()) {
            PortariaModel current = existingPortaria.get();
            if (!current.getNome().equals(portaria.getNome()) && 
                portariaRepository.existsByNome(portaria.getNome())) {
                throw new RuntimeException("Já existe uma portaria cadastrada com este nome");
            }
        }
        
        portaria.setId(id);
        return portariaRepository.save(portaria);
    }

    @Transactional
    public void deleteById(Long id) {
        if (!portariaRepository.existsById(id)) {
            throw new RuntimeException("Portaria não encontrada");
        }
        portariaRepository.deleteById(id);
    }
} 