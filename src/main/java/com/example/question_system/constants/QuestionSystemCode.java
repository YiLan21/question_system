package com.example.question_system.constants;

public enum QuestionSystemCode {

	CREATE_SUCCESSFUL("200", "�s�W���\"),
	QUESTION_EXSIT("400", "�w�إߦ����D"),
	QUESTION_ISNOT_EXSIT("400", "�����D�|���إ�"),
	DATE_FAIL("400", "�����J���~"),
	HEADER_NUMBER_FAIL("400", "�D���w�g�����o!"),
	CREATE_FAIL("400", "�إߥ���");

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
