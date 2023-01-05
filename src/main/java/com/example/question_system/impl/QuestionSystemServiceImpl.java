package com.example.question_system.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.question_system.constants.QuestionSystemCode;
import com.example.question_system.entity.HeaderInfo;
import com.example.question_system.entity.Question;
import com.example.question_system.entity.Reply;
import com.example.question_system.entity.UserInfo;
import com.example.question_system.ifs.QuestionSystemService;
import com.example.question_system.respository.UserInfoDao;
import com.example.question_system.respository.HeaderInfoDao;
import com.example.question_system.respository.QuestionDao;
import com.example.question_system.respository.ReplyDao;
import com.example.question_system.vo.ChooseCountInfo;
import com.example.question_system.vo.ChooseLast;
import com.example.question_system.vo.QuestionResCount;
import com.example.question_system.vo.QuestionSystemRes;
import com.example.question_system.vo.QuestionsFindAllInfo;
import com.example.question_system.vo.QuestionsResInfo;

@Service
public class QuestionSystemServiceImpl implements QuestionSystemService {

	@Autowired
	private QuestionDao questionDao;

	@Autowired
	private UserInfoDao userInfoDao;

	@Autowired
	private ReplyDao replyDao;

	@Autowired
	private HeaderInfoDao headerInfoDao;

	// 1.建立問題(title)
	@Override
	public QuestionSystemRes createQuestion(Integer questionNumber, String questionTitle, String questionInfo,
			LocalDate questionStarTime, LocalDate questionEndTime) {
		/*---- 編碼 & 時間輸入防呆 ----*/
		// 如果這個編碼存在的話 , 就回傳訊: 此問題已經建立 > 跳出
		if (questionDao.existsById(questionNumber)) {
			return new QuestionSystemRes(null, QuestionSystemCode.QUESTION_EXSIT.getMessage());
		}
		LocalDate questionCreatetime = LocalDate.now();
		// 如果結束時間比開始時間早， 則回傳訊息: 時間輸入錯誤 > 跳出
		if (questionStarTime.isAfter(questionEndTime)) {
			return new QuestionSystemRes(null, QuestionSystemCode.DATE_FAIL.getMessage());
		}
		// 如果 開始跟結束的時間都在 建立時間前面 --> 建立的時間要在 開始 & 結束 時間之前 ， 則回傳訊息: 時間輸入錯誤 > 跳出
		if (questionStarTime.isBefore(questionCreatetime) || questionEndTime.isBefore(questionCreatetime)) {
			return new QuestionSystemRes(null, QuestionSystemCode.DATE_FAIL.getMessage());
		}
		Question question = new Question(questionNumber, questionTitle, questionInfo, questionStarTime, questionEndTime,
				questionCreatetime);
		questionDao.save(question);
		return new QuestionSystemRes(question, QuestionSystemCode.CREATE_SUCCESSFUL.getMessage());
	}

	// 2.更新問題(title)
	@Override
	public QuestionSystemRes updateQuestion(Integer questionNumber, String questionTitle, String questionInfo,
			LocalDate questionStarTime, LocalDate questionEndTime, LocalDate questionCreatetime) {
		/*---- 編碼 & 時間輸入防呆 ----*/
		// 如果這個編碼不存在的話 , 就回傳訊: 沒有此訊息 --> 跳出
		if (!questionDao.existsById(questionNumber)) {
			return new QuestionSystemRes(null, QuestionSystemCode.QUESTION_ISNOT_EXSIT.getMessage());
		}
		// 如果結束時間比開始時間早， 則回傳訊息: 時間輸入錯誤 > 跳出
		if (questionStarTime.isAfter(questionEndTime)) {
			return new QuestionSystemRes(null, QuestionSystemCode.DATE_FAIL.getMessage());
		}
		// 如果 開始跟結束的時間都在 建立時間前面 --> 建立的時間要在 開始 & 結束 時間之前 ， 則回傳訊息: 時間輸入錯誤 > 跳出
		if (questionStarTime.isBefore(questionCreatetime) || questionEndTime.isBefore(questionCreatetime)) {
			return new QuestionSystemRes(null, QuestionSystemCode.DATE_FAIL.getMessage());
		}
		LocalDate questionUpdatetime = LocalDate.now();
		Question question = new Question(questionNumber, questionTitle, questionInfo, questionStarTime, questionEndTime,
				questionCreatetime, questionUpdatetime);
		questionDao.save(question);
		return new QuestionSystemRes(question, QuestionSystemCode.CREATE_SUCCESSFUL.getMessage());
	}

