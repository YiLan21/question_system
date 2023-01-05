package com.example.question_system.respository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.question_system.entity.Question;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer> {

	public Optional<Question> findAllByQuestionNumber(Integer questionNumber);

	public List<Question>findAll();
	
}
