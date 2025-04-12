package com.gabrielsantiago.jademg.controllers;

import com.gabrielsantiago.jademg.models.MoradiaModel;
import com.gabrielsantiago.jademg.services.MoradiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/moradias")
public class MoradiaController {

    @Autowired
    private MoradiaService moradiaService;

    @GetMapping
    public ResponseEntity<List<MoradiaModel>> getAll() {
        return ResponseEntity.ok(moradiaService.findAll());
    }

    @PostMapping
    public ResponseEntity<MoradiaModel> create(@RequestBody MoradiaModel moradia) {
        return ResponseEntity.ok(moradiaService.save(moradia));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MoradiaModel> getById(@PathVariable Long id) {
        return moradiaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<MoradiaModel> update(@PathVariable Long id, @RequestBody MoradiaModel moradia) {
        try {
            return ResponseEntity.ok(moradiaService.update(id, moradia));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        try {
            moradiaService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