	// 3.建立問題題目 (問題編碼,題目編碼,題目內容,題目類型,題目選擇)
	@Override
	public QuestionSystemRes createHeader(Integer questionNumber, Integer headerNumber, String questionHeader,
			String questionType, String headerChoose) {
		/*---- 編碼 & 時間輸入防呆 ----*/
		// 如果這個編碼不存在的話 , 就回傳訊: 沒有此訊息 --> 跳出
		if (!questionDao.existsById(questionNumber)) {
			return new QuestionSystemRes(null, QuestionSystemCode.QUESTION_ISNOT_EXSIT.getMessage());
		}
		// 如果有就繼續搜尋HeaderDao裡面有沒有同樣題目編碼跟同header number的題目
		// 在header中寫方法 findByQuestionNumberAndheaderNumber(Integer
		// questionNumber,Integer headerNumber);
		List<HeaderInfo> headerList = headerInfoDao.findByQuestionNumber(questionNumber);
		// 遍歷questionNumber 的這個List有沒有重複的題號
		for (HeaderInfo item : headerList) {
			// 如果題號有相同的話,
			if (item.getHeaderNumber().equals(headerNumber)) {
				// 就會回傳 : 題號已經重複囉! 的訊息 並且結束。
				return new QuestionSystemRes(null, QuestionSystemCode.HEADER_NUMBER_FAIL.getMessage());
				// 如果不一樣的話
			} else {
			}
		}
		// 把資料存進放在 header建構式方法中
		HeaderInfo headerInfo = new HeaderInfo(questionNumber, headerNumber, questionHeader, questionType,
				headerChoose);
		// 存入資料庫
		headerInfoDao.save(headerInfo);
		// 回傳 header
		return new QuestionSystemRes(headerInfo);
	}

	// 4.修改問題題目 (問題編碼,題目編碼,題目內容,題目類型,題目選擇)
	@Override
	public QuestionSystemRes updateHeader(Integer questionNumber, Integer headerNumber, String questionHeader,
			String questionType, String headerChoose) {
		/*---- 編碼 & 時間輸入防呆 ----*/
		// 如果這個編碼不存在的話 , 就回傳訊: 沒有此訊息 --> 跳出
		if (!questionDao.existsById(questionNumber)) {
			return new QuestionSystemRes(null, QuestionSystemCode.QUESTION_ISNOT_EXSIT.getMessage());
		}
		Optional<HeaderInfo> headerInfoOp = headerInfoDao.findByQuestionNumberAndHeaderNumber(questionNumber,
				headerNumber);
		// 如果此物件為空的話
		if (!headerInfoOp.isPresent()) {
			// 回傳NULL 跟錯誤訊息: 此物件不存在。
			return new QuestionSystemRes(null, QuestionSystemCode.QUESTION_ISNOT_EXSIT.getMessage());
		}
		// 把這個資料取出來
		HeaderInfo headerInfo = headerInfoOp.get();
		// 寫入要更改的內容
		headerInfo.setQuestionHeader(questionHeader);
		headerInfo.setQuestionType(questionType);
		headerInfo.setHeaderChoose(headerChoose);
		// 存入資料庫
		headerInfoDao.save(headerInfo);
		return new QuestionSystemRes(headerInfo);
	}

