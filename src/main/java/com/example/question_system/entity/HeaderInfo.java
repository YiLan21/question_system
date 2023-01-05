package com.example.question_system.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "header_info")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HeaderInfo {

	// 自動產生之id
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ai_id")
	private Integer aiId;

	// 問題編碼
	@Column(name = "question_number")
	private Integer questionNumber;

	// 題目編碼
	@Column(name = "header_number")
	private Integer headerNumber;

	// 題目內容
	@Column(name = "question_header")
	private String questionHeader;

	// 題目類型
	@Column(name = "question_type")
	private String questionType;

	// 題目選擇
	@Column(name = "header_choose")
	private String headerChoose;

	public HeaderInfo() {

	}

	public HeaderInfo(Integer questionNumber, Integer headerNumber, String questionHeader, String questionType,
			String headerChoose) {

		this.questionNumber = questionNumber;
		this.headerNumber = headerNumber;
		this.questionHeader = questionHeader;
		this.questionType = questionType;
		this.headerChoose = headerChoose;
	}

	public HeaderInfo(String questionHeader, String questionType, String headerChoose) {
		this.questionHeader = questionHeader;
		this.questionType = questionType;
		this.headerChoose = headerChoose;
	}

	public Integer getAiId() {
		return aiId;
	}

	public void setAiId(Integer aiId) {
		this.aiId = aiId;
	}

	public Integer getQuestionNumber() {
		return questionNumber;
	}

	public void setQuestionNumber(Integer questionNumber) {
		this.questionNumber = questionNumber;
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

	public String getHeaderChoose() {
		return headerChoose;
	}

	public void setHeaderChoose(String headerChoose) {
		this.headerChoose = headerChoose;
	}

}
