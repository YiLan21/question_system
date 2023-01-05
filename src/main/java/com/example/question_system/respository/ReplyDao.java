package com.example.question_system.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.question_system.entity.Reply;

@Repository
public interface ReplyDao extends JpaRepository<Reply, Integer> {
	public List<Reply> findAllByQuestionNumberAndHeaderNumberAndQuestionType(Integer questionNumber,
			Integer headerNumber, String questionType);

	public List<Reply> findByQuestionNumber(Integer questionNumber);

	public List<Reply> findByQuestionNumberAndHeaderNumber(Integer questionNumber, Integer headerNumber);

}
