package com.gabrielsantiago.jademg.models;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Getter
@Setter
@Entity
public class MoradiaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bloco;
    private String apartamento;

    @ManyToOne
    @JoinColumn(name = "portaria_id")
    @JsonBackReference("moradia-portaria")
    private PortariaModel portaria;

    @OneToMany(mappedBy = "moradia", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("morador-moradia")
    private List<MoradorModel> moradores;
}
