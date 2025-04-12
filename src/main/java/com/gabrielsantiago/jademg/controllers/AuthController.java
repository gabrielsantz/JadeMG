package com.gabrielsantiago.jademg.controllers;

import com.gabrielsantiago.jademg.dtos.AuthRequest;
import com.gabrielsantiago.jademg.dtos.AuthResponse;
import com.gabrielsantiago.jademg.models.PortariaModel;
import com.gabrielsantiago.jademg.repositories.PortariaRepository;
import com.gabrielsantiago.jademg.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private PortariaRepository portariaRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder encoder;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody PortariaModel portaria) {
        portaria.setPassword(encoder.encode(portaria.getPassword()));
        portariaRepository.save(portaria);
        return ResponseEntity.ok("Portaria registrada com sucesso");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest auth) {
        PortariaModel portaria = portariaRepository.findByUsername(auth.getUsername()).orElse(null);

        if (portaria == null || !encoder.matches(auth.getPassword(), portaria.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas");
        }

        String token = jwtUtil.generateToken(portaria.getUsername());
        return ResponseEntity.ok(new AuthResponse(token));
    }
}