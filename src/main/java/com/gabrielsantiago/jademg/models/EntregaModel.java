package com.gabrielsantiago.jademg.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.CreationTimestamp;

@Getter
@Setter
@Entity
public class EntregaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;

    @CreationTimestamp
    private LocalDateTime dataHora;

    @ManyToOne
    @JoinColumn(name = "morador_id")
    @JsonBackReference("entrega-morador")
    private MoradorModel morador;

    @ManyToOne
    @JoinColumn(name = "portaria_id")
    @JsonBackReference("entrega-portaria")
    private PortariaModel portaria;

    private boolean entregue;
} 