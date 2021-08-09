package com.rodrigo.questionario.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rodrigo.questionario.entities.Questionario;

@Repository
public interface QuestionarioRepository extends JpaRepository<Questionario, Long> {

}
