package com.gabrielsantiago.jademg.repositories;

import com.gabrielsantiago.jademg.models.PorteiroModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PorteiroRepository extends JpaRepository<PorteiroModel, Long> {
    boolean existsByCpf(String cpf);
} 