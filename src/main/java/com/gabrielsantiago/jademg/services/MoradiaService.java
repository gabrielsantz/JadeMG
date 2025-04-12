package com.gabrielsantiago.jademg.services;

import com.gabrielsantiago.jademg.models.MoradiaModel;
import com.gabrielsantiago.jademg.repositories.MoradiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MoradiaService {

    @Autowired
    private MoradiaRepository moradiaRepository;

    public List<MoradiaModel> findAll() {
        return moradiaRepository.findAll();
    }

    public Optional<MoradiaModel> findById(Long id) {
        return moradiaRepository.findById(id);
    }

    @Transactional
    public MoradiaModel save(MoradiaModel moradia) {
        return moradiaRepository.save(moradia);
    }

    @Transactional
    public MoradiaModel update(Long id, MoradiaModel moradia) {
        if (!moradiaRepository.existsById(id)) {
            throw new RuntimeException("Moradia não encontrada");
        }
        
        moradia.setId(id);
        return moradiaRepository.save(moradia);
    }

    @Transactional
    public void deleteById(Long id) {
        if (!moradiaRepository.existsById(id)) {
            throw new RuntimeException("Moradia não encontrada");
        }
        moradiaRepository.deleteById(id);
    }
} 