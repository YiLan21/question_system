package com.example.question_system.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "question")
public class Question {

	// �ݨ��s��
	@Id
	@Column(name = "question_number")
	private Integer questionNumber;

	// �ݨ��}�Y
	@Column(name = "question_title")
	private String questionTitle;

	// �ݨ����e
	@Column(name = "question_info")
	private String questionInfo;

	// �ݨ��}�l�ɶ�
	@Column(name = "question_star_time")
	private LocalDate questionStarTime;

	// �ݨ鵲���ɶ�
	@Column(name = "question_end_time")
	private LocalDate questionEndTime;

	// �ݨ�إ߮ɶ�
	@Column(name = "question_createtime")
	private LocalDate questionCreatetime;

	// �ݨ骬�A
	@Column(name = "question_updatetime")
	private LocalDate questionUpdatetime;

	public Question() {

	}

	public Question(Integer questionNumber, String questionTitle, String questionInfo, LocalDate questionStarTime,
			LocalDate questionEndTime, LocalDate questionCreatetime) {
		this.questionNumber = questionNumber;
		this.questionTitle = questionTitle;
		this.questionInfo = questionInfo;
		this.questionStarTime = questionStarTime;
		this.questionEndTime = questionEndTime;
		this.questionCreatetime = questionCreatetime;
	}

	public Question(Integer questionNumber, String questionTitle, String questionInfo, LocalDate questionStarTime,
			LocalDate questionEndTime, LocalDate questionCreatetime, LocalDate questionUpdatetime) {
		this.questionNumber = questionNumber;
		this.questionTitle = questionTitle;
		this.questionInfo = questionInfo;
		this.questionStarTime = questionStarTime;
		this.questionEndTime = questionEndTime;
		this.questionCreatetime = questionCreatetime;
		this.questionUpdatetime = questionUpdatetime;
	}

	public LocalDate getQuestionUpdatetime() {
		return questionUpdatetime;
	}

	public void setQuestionUpdatetime(LocalDate questionUpdatetime) {
		this.questionUpdatetime = questionUpdatetime;
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

	public LocalDate getQuestionCreatetime() {
		return questionCreatetime;
	}

	public void setQuestionCreatetime(LocalDate questionCreatetime) {
		this.questionCreatetime = questionCreatetime;
	}

}
