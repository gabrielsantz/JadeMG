package com.gabrielsantiago.jademg.controllers;

import com.gabrielsantiago.jademg.models.EntregaModel;
import com.gabrielsantiago.jademg.services.EntregaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/entregas")
public class EntregaController {

    @Autowired
    private EntregaService entregaService;

    @GetMapping
    public ResponseEntity<List<EntregaModel>> getAll() {
        return ResponseEntity.ok(entregaService.findAll());
    }

    @PostMapping
    public ResponseEntity<EntregaModel> create(@RequestBody EntregaModel entrega) {
        return ResponseEntity.ok(entregaService.save(entrega));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntregaModel> getById(@PathVariable Long id) {
        return entregaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntregaModel> update(@PathVariable Long id, @RequestBody EntregaModel entrega) {
        try {
            return ResponseEntity.ok(entregaService.update(id, entrega));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        try {
            entregaService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
} 