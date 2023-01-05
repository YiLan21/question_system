package com.example.question_system.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.question_system.ifs.QuestionSystemService;
import com.example.question_system.vo.QuestionSystemReq;
import com.example.question_system.vo.QuestionSystemRes;

@CrossOrigin
@RestController
public class QuestionSystemController {

	@Autowired
	private QuestionSystemService questionSystemService;

	// 1.�إ߰��D (title)
	@PostMapping(value = "api/create_question")
	public QuestionSystemRes createQuestion(@RequestBody QuestionSystemReq req) {

		return questionSystemService.createQuestion(req.getQuestionNumber(), req.getQuestionTitle(),
				req.getQuestionInfo(), req.getQuestionStarTime(), req.getQuestionEndTime());
	}

	// 2.�ק���D���
	@PostMapping(value = "api/update_question")
	public QuestionSystemRes updateQuestion(@RequestBody QuestionSystemReq req) {

		return questionSystemService.updateQuestion(req.getQuestionNumber(), req.getQuestionTitle(),
				req.getQuestionInfo(), req.getQuestionStarTime(), req.getQuestionEndTime(),
				req.getQuestionCreatetime());
	}

	// 3.�إ߰��D�D�� (���D�s�X,�D�ؽs�X,�D�ؤ��e,�D������,�D�ؿ��)
	@PostMapping(value = "api/create_header")
	public QuestionSystemRes createHeader(@RequestBody QuestionSystemReq req) {

		return questionSystemService.createHeader(req.getQuestionNumber(), req.getHeaderNumber(),
				req.getQuestionHeader(), req.getQuestionType(), req.getHeaderChoose());
	}

	// 4.�ק��D�ؤ��e (���D�s�X,�D�ؽs�X,�D�ؤ��e,�D������,�D�ؿ��)
	@PostMapping(value = "api/update_header")
	public QuestionSystemRes updateHeader(@RequestBody QuestionSystemReq req) {

		return questionSystemService.updateHeader(req.getQuestionNumber(), req.getHeaderNumber(),
				req.getQuestionHeader(), req.getQuestionType(), req.getHeaderChoose());
	}

	// 5.�j�M�D�� + ���e (�� QuestionNumber )
	@PostMapping(value = "api/getQuestionAndHeader")
	public QuestionSystemRes getQuestionAndHeader(@RequestBody QuestionSystemReq req) {

		return questionSystemService.getQuestionAndHeader(req.getQuestionNumber());
	}

	// 6.��ܩҦ����D(��)
	@PostMapping(value = "api/getAllQuestions")
	public QuestionSystemRes getAllQuestions() {
		return questionSystemService.getAllQuestions();
	}

	// 7.�j�M�ŦX���ت����D
	@PostMapping(value = "api/getQuestionsByTitleOrDate")
	public QuestionSystemRes getQuestionsByTitleOrDate(@RequestBody QuestionSystemReq req) {
		return questionSystemService.getQuestionsByTitleOrDate(req.getQuestionTitle(), req.getQuestionStarTime(),
				req.getQuestionEndTime());
	}

	// 8.�Ыص��׳�
	@PostMapping(value = "api/createAnswer")
	public QuestionSystemRes createAnswer(@RequestBody QuestionSystemReq req) {
		return questionSystemService.createAnswer(req.getQuestionNumber(), req.getReply(), req.getUserName(),
				req.getUserPhone(), req.getUserEmail(), req.getUserAge(), req.getUserGender());
	}

	// 9.��ܥX�^�� (��v)
	@PostMapping(value = "api/getAnswerCount")
	public QuestionSystemRes getAnswerCount(@RequestBody QuestionSystemReq req) {
		return questionSystemService.getAnswerCount(req.getQuestionNumber(), req.getHeaderNumber(),
				req.getQuestionType());
	}

	// 10.20230104�s�W �� �p��έp��api
	@PostMapping(value = "api/getHappyNewYearCount")
	public QuestionSystemRes getHappyNewYearCount(@RequestBody QuestionSystemReq req) {
		return questionSystemService.getHappyNewYearCount(req.getQuestionNumber());
	}

}