	// 5.搜尋題目 + 內容 (用 QuestionNumber )
	@Override
	public QuestionSystemRes getQuestionAndHeader(Integer questionNumber) {

		// 1.確認 QuestionNumber 後就在questionDao 中撈出他的資料 (Optional 因為只有一筆)
		// 2.在HeaderDao 中取出 這個 questionNumber 的所有問題 (用list包起來 --> 在res裡面已經建立
		// QuestionsResList)

		Optional<Question> questionOp = questionDao.findAllByQuestionNumber(questionNumber);

		if (!questionOp.isPresent()) {
			return new QuestionSystemRes(null, QuestionSystemCode.QUESTION_ISNOT_EXSIT.getMessage());
		}
		// 取得題目
		Question question = questionOp.get();
		String state = "投票中";
		// 如果開始時間在現在之後 ， state = 尚未開放投票
		if (question.getQuestionStarTime().isAfter(LocalDate.now())) {
			state = "尚未開始";
			// 如果開始時間在現在之後 ， state = 結束投票
		} else if (question.getQuestionEndTime().isBefore(LocalDate.now())) {
			state = "已完結";
			// 除此之外都是顯示投票中
		} else {
			state = "投票中";
		}
		// 建立新的list 要來放 顯示的訊息
		List<QuestionsResInfo> questionsResInfoList = new ArrayList<>();
		List<HeaderInfo> headerInfoList = headerInfoDao.findByQuestionNumber(questionNumber);
		for (HeaderInfo item : headerInfoList) {
			// 建立一張表單放切好的 HeaderChoose
			List<String> headerChooseList = new ArrayList<>();
			String headerChoose = item.getHeaderChoose();
			String[] headerChooseCheck = headerChoose.split(",");
			for (String i : headerChooseCheck) {
				String str = i.trim();
				headerChooseList.add(str);
			}
			QuestionsResInfo questionsResInfo = new QuestionsResInfo(item.getHeaderNumber(), item.getQuestionHeader(),
					item.getQuestionType(), headerChooseList);
			questionsResInfoList.add(questionsResInfo);

		}
		return new QuestionSystemRes(question, state, questionsResInfoList);
	}

	// 6.顯示所有的 Questions
	@Override
	public QuestionSystemRes getAllQuestions() {
		List<QuestionsFindAllInfo> questionsFindAllInfoList = new ArrayList<>();
		List<Question> questionList = questionDao.findAll();
		for (Question item : questionList) {
			String state = "投票中";
			// 如果開始時間在現在之後 ， state = 尚未開放投票
			if (item.getQuestionStarTime().isAfter(LocalDate.now())) {
				state = "尚未開始";
				// 如果開始時間在現在之後 ， state = 結束投票
			} else if (item.getQuestionEndTime().isBefore(LocalDate.now())) {
				state = "已完結";
				// 除此之外都是顯示投票中
			} else {
				state = "投票中";
			}
			QuestionsFindAllInfo questionsFindAllInfo = new QuestionsFindAllInfo(item.getQuestionNumber(),
					item.getQuestionTitle(), item.getQuestionInfo(), item.getQuestionStarTime(),
					item.getQuestionEndTime(), state);
			questionsFindAllInfoList.add(questionsFindAllInfo);
		}
		return new QuestionSystemRes(questionsFindAllInfoList);
	}

