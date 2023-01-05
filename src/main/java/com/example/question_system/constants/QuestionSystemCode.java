package com.example.question_system.constants;

public enum QuestionSystemCode {

	CREATE_SUCCESSFUL("200", "新增成功"),
	QUESTION_EXSIT("400", "已建立此問題"),
	QUESTION_ISNOT_EXSIT("400", "此問題尚未建立"),
	DATE_FAIL("400", "日期輸入錯誤"),
	HEADER_NUMBER_FAIL("400", "題號已經重複囉!"),
	CREATE_FAIL("400", "建立失敗");

	private String code;

	private String message;

	private QuestionSystemCode(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

}
