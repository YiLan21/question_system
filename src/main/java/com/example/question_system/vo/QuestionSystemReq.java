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
	// title編號
	private Integer questionNumber;
	// 大標題名稱
	private String questionTitle;
	// 大標題描述
	private String questionInfo;
	// 問券開始時間
	private LocalDate questionStarTime;
	// 問券結束時間
	private LocalDate questionEndTime;
	// 小題目內容 (原名稱 : headerType)
	private String questionType;
	// 問券狀態: 目前從方法裡面判定 沒有實質放在資料庫中
	private String questionState;
	// 問題建立時間
	private LocalDate questionCreatetime;
	// 問題題號
	private Integer headerNumber;
	// 題目
	private String questionHeader;
	// 題目選項(用";"隔開)
	private String headerChoose;
	// 回覆內容
	private String reply;
	// 使用者資訊 (名稱)
	private String userName;
	// 使用者資訊 (電話)
	private String userPhone;
	// 使用者資訊 (信箱)
	private String userEmail;
	// 使用者資訊 (年紀)
	private Integer userAge;
	// 使用者資訊 (性別)
	private String userGender;
	// 使用者資校 (使用者id --> 用AI生成的 )
	private Integer answerAutoId;
	// 使用者id
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
