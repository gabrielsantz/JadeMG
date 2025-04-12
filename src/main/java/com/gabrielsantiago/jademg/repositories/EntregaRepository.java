package com.gabrielsantiago.jademg.repositories;

import com.gabrielsantiago.jademg.models.EntregaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntregaRepository extends JpaRepository<EntregaModel, Long> {
} 