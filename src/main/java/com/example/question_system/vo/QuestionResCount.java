package com.example.question_system.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class QuestionResCount {
	
	//回傳個別包的list
	private Integer headerNumber;
	private String questionHeader;
	private String questionType;
	private List<ChooseLast> chooseLastList;

	public QuestionResCount() {
	}

	public QuestionResCount(Integer headerNumber, String questionHeader, String questionType,
			List<ChooseLast> chooseLastList) {
		this.headerNumber = headerNumber;
		this.questionHeader = questionHeader;
		this.questionType = questionType;
		this.chooseLastList = chooseLastList;
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

	public List<ChooseLast> getChooseLastList() {
		return chooseLastList;
	}

	public void setChooseLastList(List<ChooseLast> chooseLastList) {
		this.chooseLastList = chooseLastList;
	}

}
