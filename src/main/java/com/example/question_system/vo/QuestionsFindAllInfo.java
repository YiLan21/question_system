package com.example.question_system.vo;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class QuestionsFindAllInfo {

	private Integer questionNumber;
	private String questionTitle;
	private String questionInfo;
	private LocalDate questionStarTime;
	private LocalDate questionEndTime;
	private String message;

	public QuestionsFindAllInfo() {

	}

	public QuestionsFindAllInfo(Integer questionNumber, String questionTitle, String questionInfo,
			LocalDate questionStarTime, LocalDate questionEndTime, String message) {
		this.questionNumber = questionNumber;
		this.questionTitle = questionTitle;
		this.questionInfo = questionInfo;
		this.questionStarTime = questionStarTime;
		this.questionEndTime = questionEndTime;
		this.message = message;
	}

	public Integer getQuestionNumber() {
		return questionNumber;
	}

	public void setQuestionNumber(Integer questionNumber) {
		this.questionNumber = questionNumber;
	}

	public String getQuestionTitle() {
		return questionTitle;
	}

	public void setQuestionTitle(String questionTitle) {
		this.questionTitle = questionTitle;
	}

	public String getQuestionInfo() {
		return questionInfo;
	}

	public void setQuestionInfo(String questionInfo) {
		this.questionInfo = questionInfo;
	}

	public LocalDate getQuestionStarTime() {
		return questionStarTime;
	}

	public void setQuestionStarTime(LocalDate questionStarTime) {
		this.questionStarTime = questionStarTime;
	}

	public LocalDate getQuestionEndTime() {
		return questionEndTime;
	}

	public void setQuestionEndTime(LocalDate questionEndTime) {
		this.questionEndTime = questionEndTime;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