	// 7.顯示符合項目的問題
	@Override
	public QuestionSystemRes getQuestionsByTitleOrDate(String questionTitle, LocalDate questionStarTime,
			LocalDate questionEndTime) {
		/*
		 * 4種狀況 .狀況 1輸入questionTitle ， questionStarTime & questionEndTime 為空 狀況
		 * 2輸入questionStarTime & questionEndTime ， questionTitle 為空 狀況
		 * 3輸入questionStarTime & questionEndTime & questionTitle 4全部輸入為空值
		 */
		List<QuestionsFindAllInfo> questionsFindAllInfoList = new ArrayList<>();

		List<Question> questionList = questionDao.findAll();
		// 狀況 1.輸入 questionTitle ， questionStarTime & questionEndTime 為空
		if (StringUtils.hasText(questionTitle) && questionStarTime == null && questionEndTime == null) {
			for (Question item : questionList) {
				if (item.getQuestionTitle().contains(questionTitle)) {
					String state = "投票中";
					// 如果開始時間在現在之後 ， state = 尚未開放投票
					if (item.getQuestionStarTime().isAfter(LocalDate.now())) {
						state = "尚未開始";
						// 如果開始時間在現在之後 ， state = 結束投票
					} else if (item.getQuestionEndTime().isBefore(LocalDate.now())) {
						state = "已完結";
						// 除此之外都是顯示投票中
					} else {
						state = "投票中";
					}
					QuestionsFindAllInfo questionsFindAllInfo = new QuestionsFindAllInfo(item.getQuestionNumber(),
							item.getQuestionTitle(), item.getQuestionInfo(), item.getQuestionStarTime(),
							item.getQuestionEndTime(), state);
					questionsFindAllInfoList.add(questionsFindAllInfo);

				}

			}
			return new QuestionSystemRes(questionsFindAllInfoList);
			// 狀況 2. 輸入questionStarTime & questionEndTime ， questionTitle 為空 狀況
		} else if (!StringUtils.hasText(questionTitle) && questionStarTime != null && questionEndTime != null) {
			for (Question item : questionList) {
				if (item.getQuestionStarTime().isAfter(questionStarTime)
						&& item.getQuestionEndTime().isBefore(questionEndTime)) {
					String state = "投票中";
					// 如果開始時間在現在之後 ， state = 尚未開放投票
					if (item.getQuestionStarTime().isAfter(LocalDate.now())) {
						state = "尚未開始";
						// 如果開始時間在現在之後 ， state = 結束投票
					} else if (item.getQuestionEndTime().isBefore(LocalDate.now())) {
						state = "已完結";
						// 除此之外都是顯示投票中
					} else {
						state = "投票中";
					}
					QuestionsFindAllInfo questionsFindAllInfo = new QuestionsFindAllInfo(item.getQuestionNumber(),
							item.getQuestionTitle(), item.getQuestionInfo(), item.getQuestionStarTime(),
							item.getQuestionEndTime(), state);
					questionsFindAllInfoList.add(questionsFindAllInfo);

				}
			}
			return new QuestionSystemRes(questionsFindAllInfoList);
			// 狀況 3. 輸入questionStarTime & questionEndTime & questionTitle 狀況
		} else if (StringUtils.hasText(questionTitle) && questionStarTime != null && questionEndTime != null) {
			for (Question item : questionList) {
				if (item.getQuestionStarTime().isAfter(questionStarTime)
						&& item.getQuestionEndTime().isBefore(questionEndTime)
						&& item.getQuestionTitle().contains(questionTitle)) {
					String state = "投票中";
					// 如果開始時間在現在之後 ， state = 尚未開放投票
					if (item.getQuestionStarTime().isAfter(LocalDate.now())) {
						state = "尚未開始";
						// 如果開始時間在現在之後 ， state = 結束投票
					} else if (item.getQuestionEndTime().isBefore(LocalDate.now())) {
						state = "已完結";
						// 除此之外都是顯示投票中
					} else {
						state = "投票中";
					}
					QuestionsFindAllInfo questionsFindAllInfo = new QuestionsFindAllInfo(item.getQuestionNumber(),
							item.getQuestionTitle(), item.getQuestionInfo(), item.getQuestionStarTime(),
							item.getQuestionEndTime(), state);
					questionsFindAllInfoList.add(questionsFindAllInfo);

				}
			}
			return new QuestionSystemRes(questionsFindAllInfoList);
		} else {
			for (Question item : questionList) {

				String state = "投票中";
				// 如果開始時間在現在之後 ， state = 尚未開放投票
				if (item.getQuestionStarTime().isAfter(LocalDate.now())) {
					state = "尚未開始";
					// 如果開始時間在現在之後 ， state = 結束投票
				} else if (item.getQuestionEndTime().isBefore(LocalDate.now())) {
					state = "已完結";
					// 除此之外都是顯示投票中
				} else {
					state = "投票中";
				}
				QuestionsFindAllInfo questionsFindAllInfo = new QuestionsFindAllInfo(item.getQuestionNumber(),
						item.getQuestionTitle(), item.getQuestionInfo(), item.getQuestionStarTime(),
						item.getQuestionEndTime(), state);
				questionsFindAllInfoList.add(questionsFindAllInfo);
			}

		}
		return new QuestionSystemRes(questionsFindAllInfoList);

	}

