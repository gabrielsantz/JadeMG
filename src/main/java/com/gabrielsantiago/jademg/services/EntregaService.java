package com.gabrielsantiago.jademg.services;

import com.gabrielsantiago.jademg.models.EntregaModel;
import com.gabrielsantiago.jademg.repositories.EntregaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EntregaService {

    @Autowired
    private EntregaRepository entregaRepository;

    public List<EntregaModel> findAll() {
        return entregaRepository.findAll();
    }

    public Optional<EntregaModel> findById(Long id) {
        return entregaRepository.findById(id);
    }

    @Transactional
    public EntregaModel save(EntregaModel entrega) {
        return entregaRepository.save(entrega);
    }

    @Transactional
    public EntregaModel update(Long id, EntregaModel entrega) {
        if (!entregaRepository.existsById(id)) {
            throw new RuntimeException("Entrega não encontrada");
        }
        
        entrega.setId(id);
        return entregaRepository.save(entrega);
    }

    @Transactional
    public void deleteById(Long id) {
        if (!entregaRepository.existsById(id)) {
            throw new RuntimeException("Entrega não encontrada");
        }
        entregaRepository.deleteById(id);
    }
} 