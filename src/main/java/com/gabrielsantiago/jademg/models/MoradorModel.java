package com.gabrielsantiago.jademg.models;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Getter
@Setter
@Entity
public class MoradorModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String cpf;
    private String telefone;

    @ManyToOne
    @JoinColumn(name = "moradia_id")
    @JsonBackReference("morador-moradia")
    private MoradiaModel moradia;

    @ManyToOne
    @JoinColumn(name = "portaria_id")
    @JsonBackReference("morador-portaria")
    private PortariaModel portaria;
}
