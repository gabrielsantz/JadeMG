package com.gabrielsantiago.jademg.repositories;

import com.gabrielsantiago.jademg.models.PortariaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PortariaRepository extends JpaRepository<PortariaModel, Long> {
    Optional<PortariaModel> findByUsername(String username);
    boolean existsByNome(String nome);
}
