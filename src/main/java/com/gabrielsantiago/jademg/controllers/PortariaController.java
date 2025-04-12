package com.gabrielsantiago.jademg.controllers;

import com.gabrielsantiago.jademg.models.PortariaModel;
import com.gabrielsantiago.jademg.services.PortariaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/portarias")
public class PortariaController {

    @Autowired
    private PortariaService portariaService;

    @GetMapping
    public ResponseEntity<List<PortariaModel>> getAll() {
        return ResponseEntity.ok(portariaService.findAll());
    }

    @PostMapping
    public ResponseEntity<PortariaModel> create(@RequestBody PortariaModel portaria) {
        return ResponseEntity.ok(portariaService.save(portaria));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PortariaModel> getById(@PathVariable Long id) {
        return portariaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<PortariaModel> update(@PathVariable Long id, @RequestBody PortariaModel portaria) {
        try {
            return ResponseEntity.ok(portariaService.update(id, portaria));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        try {
            portariaService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
