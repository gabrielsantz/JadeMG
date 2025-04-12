package com.gabrielsantiago.jademg.controllers;

import com.gabrielsantiago.jademg.models.MoradorModel;
import com.gabrielsantiago.jademg.services.MoradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/moradores")
public class MoradorController {

    @Autowired
    private MoradorService moradorService;

    @GetMapping
    public ResponseEntity<List<MoradorModel>> getAll() {
        return ResponseEntity.ok(moradorService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MoradorModel> getById(@PathVariable Long id) {
        return moradorService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<MoradorModel> create(@RequestBody MoradorModel morador) {
        return ResponseEntity.ok(moradorService.save(morador));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MoradorModel> update(@PathVariable Long id, @RequestBody MoradorModel morador) {
        try {
            return ResponseEntity.ok(moradorService.update(id, morador));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        try {
            moradorService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
