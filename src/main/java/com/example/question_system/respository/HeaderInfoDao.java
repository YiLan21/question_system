package com.example.question_system.respository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.question_system.entity.HeaderInfo;
import com.example.question_system.entity.Reply;

@Repository
public interface HeaderInfoDao extends JpaRepository<HeaderInfo, Integer> {

	public List<HeaderInfo> findByQuestionNumber(Integer questionNumber);

	public Optional<HeaderInfo> findByQuestionNumberAndHeaderNumber(Integer questionNumber, Integer headerNumber);

	public List<HeaderInfo> findByQuestionNumberAndHeaderNumberAndQuestionType(Integer questionNumber,
			Integer headerNumber, String questionType);

	public List<HeaderInfo> findAllByQuestionNumberAndHeaderNumber(Integer questionNumber, Integer headerNumber);

}