	// 8.儲存回答
	public QuestionSystemRes createAnswer(Integer questionNumber, String reply, String userName, String userPhone,
			String userEmail, Integer userAge, String userGender) {
		// 建立userInfo存到資料庫 --> .save --> Dao
		LocalDate answerCreatetime = LocalDate.now();
		// 先實體化一個 UserInfo 的空間 --> 然後get 這個空間的 AnswerAutoId() 並且塞入其他資訊
		UserInfo userInfo = new UserInfo();
		UserInfo userInfo1 = new UserInfo(userInfo.getAnswerAutoId(), questionNumber, userName, userPhone, userEmail,
				userAge, userGender, answerCreatetime);
		userInfoDao.save(userInfo1);
		/*
		 * 先用List包裝所有同questionNumber 的題目 切割輸入的reply EX"A, A;B, C,C" 存入 List 裡面
		 * 然後一個一個帶入儲存格 --> 最後的防呆可以用兩個list的 sizz確認 題署有沒有對 (當然是最後)
		 */
		// 實體化 QuestionHeaderList
		List<HeaderInfo> QuestionHeaderList = headerInfoDao.findByQuestionNumber(questionNumber);
		// 新建立 replyList 用來存放等等切割後的Reply
		List<String> replyList = new ArrayList<>();
		String[] replyArray = reply.split(",");
		for (String item : replyArray) {
			String str = item.trim();
			replyList.add(str);
		}
		for (int i = 0; i < QuestionHeaderList.size(); i++) {
			Integer HeaderNumber = QuestionHeaderList.get(i).getHeaderNumber();
			// 不再時間內的問題 (ex: 投票尚未建立 & 投票已結束 的 在前端就無法寫入了)
			Reply replyInfo = new Reply(questionNumber, HeaderNumber, replyList.get(i),
					QuestionHeaderList.get(i).getQuestionType(), userName, userPhone, userEmail, userAge, userGender,
					userInfo1.getAnswerAutoId());
			replyDao.save(replyInfo);
		}
		return new QuestionSystemRes("成功");
	}

