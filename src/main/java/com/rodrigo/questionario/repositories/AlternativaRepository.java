package com.rodrigo.questionario.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rodrigo.questionario.entities.Alternativa;

@Repository
public interface AlternativaRepository extends JpaRepository<Alternativa, Long> {

}
