package com.example.question_system.vo;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChooseLast {
//放置每一個小題的選擇題的題目跟百分比

	private String answer;
	private double percentage;
	private int acount;
	private int lastTotle;

	public ChooseLast() {
	}

	public ChooseLast(String answer, double percentage, int acount, int lastTotle) {
		this.answer = answer;
		this.percentage = percentage;
		this.acount = acount;
		this.lastTotle = lastTotle;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public double getPercentage() {
		return percentage;
	}

	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}

	public int getAcount() {
		return acount;
	}

	public void setAcount(int acount) {
		this.acount = acount;
	}

	public int getLastTotle() {
		return lastTotle;
	}

	public void setLastTotle(int lastTotle) {
		this.lastTotle = lastTotle;
	}

}
