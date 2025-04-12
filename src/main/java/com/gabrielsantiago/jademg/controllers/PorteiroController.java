package com.gabrielsantiago.jademg.controllers;

import com.gabrielsantiago.jademg.models.PorteiroModel;
import com.gabrielsantiago.jademg.services.PorteiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/porteiros")
public class PorteiroController {

    @Autowired
    private PorteiroService porteiroService;

    @GetMapping
    public ResponseEntity<List<PorteiroModel>> getAllPorteiros() {
        return ResponseEntity.ok(porteiroService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PorteiroModel> getPorteiroById(@PathVariable Long id) {
        return porteiroService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PorteiroModel> createPorteiro(@RequestBody PorteiroModel porteiro) {
        try {
            PorteiroModel savedPorteiro = porteiroService.save(porteiro);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedPorteiro);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<PorteiroModel> updatePorteiro(@PathVariable Long id, @RequestBody PorteiroModel porteiro) {
        try {
            PorteiroModel updatedPorteiro = porteiroService.update(id, porteiro);
            return ResponseEntity.ok(updatedPorteiro);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePorteiro(@PathVariable Long id) {
        try {
            porteiroService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
} 