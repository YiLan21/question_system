package com.example.question_system.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "user_info")
public class UserInfo {

	// �۰ʲ��ͤ�id
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "answer_auto_id")
	private Integer answerAutoId;

	// 12/29�s�W �^���D��(TITLE)�D��
	@Column(name = "question_number")
	private Integer questionNumber;

	// ��J�̩m�W
	@Column(name = "user_name")
	private String userName;

	// ��J�̹q��
	@Column(name = "user_phone")
	private String userPhone;

	// ��J�̫H�c
	@Column(name = "user_email")
	private String userEmail;

	// ��J�̦~��
	@Column(name = "user_age")
	private Integer userAge;

	// ��J�̩ʧO
	@Column(name = "user_gender")
	private String userGender;

	// 12/29�s�W �إ߮ɶ�
	@Column(name = "answer_createtime")
	private LocalDate answerCreatetime;

	public UserInfo() {

	}

	public UserInfo(Integer answerAutoId, Integer questionNumber, String userName, String userPhone, String userEmail,
			Integer userAge, String userGender, LocalDate answerCreatetime) {
		this.answerAutoId = answerAutoId;
		this.questionNumber = questionNumber;
		this.userName = userName;
		this.userPhone = userPhone;
		this.userEmail = userEmail;
		this.userAge = userAge;
		this.userGender = userGender;
		this.answerCreatetime = answerCreatetime;
	}

	public Integer getAnswerAutoId() {
		return answerAutoId;
	}

	public void setAnswerAutoId(Integer answerAutoId) {
		this.answerAutoId = answerAutoId;
	}

	public Integer getQuestionNumber() {
		return questionNumber;
	}

	public void setQuestionNumber(Integer questionNumber) {
		this.questionNumber = questionNumber;
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

	public LocalDate getAnswerCreatetime() {
		return answerCreatetime;
	}

	public void setAnswerCreatetime(LocalDate answerCreatetime) {
		this.answerCreatetime = answerCreatetime;
	}

}
