package com.example.question_system.vo;

import java.time.LocalDate;

import com.example.question_system.entity.UserInfo;
import com.example.question_system.entity.Question;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class QuestionSystemReq {
	// entity
	private UserInfo UserInfo;
	// entity
	private Question question;
	// title�s��
	private Integer questionNumber;
	// �j���D�W��
	private String questionTitle;
	// �j���D�y�z
	private String questionInfo;
	// �ݨ�}�l�ɶ�
	private LocalDate questionStarTime;
	// �ݨ鵲���ɶ�
	private LocalDate questionEndTime;
	// �p�D�ؤ��e (��W�� : headerType)
	private String questionType;
	// �ݨ骬�A: �ثe�q��k�̭��P�w �S������b��Ʈw��
	private String questionState;
	// ���D�إ߮ɶ�
	private LocalDate questionCreatetime;
	// ���D�D��
	private Integer headerNumber;
	// �D��
	private String questionHeader;
	// �D�ؿﶵ(��";"�j�})
	private String headerChoose;
	// �^�Ф��e
	private String reply;
	// �ϥΪ̸�T (�W��)
	private String userName;
	// �ϥΪ̸�T (�q��)
	private String userPhone;
	// �ϥΪ̸�T (�H�c)
	private String userEmail;
	// �ϥΪ̸�T (�~��)
	private Integer userAge;
	// �ϥΪ̸�T (�ʧO)
	private String userGender;
	// �ϥΪ̸�� (�ϥΪ�id --> ��AI�ͦ��� )
	private Integer answerAutoId;
	// �ϥΪ�id
	private Integer userId;
	/*-----------------------------------------*/

	public QuestionSystemReq() {

	}

	/*-----------------------------------------*/

	public UserInfo getAnswerInfo() {
		return UserInfo;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public UserInfo getUserInfo() {
		return UserInfo;
	}

	public void setUserInfo(UserInfo UserInfo) {
		this.UserInfo = UserInfo;
	}

	public Integer getAnswerAutoId() {
		return answerAutoId;
	}

	public void setAnswerAutoId(Integer answerAutoId) {
		this.answerAutoId = answerAutoId;
	}

	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
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

	public String getHeaderChoose() {
		return headerChoose;
	}

	public void setHeaderChoose(String headerChoose) {
		this.headerChoose = headerChoose;
	}

	public void setAnswerInfo(UserInfo UserInfo) {
		this.UserInfo = UserInfo;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
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

	public String getQuestionType() {
		return questionType;
	}

	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}

	public String getQuestionState() {
		return questionState;
	}

	public void setQuestionState(String questionState) {
		this.questionState = questionState;
	}

	public LocalDate getQuestionCreatetime() {
		return questionCreatetime;
	}

	public void setQuestionCreatetime(LocalDate questionCreatetime) {
		this.questionCreatetime = questionCreatetime;
	}

}
