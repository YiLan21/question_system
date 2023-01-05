package com.example.question_system.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class QuestionsResInfo {

	private Integer headerNumber;
	private String questionHeader;
	private String questionType;
	private List<String> headerChooseList;

	public QuestionsResInfo() {
	}

	public QuestionsResInfo(Integer headerNumber, String questionHeader, String questionType,List<String> headerChooseList) {
		this.headerNumber = headerNumber;
		this.questionHeader = questionHeader;
		this.questionType = questionType;
		this.headerChooseList = headerChooseList;
	}

	
	public List<String> getHeaderChooseList() {
		return headerChooseList;
	}

	public void setHeaderChooseList(List<String> headerChooseList) {
		this.headerChooseList = headerChooseList;
	}

	public Integer getHeaderNumber() {
		return headerNumber;
	}

	public void setHeaderNumber(Integer headerNumber) {
		this.headerNumber = headerNumber;
	}

	public String getQuestionHeader() {
		return questionHeader;
	}

	public void setQuestionHeader(String questionHeader) {
		this.questionHeader = questionHeader;
	}

	public String getQuestionType() {
		return questionType;
	}

	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}

}
