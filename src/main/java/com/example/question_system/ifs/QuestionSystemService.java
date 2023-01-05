package com.example.question_system.ifs;

import java.time.LocalDate;

import com.example.question_system.vo.QuestionSystemRes;

public interface QuestionSystemService {

	// 1.�Ыذ��D���
	public QuestionSystemRes createQuestion(Integer questionNumber, String questionTitle, String questionInfo,
			LocalDate questionStarTime, LocalDate questionEndTime);

	// 2.�ק���D���
	public QuestionSystemRes updateQuestion(Integer questionNumber, String questionTitle, String questionInfo,
			LocalDate questionStarTime, LocalDate questionEndTime, LocalDate questionCreatetime);

	// 3.�إ߰��D�D�� (���D�s�X,�D�ؽs�X,�D�ؤ��e,�D������,�D�ؿ��)
	public QuestionSystemRes createHeader(Integer questionNumber, Integer headerNumber, String questionHeader,
			String headerType, String headerChoose);

	// 4.�ק��D�ؤ��e (���D�s�X,�D�ؽs�X,�D�ؤ��e,�D������,�D�ؿ��)
	public QuestionSystemRes updateHeader(Integer questionNumber, Integer headerNumber, String questionHeader,
			String headerType, String headerChoose);

	// 5.�j�M�D�� + ���e (�� QuestionNumber )
	public QuestionSystemRes getQuestionAndHeader(Integer questionNumber);

	// 6.��ܩҦ����D(��)
	public QuestionSystemRes getAllQuestions();

	// 7.�j�M�ŦX���ت����D
	// (��J�� String questionTitle,LocalDate questionStarTime, LocalDate
	// questionEndTime )
	public QuestionSystemRes getQuestionsByTitleOrDate(String questionTitle, LocalDate questionStarTime,
			LocalDate questionEndTime);

	// 8.�Ыص��׳�
	// ( ��J�� Integer questioNumber, Integer headerNumber, String reply,String
	// questionType, String userName,Integer userPhone, String userEmail, Integer
	// userAge, String userGender)
	public QuestionSystemRes createAnswer(Integer questioNumber, String reply, String userName, String userPhone,
			String userEmail, Integer userAge, String userGender);

	// 9.��ܥX�^�� (��v)
	public QuestionSystemRes getAnswerCount(Integer questioNumber, Integer headerNumber, String questionType);

	// 10.20230104�s�W �� �p��έp��api
	public QuestionSystemRes getHappyNewYearCount(Integer questionNumber);

	// �j�M 1. �̷� questioNumber �j�M userName answer_createtime (user_info)
	public QuestionSystemRes getReplyUser(Integer questioNumber);

	// �j�M 2. �̷� questioNumber userName ���o��g����椺�e (�n�jauid )(replyDao)
	public QuestionSystemRes getReplyUserInfo(Integer questioNumber);
}
