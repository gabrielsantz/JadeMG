package com.gabrielsantiago.jademg.repositories;

import com.gabrielsantiago.jademg.models.MoradorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoradorRepository extends JpaRepository<MoradorModel, Long> {
    boolean existsByCpf(String cpf);
}
