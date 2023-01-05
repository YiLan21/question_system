package com.example.question_system.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "reply")
public class Reply {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "auto_id")
	private Integer autoId;

	// 問題編馬
	@Column(name = "question_number")
	private Integer questionNumber;

	// 第幾題
	@Column(name = "header_number")
	private Integer headerNumber;

	// 回答
	@Column(name = "reply")
	private String reply;

	// 回答類型
	@Column(name = "question_type")
	private String questionType;

	// 輸入者姓名
	@Column(name = "user_name")
	private String userName;

	// 輸入者電話
	@Column(name = "user_phone")
	private String userPhone;

	// 輸入者信箱
	@Column(name = "user_email")
	private String userEmail;

	// 輸入者年齡
	@Column(name = "user_age")
	private Integer userAge;

	// 輸入者性別
	@Column(name = "user_gender")
	private String userGender;

	@Column(name = "answer_auto_id")
	private Integer answerAutoId;

	public Reply() {

	}

	public Reply(Integer questionNumber, Integer headerNumber, String reply, String questionType, String userName,
			String userPhone, String userEmail, Integer userAge, String userGender, Integer answerAutoId) {
		this.questionNumber = questionNumber;
		this.headerNumber = headerNumber;
		this.reply = reply;
		this.questionType = questionType;
		this.userName = userName;
		this.userPhone = userPhone;
		this.userEmail = userEmail;
		this.userAge = userAge;
		this.userGender = userGender;
		this.answerAutoId = answerAutoId;
	}

	public Reply(Integer questionNumber, Integer headerNumber, String reply, String questionType, String userName,
			String userPhone, String userEmail, Integer userAge, String userGender) {
		this.questionNumber = questionNumber;
		this.headerNumber = headerNumber;
		this.reply = reply;
		this.questionType = questionType;
		this.userName = userName;
		this.userPhone = userPhone;
		this.userEmail = userEmail;
		this.userAge = userAge;
		this.userGender = userGender;
	}

	public Integer getAnswerAutoId() {
		return answerAutoId;
	}

	public void setAnswerAutoId(Integer answerAutoId) {
		this.answerAutoId = answerAutoId;
	}

	public Integer getAutoId() {
		return autoId;
	}

	public void setAutoId(Integer autoId) {
		this.autoId = autoId;
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

	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}

	public String getQuestionType() {
		return questionType;
	}

	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public Integer getUserAge() {
		return userAge;
	}

	public void setUserAge(Integer userAge) {
		this.userAge = userAge;
	}

	public String getUserGender() {
		return userGender;
	}

	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}

}
