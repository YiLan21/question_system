package com.example.question_system.vo;

import java.util.List;

import com.example.question_system.entity.UserInfo;
import com.example.question_system.entity.HeaderInfo;
import com.example.question_system.entity.Question;
import com.example.question_system.entity.Reply;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class QuestionSystemRes {

	private Question question;

	private UserInfo UserInfo;

	private Reply reply;

	private HeaderInfo headerInfo;

	private ChooseCountInfo chooseCountInfo;

	private String message;

	private String state;

	private List<Question> questionList;

	private List<QuestionsResInfo> questionsResInfo;

	private List<ChooseCountInfo> chooseCountInfoList;

	private List<QuestionResCount> questionResCountList;

	private List<QuestionsFindAllInfo> questionsFindAllInfo;

	/*-----------------------------------------------------------------*/

	public QuestionSystemRes() {

	}

	public QuestionSystemRes(Question question, List<ChooseCountInfo> chooseCountInfoList) {
		this.question = question;
		this.chooseCountInfoList = chooseCountInfoList;
	}

	public QuestionSystemRes(Question question, List<QuestionResCount> questionResCountList, String state) {
		this.question = question;
		this.questionResCountList = questionResCountList;
		this.state = state;
	}

	public QuestionSystemRes(ChooseCountInfo chooseCountInfo) {
		this.chooseCountInfo = chooseCountInfo;
	}

	public QuestionSystemRes(Question question, String message) {
		this.question = question;
		this.message = message;
	}

	public QuestionSystemRes(HeaderInfo headerInfo) {
		this.headerInfo = headerInfo;
	}

	public QuestionSystemRes(Question question, String state, List<QuestionsResInfo> questionsResInfo) {
		this.question = question;
		this.state = state;
		this.questionsResInfo = questionsResInfo;
	}

	// 回傳首頁訊息 ，寫入state
	public QuestionSystemRes(List<QuestionsFindAllInfo> questionsFindAllInfo) {
		this.questionsFindAllInfo = questionsFindAllInfo;
	}

	public QuestionSystemRes(String message) {
		this.message = message;
	}

	public QuestionSystemRes(Reply reply) {
		this.reply = reply;
	}

	/*-----------------------------------------------------------------*/

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public UserInfo getUserInfo() {
		return UserInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		UserInfo = userInfo;
	}

	public Reply getReply() {
		return reply;
	}

	public void setReply(Reply reply) {
		this.reply = reply;
	}

	public HeaderInfo getHeaderInfo() {
		return headerInfo;
	}

	public void setHeaderInfo(HeaderInfo headerInfo) {
		this.headerInfo = headerInfo;
	}

	public ChooseCountInfo getChooseCountInfo() {
		return chooseCountInfo;
	}

	public void setChooseCountInfo(ChooseCountInfo chooseCountInfo) {
		this.chooseCountInfo = chooseCountInfo;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<Question> getQuestionList() {
		return questionList;
	}

	public void setQuestionList(List<Question> questionList) {
		this.questionList = questionList;
	}

	public List<QuestionsResInfo> getQuestionsResInfo() {
		return questionsResInfo;
	}

	public void setQuestionsResInfo(List<QuestionsResInfo> questionsResInfo) {
		this.questionsResInfo = questionsResInfo;
	}

	public List<ChooseCountInfo> getChooseCountInfoList() {
		return chooseCountInfoList;
	}

	public void setChooseCountInfoList(List<ChooseCountInfo> chooseCountInfoList) {
		this.chooseCountInfoList = chooseCountInfoList;
	}

	public List<QuestionResCount> getQuestionResCountList() {
		return questionResCountList;
	}

	public void setQuestionResCountList(List<QuestionResCount> questionResCountList) {
		this.questionResCountList = questionResCountList;
	}

	public List<QuestionsFindAllInfo> getQuestionsFindAllInfo() {
		return questionsFindAllInfo;
	}

	public void setQuestionsFindAllInfo(List<QuestionsFindAllInfo> questionsFindAllInfo) {
		this.questionsFindAllInfo = questionsFindAllInfo;
	}

}
