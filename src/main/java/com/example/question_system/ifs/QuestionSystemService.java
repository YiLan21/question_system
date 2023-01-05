package com.example.question_system.ifs;

import java.time.LocalDate;

import com.example.question_system.vo.QuestionSystemRes;

public interface QuestionSystemService {

	// 1.創建問題資料
	public QuestionSystemRes createQuestion(Integer questionNumber, String questionTitle, String questionInfo,
			LocalDate questionStarTime, LocalDate questionEndTime);

	// 2.修改問題資料
	public QuestionSystemRes updateQuestion(Integer questionNumber, String questionTitle, String questionInfo,
			LocalDate questionStarTime, LocalDate questionEndTime, LocalDate questionCreatetime);

	// 3.建立問題題目 (問題編碼,題目編碼,題目內容,題目類型,題目選擇)
	public QuestionSystemRes createHeader(Integer questionNumber, Integer headerNumber, String questionHeader,
			String headerType, String headerChoose);

	// 4.修改題目內容 (問題編碼,題目編碼,題目內容,題目類型,題目選擇)
	public QuestionSystemRes updateHeader(Integer questionNumber, Integer headerNumber, String questionHeader,
			String headerType, String headerChoose);

	// 5.搜尋題目 + 內容 (用 QuestionNumber )
	public QuestionSystemRes getQuestionAndHeader(Integer questionNumber);

	// 6.顯示所有問題(有)
	public QuestionSystemRes getAllQuestions();

	// 7.搜尋符合項目的問題
	// (輸入值 String questionTitle,LocalDate questionStarTime, LocalDate
	// questionEndTime )
	public QuestionSystemRes getQuestionsByTitleOrDate(String questionTitle, LocalDate questionStarTime,
			LocalDate questionEndTime);

	// 8.創建答案單
	// ( 輸入值 Integer questioNumber, Integer headerNumber, String reply,String
	// questionType, String userName,Integer userPhone, String userEmail, Integer
	// userAge, String userGender)
	public QuestionSystemRes createAnswer(Integer questioNumber, String reply, String userName, String userPhone,
			String userEmail, Integer userAge, String userGender);

	// 9.顯示出回覆 (比率)
	public QuestionSystemRes getAnswerCount(Integer questioNumber, Integer headerNumber, String questionType);

	// 10.20230104新增 的 計算統計的api
	public QuestionSystemRes getHappyNewYearCount(Integer questionNumber);

	// 搜尋 1. 依照 questioNumber 搜尋 userName answer_createtime (user_info)
	public QuestionSystemRes getReplyUser(Integer questioNumber);

	// 搜尋 2. 依照 questioNumber userName 取得填寫的表單內容 (要綁auid )(replyDao)
	public QuestionSystemRes getReplyUserInfo(Integer questioNumber);
}
