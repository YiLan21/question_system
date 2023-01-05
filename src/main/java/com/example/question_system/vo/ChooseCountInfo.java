package com.example.question_system.vo;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChooseCountInfo {

	private Integer questionNumber;
	private Integer headerNumber;
	private String type;
	private String apoint;
	private String bpoint;
	private String cpoint;
	private String dpoint;
	private String epoint;

//	// 另外回復的部分
	private String answer;
	private int acount;
	private double percentage;
	private int totle;
	private String questionHeader;

	public ChooseCountInfo() {

	}

	public ChooseCountInfo(Integer questionNumber, Integer headerNumber, String type, String apoint, String bpoint,
			String cpoint, String dpoint, String epoint) {
		this.questionNumber = questionNumber;
		this.headerNumber = headerNumber;
		this.type = type;
		this.apoint = apoint;
		this.bpoint = bpoint;
		this.cpoint = cpoint;
		this.dpoint = dpoint;
		this.epoint = epoint;
	}

	public ChooseCountInfo(Integer questionNumber, Integer headerNumber, String type, String answer, int acount,
			double percentage, int totle) {
		this.questionNumber = questionNumber;
		this.headerNumber = headerNumber;
		this.type = type;
		this.answer = answer;
		this.acount = acount;
		this.percentage = percentage;
		this.totle = totle;
	}

	// 20230104 新增
	// questionNumber(題目),headerNumber(小題編號),questionHeader(小題題目),reply(回覆)
	//
	public ChooseCountInfo(Integer questionNumber, Integer headerNumber, String questionHeader, String type,
			String answer, int acount, double percentage, int totle) {
		this.questionNumber = questionNumber;
		this.headerNumber = headerNumber;
		this.questionHeader = questionHeader;
		this.type = type;
		this.answer = answer;
		this.acount = acount;
		this.percentage = percentage;
		this.totle = totle;
	}

	public String getQuestionHeader() {
		return questionHeader;
	}

	public void setQuestionHeader(String questionHeader) {
		this.questionHeader = questionHeader;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public int getAcount() {
		return acount;
	}

	public void setAcount(int acount) {
		this.acount = acount;
	}

	public double getPercentage() {
		return percentage;
	}

	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}

	public int getTotle() {
		return totle;
	}

	public void setTotle(int totle) {
		this.totle = totle;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getApoint() {
		return apoint;
	}

	public void setApoint(String apoint) {
		this.apoint = apoint;
	}

	public String getBpoint() {
		return bpoint;
	}

	public void setBpoint(String bpoint) {
		this.bpoint = bpoint;
	}

	public String getCpoint() {
		return cpoint;
	}

	public void setCpoint(String cpoint) {
		this.cpoint = cpoint;
	}

	public String getDpoint() {
		return dpoint;
	}

	public void setDpoint(String dpoint) {
		this.dpoint = dpoint;
	}

	public String getEpoint() {
		return epoint;
	}

	public void setEpoint(String epoint) {
		this.epoint = epoint;
	}

}
