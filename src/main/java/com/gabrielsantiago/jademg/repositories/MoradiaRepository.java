package com.gabrielsantiago.jademg.repositories;

import com.gabrielsantiago.jademg.models.MoradiaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoradiaRepository extends JpaRepository<MoradiaModel, Long> {
}