	// 9. 計算這個題目有多少 %
	@Override
	public QuestionSystemRes getAnswerCount(Integer questionNumber, Integer headerNumber, String questionType) {

		List<Reply> replyList = replyDao.findAllByQuestionNumberAndHeaderNumberAndQuestionType(questionNumber,
				headerNumber, questionType);

		int aTotle = 0;
		int bTotle = 0;
		int cTotle = 0;
		int dTotle = 0;
		int eTotle = 0;

		if (questionType.contains("單選")) {
			for (Reply item : replyList) {
				String i = item.getReply();

				switch (i) {
				case "A":
					aTotle++;
					break;
				case "B":
					bTotle++;
					break;
				case "C":
					cTotle++;
					break;
				case "D":
					dTotle++;
					break;
				case "E":
					eTotle++;
					break;
				}
			}
			int totle = replyList.size();
			// 計算百分比
			// A
			int a = (int) Math.round(((aTotle * 100.0 / totle)));
			String Apoint = a + "%";
			// B
			int b = (int) Math.round(((bTotle * 100.0 / totle)));
			String Bpoint = b + "%";
			// C
			int c = (int) Math.round(((cTotle * 100.0 / totle)));
			String Cpoint = c + "%";
			// D
			int d = (int) Math.round(((dTotle * 100.0 / totle)));
			String Dpoint = d + "%";
			// E
			int e = (int) Math.round(((eTotle * 100.0 / totle)));
			String Epoint = e + "%";

			ChooseCountInfo chooseCountInfo = new ChooseCountInfo(questionNumber, headerNumber, questionType, Apoint,
					Bpoint, Cpoint, Dpoint, Epoint);

			return new QuestionSystemRes(chooseCountInfo);
		}
		List<String> checkSizaList = new ArrayList<>();
		if (questionType.contains("多選")) {
			// 如果是多選 先new 一個新的list 判斷 有沒有需要切割
			for (Reply item : replyList) {
				String i = item.getReply();
				String[] newReply = i.split(";");

				for (String checkReply : newReply) {

					String j = checkReply.trim();
					checkSizaList.add(j);

					switch (j) {
					case "A":
						aTotle++;
						break;
					case "B":
						bTotle++;
						break;
					case "C":
						cTotle++;
						break;
					case "D":
						dTotle++;
						break;
					case "E":
						eTotle++;
						break;
					}
				}
			}
			int totle = checkSizaList.size();
			// 計算百分比
			// A
			int a = (int) Math.round(((aTotle * 100.0 / totle)));
			String Apoint = a + "%";
			// B
			int b = (int) Math.round(((bTotle * 100.0 / totle)));
			String Bpoint = b + "%";
			// C
			int c = (int) Math.round(((cTotle * 100.0 / totle)));
			String Cpoint = c + "%";
			// D
			int d = (int) Math.round(((dTotle * 100.0 / totle)));
			String Dpoint = d + "%";
			// E
			int e = (int) Math.round(((eTotle * 100.0 / totle)));
			String Epoint = e + "%";

			ChooseCountInfo chooseCountInfo = new ChooseCountInfo(questionNumber, headerNumber, questionType, Apoint,
					Bpoint, Cpoint, Dpoint, Epoint);

			return new QuestionSystemRes(chooseCountInfo);

		}

		return new QuestionSystemRes("文字訊息無法統計");
	}

//	// 10.計算統計
//	@Override
//	public QuestionSystemRes getHappyNewYearCount(Integer questionNumber) {
//		
//		// 把這個 Question 的info 全部拿出來裝成 Optional
//		Optional<Question> questionOp = questionDao.findAllByQuestionNumber(questionNumber);
//		
//		if (!questionOp.isPresent()) {
//			return new QuestionSystemRes(null, QuestionSystemCode.QUESTION_ISNOT_EXSIT.getMessage());
//		}
//		// 取得題目
//		Question question = questionOp.get();
//		String state = "投票中";
//		// 如果開始時間在現在之後 ， state = 尚未開放投票
//		if (question.getQuestionStarTime().isAfter(LocalDate.now())) {
//			state = "尚未開始";
//			// 如果開始時間在現在之後 ， state = 結束投票
//		} else if (question.getQuestionEndTime().isBefore(LocalDate.now())) {
//			state = "已完結";
//			// 除此之外都是顯示投票中
//		} else {
//			state = "投票中";
//		}
//		// 要回傳單一題目中的小題目的Info
//		// 包含 headerNumber questionHeader questionType List<ChooseLast> chooseLastList;
//		List<QuestionResCount> questionResCountList = new ArrayList<>();
//		// 先在方法中 找出 符合這個questioNumber 的資訊
//		List<Reply> replyList = replyDao.findByQuestionNumber(questionNumber);
//		for (Reply reply : replyList) {
//			int totle = 0;
//			Map<String, Integer> count = new HashMap<>();
////			for (HeaderInfo headerInfo : headerList) {
//			// 存入 切割後所有的選擇 用","分開
//			// 存放 在題目中切割後的選項 --> 裡面放的是所有的choose
//			List<String> headerChooseList = new ArrayList<>();
//			// 存放有回答這個題目切割後的答案 --> 現在存放最後的所有這題得到的答案list
//			List<String> replyChooseList = new ArrayList<>();
//			// 取出所有這個題號的這小題的所有資訊
//			List<Reply> totleReply = replyDao.findByQuestionNumberAndHeaderNumber(questionNumber,
//					reply.getHeaderNumber());
//			List<HeaderInfo> totleHeader = headerInfoDao.findAllByQuestionNumberAndHeaderNumber(questionNumber,
//					reply.getHeaderNumber());
//			Optional<HeaderInfo> Header = headerInfoDao.findByQuestionNumberAndHeaderNumber(questionNumber,
//					reply.getHeaderNumber());
//			// 這個list 是要放置 選項的 (已經建立在vo.)
//			List<ChooseLast> chooseLastList = new ArrayList<>();
//			
//			// 在此是分割這題得到的所有答案變成一個list --> replyChooseList
//			for (Reply totleRe : totleReply) {
//				String lastRe = totleRe.getReply();
//				String[] lastReArray = lastRe.split(";");
//				for (String last : lastReArray) {
//					replyChooseList.add(last);
//				}
//			}
//			
//			for (HeaderInfo totleHd : totleHeader) {
//				String lastHd = totleHd.getHeaderChoose();
//				String[] lastHdeArray = lastHd.split(",");
//				for (String lastH : lastHdeArray) {
//					headerChooseList.add(lastH);
//					// 把每個選項都添加到reply裡面 (代表每個選項都會被建立一個list -->chooseLast)
//					replyChooseList.add(lastH);
//				}
//			}
//			
//			for (String replyitem : replyChooseList) {
//				count.put(replyitem, count.getOrDefault(replyitem, 0) + 1);
//				totle++;
//			}
//			// 計算答案占比
//			for (Map.Entry<String, Integer> entry : count.entrySet()) {
//				
//				String answer = entry.getKey();
//				int acount = entry.getValue() - 1;
//				int lastTotle = totle - headerChooseList.size();
//				double percentage = 100.0 * acount / lastTotle;
//				
//				ChooseLast chooseLast = new ChooseLast(answer, percentage, acount, lastTotle);
//				// 這裡面放置的是每個選項跟百分比
//				chooseLastList.add(chooseLast);
//			}
//			/*
//			 * // 要回傳單一題目中的小題目的Info // 包含 headerNumber questionHeader questionType
//			 * List<ChooseLast> chooseLastList; List<QuestionResCount> questionResCount =new
//			 * ArrayList<>();
//			 */
//			QuestionResCount questionResCountLast = new QuestionResCount(reply.getHeaderNumber(),
//					Header.get().getQuestionHeader(), reply.getQuestionType(), chooseLastList);
//			questionResCountList.add(questionResCountLast);
//		}
//		
//		return new QuestionSystemRes(question, questionResCountList, state);
//	}
	// 10.計算統計
	@Override
	public QuestionSystemRes getHappyNewYearCount(Integer questionNumber) {

		// 把這個 Question 的info 全部拿出來裝成 Optional
		Optional<Question> questionOp = questionDao.findAllByQuestionNumber(questionNumber);

		if (!questionOp.isPresent()) {
			return new QuestionSystemRes(null, QuestionSystemCode.QUESTION_ISNOT_EXSIT.getMessage());
		}
		// 取得題目
		Question question = questionOp.get();
		String state = "投票中";
		// 如果開始時間在現在之後 ， state = 尚未開放投票
		if (question.getQuestionStarTime().isAfter(LocalDate.now())) {
			state = "尚未開始";
			// 如果開始時間在現在之後 ， state = 結束投票
		} else if (question.getQuestionEndTime().isBefore(LocalDate.now())) {
			state = "已完結";
			// 除此之外都是顯示投票中
		} else {
			state = "投票中";
		}
		// 要回傳單一題目中的小題目的Info
		// 包含 headerNumber questionHeader questionType List<ChooseLast> chooseLastList;
		List<QuestionResCount> questionResCountList = new ArrayList<>();
		// 先在方法中 找出 符合這個questioNumber 的資訊
//		List<Reply> replyList = replyDao.findByQuestionNumber(questionNumber);
		List<HeaderInfo> replyList = headerInfoDao.findByQuestionNumber(questionNumber);

		for (HeaderInfo reply : replyList) {
			int totle = 0;
			Map<String, Integer> count = new HashMap<>();
//			for (HeaderInfo headerInfo : headerList) {
			// 存入 切割後所有的選擇 用","分開
			// 存放 在題目中切割後的選項 --> 裡面放的是所有的choose
			List<String> headerChooseList = new ArrayList<>();
			// 存放有回答這個題目切割後的答案 --> 現在存放最後的所有這題得到的答案list
			List<String> replyChooseList = new ArrayList<>();
			// 取出所有這個題號的這小題的所有資訊
			List<Reply> totleReply = replyDao.findByQuestionNumberAndHeaderNumber(questionNumber,
					reply.getHeaderNumber());
			List<HeaderInfo> totleHeader = headerInfoDao.findAllByQuestionNumberAndHeaderNumber(questionNumber,
					reply.getHeaderNumber());
			Optional<HeaderInfo> Header = headerInfoDao.findByQuestionNumberAndHeaderNumber(questionNumber,
					reply.getHeaderNumber());
			// 這個list 是要放置 選項的 (已經建立在vo.)
			List<ChooseLast> chooseLastList = new ArrayList<>();

			// 在此是分割這題得到的所有答案變成一個list --> replyChooseList
			for (Reply totleRe : totleReply) {
				String lastRe = totleRe.getReply();
				String[] lastReArray = lastRe.split(";");
				for (String last : lastReArray) {
					replyChooseList.add(last);
				}
			}

			for (HeaderInfo totleHd : totleHeader) {
				String lastHd = totleHd.getHeaderChoose();
				String[] lastHdeArray = lastHd.split(",");
				for (String lastH : lastHdeArray) {
					headerChooseList.add(lastH);
					// 把每個選項都添加到reply裡面 (代表每個選項都會被建立一個list -->chooseLast)
					replyChooseList.add(lastH);
				}
			}

			for (String replyitem : replyChooseList) {
				count.put(replyitem, count.getOrDefault(replyitem, 0) + 1);
				totle++;
			}
			// 計算答案占比
			for (Map.Entry<String, Integer> entry : count.entrySet()) {

				String answer = entry.getKey();
				int acount = entry.getValue() - 1;
				int lastTotle = totle - headerChooseList.size();
				double percentage = 100.0 * acount / lastTotle;

				ChooseLast chooseLast = new ChooseLast(answer, percentage, acount, lastTotle);
				// 這裡面放置的是每個選項跟百分比
				chooseLastList.add(chooseLast);
			}
			/*
			 * // 要回傳單一題目中的小題目的Info // 包含 headerNumber questionHeader questionType
			 * List<ChooseLast> chooseLastList; List<QuestionResCount> questionResCount =new
			 * ArrayList<>();
			 */
			QuestionResCount questionResCountLast = new QuestionResCount(reply.getHeaderNumber(),
					Header.get().getQuestionHeader(), reply.getQuestionType(), chooseLastList);
			questionResCountList.add(questionResCountLast);
		}

		return new QuestionSystemRes(question, questionResCountList, state);
	}

	// 搜尋 1. 依照 questioNumber 搜尋 userName answer_createtime (user_info)
	@Override
	public QuestionSystemRes getReplyUser(Integer questioNumber) {
		return null;
	}

	// 搜尋 2. 依照 questioNumber userName 取得填寫的表單內容 (要綁auid )(replyDao)
	// 誰寫過這張表單
	@Override
	public QuestionSystemRes getReplyUserInfo(Integer questioNumber) {
		return null;
	}

}
