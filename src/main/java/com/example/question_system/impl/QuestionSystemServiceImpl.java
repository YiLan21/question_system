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

	// 1.�إ߰��D(title)
	@Override
	public QuestionSystemRes createQuestion(Integer questionNumber, String questionTitle, String questionInfo,
			LocalDate questionStarTime, LocalDate questionEndTime) {
		/*---- �s�X & �ɶ���J���b ----*/
		// �p�G�o�ӽs�X�s�b���� , �N�^�ǰT: �����D�w�g�إ� > ���X
		if (questionDao.existsById(questionNumber)) {
			return new QuestionSystemRes(null, QuestionSystemCode.QUESTION_EXSIT.getMessage());
		}
		LocalDate questionCreatetime = LocalDate.now();
		// �p�G�����ɶ���}�l�ɶ����A �h�^�ǰT��: �ɶ���J���~ > ���X
		if (questionStarTime.isAfter(questionEndTime)) {
			return new QuestionSystemRes(null, QuestionSystemCode.DATE_FAIL.getMessage());
		}
		// �p�G �}�l�򵲧����ɶ����b �إ߮ɶ��e�� --> �إߪ��ɶ��n�b �}�l & ���� �ɶ����e �A �h�^�ǰT��: �ɶ���J���~ > ���X
		if (questionStarTime.isBefore(questionCreatetime) || questionEndTime.isBefore(questionCreatetime)) {
			return new QuestionSystemRes(null, QuestionSystemCode.DATE_FAIL.getMessage());
		}
		Question question = new Question(questionNumber, questionTitle, questionInfo, questionStarTime, questionEndTime,
				questionCreatetime);
		questionDao.save(question);
		return new QuestionSystemRes(question, QuestionSystemCode.CREATE_SUCCESSFUL.getMessage());
	}

	// 2.��s���D(title)
	@Override
	public QuestionSystemRes updateQuestion(Integer questionNumber, String questionTitle, String questionInfo,
			LocalDate questionStarTime, LocalDate questionEndTime, LocalDate questionCreatetime) {
		/*---- �s�X & �ɶ���J���b ----*/
		// �p�G�o�ӽs�X���s�b���� , �N�^�ǰT: �S�����T�� --> ���X
		if (!questionDao.existsById(questionNumber)) {
			return new QuestionSystemRes(null, QuestionSystemCode.QUESTION_ISNOT_EXSIT.getMessage());
		}
		// �p�G�����ɶ���}�l�ɶ����A �h�^�ǰT��: �ɶ���J���~ > ���X
		if (questionStarTime.isAfter(questionEndTime)) {
			return new QuestionSystemRes(null, QuestionSystemCode.DATE_FAIL.getMessage());
		}
		// �p�G �}�l�򵲧����ɶ����b �إ߮ɶ��e�� --> �إߪ��ɶ��n�b �}�l & ���� �ɶ����e �A �h�^�ǰT��: �ɶ���J���~ > ���X
		if (questionStarTime.isBefore(questionCreatetime) || questionEndTime.isBefore(questionCreatetime)) {
			return new QuestionSystemRes(null, QuestionSystemCode.DATE_FAIL.getMessage());
		}
		LocalDate questionUpdatetime = LocalDate.now();
		Question question = new Question(questionNumber, questionTitle, questionInfo, questionStarTime, questionEndTime,
				questionCreatetime, questionUpdatetime);
		questionDao.save(question);
		return new QuestionSystemRes(question, QuestionSystemCode.CREATE_SUCCESSFUL.getMessage());
	}

	// 3.�إ߰��D�D�� (���D�s�X,�D�ؽs�X,�D�ؤ��e,�D������,�D�ؿ��)
	@Override
	public QuestionSystemRes createHeader(Integer questionNumber, Integer headerNumber, String questionHeader,
			String questionType, String headerChoose) {
		/*---- �s�X & �ɶ���J���b ----*/
		// �p�G�o�ӽs�X���s�b���� , �N�^�ǰT: �S�����T�� --> ���X
		if (!questionDao.existsById(questionNumber)) {
			return new QuestionSystemRes(null, QuestionSystemCode.QUESTION_ISNOT_EXSIT.getMessage());
		}
		// �p�G���N�~��j�MHeaderDao�̭����S���P���D�ؽs�X��Pheader number���D��
		// �bheader���g��k findByQuestionNumberAndheaderNumber(Integer
		// questionNumber,Integer headerNumber);
		List<HeaderInfo> headerList = headerInfoDao.findByQuestionNumber(questionNumber);
		// �M��questionNumber ���o��List���S�����ƪ��D��
		for (HeaderInfo item : headerList) {
			// �p�G�D�����ۦP����,
			if (item.getHeaderNumber().equals(headerNumber)) {
				// �N�|�^�� : �D���w�g�����o! ���T�� �åB�����C
				return new QuestionSystemRes(null, QuestionSystemCode.HEADER_NUMBER_FAIL.getMessage());
				// �p�G���@�˪���
			} else {
			}
		}
		// ���Ʀs�i��b header�غc����k��
		HeaderInfo headerInfo = new HeaderInfo(questionNumber, headerNumber, questionHeader, questionType,
				headerChoose);
		// �s�J��Ʈw
		headerInfoDao.save(headerInfo);
		// �^�� header
		return new QuestionSystemRes(headerInfo);
	}

	// 4.�ק���D�D�� (���D�s�X,�D�ؽs�X,�D�ؤ��e,�D������,�D�ؿ��)
	@Override
	public QuestionSystemRes updateHeader(Integer questionNumber, Integer headerNumber, String questionHeader,
			String questionType, String headerChoose) {
		/*---- �s�X & �ɶ���J���b ----*/
		// �p�G�o�ӽs�X���s�b���� , �N�^�ǰT: �S�����T�� --> ���X
		if (!questionDao.existsById(questionNumber)) {
			return new QuestionSystemRes(null, QuestionSystemCode.QUESTION_ISNOT_EXSIT.getMessage());
		}
		Optional<HeaderInfo> headerInfoOp = headerInfoDao.findByQuestionNumberAndHeaderNumber(questionNumber,
				headerNumber);
		// �p�G�����󬰪Ū���
		if (!headerInfoOp.isPresent()) {
			// �^��NULL ����~�T��: �����󤣦s�b�C
			return new QuestionSystemRes(null, QuestionSystemCode.QUESTION_ISNOT_EXSIT.getMessage());
		}
		// ��o�Ӹ�ƨ��X��
		HeaderInfo headerInfo = headerInfoOp.get();
		// �g�J�n��諸���e
		headerInfo.setQuestionHeader(questionHeader);
		headerInfo.setQuestionType(questionType);
		headerInfo.setHeaderChoose(headerChoose);
		// �s�J��Ʈw
		headerInfoDao.save(headerInfo);
		return new QuestionSystemRes(headerInfo);
	}

	// 5.�j�M�D�� + ���e (�� QuestionNumber )
	@Override
	public QuestionSystemRes getQuestionAndHeader(Integer questionNumber) {

		// 1.�T�{ QuestionNumber ��N�bquestionDao �����X�L����� (Optional �]���u���@��)
		// 2.�bHeaderDao �����X �o�� questionNumber ���Ҧ����D (��list�]�_�� --> �bres�̭��w�g�إ�
		// QuestionsResList)

		Optional<Question> questionOp = questionDao.findAllByQuestionNumber(questionNumber);

		if (!questionOp.isPresent()) {
			return new QuestionSystemRes(null, QuestionSystemCode.QUESTION_ISNOT_EXSIT.getMessage());
		}
		// ���o�D��
		Question question = questionOp.get();
		String state = "�벼��";
		// �p�G�}�l�ɶ��b�{�b���� �A state = �|���}��벼
		if (question.getQuestionStarTime().isAfter(LocalDate.now())) {
			state = "�|���}�l";
			// �p�G�}�l�ɶ��b�{�b���� �A state = �����벼
		} else if (question.getQuestionEndTime().isBefore(LocalDate.now())) {
			state = "�w����";
			// �������~���O��ܧ벼��
		} else {
			state = "�벼��";
		}
		// �إ߷s��list �n�ө� ��ܪ��T��
		List<QuestionsResInfo> questionsResInfoList = new ArrayList<>();
		List<HeaderInfo> headerInfoList = headerInfoDao.findByQuestionNumber(questionNumber);
		for (HeaderInfo item : headerInfoList) {
			// �إߤ@�i������n�� HeaderChoose
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

	// 6.��ܩҦ��� Questions
	@Override
	public QuestionSystemRes getAllQuestions() {
		List<QuestionsFindAllInfo> questionsFindAllInfoList = new ArrayList<>();
		List<Question> questionList = questionDao.findAll();
		for (Question item : questionList) {
			String state = "�벼��";
			// �p�G�}�l�ɶ��b�{�b���� �A state = �|���}��벼
			if (item.getQuestionStarTime().isAfter(LocalDate.now())) {
				state = "�|���}�l";
				// �p�G�}�l�ɶ��b�{�b���� �A state = �����벼
			} else if (item.getQuestionEndTime().isBefore(LocalDate.now())) {
				state = "�w����";
				// �������~���O��ܧ벼��
			} else {
				state = "�벼��";
			}
			QuestionsFindAllInfo questionsFindAllInfo = new QuestionsFindAllInfo(item.getQuestionNumber(),
					item.getQuestionTitle(), item.getQuestionInfo(), item.getQuestionStarTime(),
					item.getQuestionEndTime(), state);
			questionsFindAllInfoList.add(questionsFindAllInfo);
		}
		return new QuestionSystemRes(questionsFindAllInfoList);
	}

	// 7.��ܲŦX���ت����D
	@Override
	public QuestionSystemRes getQuestionsByTitleOrDate(String questionTitle, LocalDate questionStarTime,
			LocalDate questionEndTime) {
		/*
		 * 4�ت��p .���p 1��JquestionTitle �A questionStarTime & questionEndTime ���� ���p
		 * 2��JquestionStarTime & questionEndTime �A questionTitle ���� ���p
		 * 3��JquestionStarTime & questionEndTime & questionTitle 4������J���ŭ�
		 */
		List<QuestionsFindAllInfo> questionsFindAllInfoList = new ArrayList<>();

		List<Question> questionList = questionDao.findAll();
		// ���p 1.��J questionTitle �A questionStarTime & questionEndTime ����
		if (StringUtils.hasText(questionTitle) && questionStarTime == null && questionEndTime == null) {
			for (Question item : questionList) {
				if (item.getQuestionTitle().contains(questionTitle)) {
					String state = "�벼��";
					// �p�G�}�l�ɶ��b�{�b���� �A state = �|���}��벼
					if (item.getQuestionStarTime().isAfter(LocalDate.now())) {
						state = "�|���}�l";
						// �p�G�}�l�ɶ��b�{�b���� �A state = �����벼
					} else if (item.getQuestionEndTime().isBefore(LocalDate.now())) {
						state = "�w����";
						// �������~���O��ܧ벼��
					} else {
						state = "�벼��";
					}
					QuestionsFindAllInfo questionsFindAllInfo = new QuestionsFindAllInfo(item.getQuestionNumber(),
							item.getQuestionTitle(), item.getQuestionInfo(), item.getQuestionStarTime(),
							item.getQuestionEndTime(), state);
					questionsFindAllInfoList.add(questionsFindAllInfo);

				}

			}
			return new QuestionSystemRes(questionsFindAllInfoList);
			// ���p 2. ��JquestionStarTime & questionEndTime �A questionTitle ���� ���p
		} else if (!StringUtils.hasText(questionTitle) && questionStarTime != null && questionEndTime != null) {
			for (Question item : questionList) {
				if (item.getQuestionStarTime().isAfter(questionStarTime)
						&& item.getQuestionEndTime().isBefore(questionEndTime)) {
					String state = "�벼��";
					// �p�G�}�l�ɶ��b�{�b���� �A state = �|���}��벼
					if (item.getQuestionStarTime().isAfter(LocalDate.now())) {
						state = "�|���}�l";
						// �p�G�}�l�ɶ��b�{�b���� �A state = �����벼
					} else if (item.getQuestionEndTime().isBefore(LocalDate.now())) {
						state = "�w����";
						// �������~���O��ܧ벼��
					} else {
						state = "�벼��";
					}
					QuestionsFindAllInfo questionsFindAllInfo = new QuestionsFindAllInfo(item.getQuestionNumber(),
							item.getQuestionTitle(), item.getQuestionInfo(), item.getQuestionStarTime(),
							item.getQuestionEndTime(), state);
					questionsFindAllInfoList.add(questionsFindAllInfo);

				}
			}
			return new QuestionSystemRes(questionsFindAllInfoList);
			// ���p 3. ��JquestionStarTime & questionEndTime & questionTitle ���p
		} else if (StringUtils.hasText(questionTitle) && questionStarTime != null && questionEndTime != null) {
			for (Question item : questionList) {
				if (item.getQuestionStarTime().isAfter(questionStarTime)
						&& item.getQuestionEndTime().isBefore(questionEndTime)
						&& item.getQuestionTitle().contains(questionTitle)) {
					String state = "�벼��";
					// �p�G�}�l�ɶ��b�{�b���� �A state = �|���}��벼
					if (item.getQuestionStarTime().isAfter(LocalDate.now())) {
						state = "�|���}�l";
						// �p�G�}�l�ɶ��b�{�b���� �A state = �����벼
					} else if (item.getQuestionEndTime().isBefore(LocalDate.now())) {
						state = "�w����";
						// �������~���O��ܧ벼��
					} else {
						state = "�벼��";
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

				String state = "�벼��";
				// �p�G�}�l�ɶ��b�{�b���� �A state = �|���}��벼
				if (item.getQuestionStarTime().isAfter(LocalDate.now())) {
					state = "�|���}�l";
					// �p�G�}�l�ɶ��b�{�b���� �A state = �����벼
				} else if (item.getQuestionEndTime().isBefore(LocalDate.now())) {
					state = "�w����";
					// �������~���O��ܧ벼��
				} else {
					state = "�벼��";
				}
				QuestionsFindAllInfo questionsFindAllInfo = new QuestionsFindAllInfo(item.getQuestionNumber(),
						item.getQuestionTitle(), item.getQuestionInfo(), item.getQuestionStarTime(),
						item.getQuestionEndTime(), state);
				questionsFindAllInfoList.add(questionsFindAllInfo);
			}

		}
		return new QuestionSystemRes(questionsFindAllInfoList);

	}

	// 8.�x�s�^��
	public QuestionSystemRes createAnswer(Integer questionNumber, String reply, String userName, String userPhone,
			String userEmail, Integer userAge, String userGender) {
		// �إ�userInfo�s���Ʈw --> .save --> Dao
		LocalDate answerCreatetime = LocalDate.now();
		// ������Ƥ@�� UserInfo ���Ŷ� --> �M��get �o�ӪŶ��� AnswerAutoId() �åB��J��L��T
		UserInfo userInfo = new UserInfo();
		UserInfo userInfo1 = new UserInfo(userInfo.getAnswerAutoId(), questionNumber, userName, userPhone, userEmail,
				userAge, userGender, answerCreatetime);
		userInfoDao.save(userInfo1);
		/*
		 * ����List�]�˩Ҧ��PquestionNumber ���D�� ���ο�J��reply EX"A, A;B, C,C" �s�J List �̭�
		 * �M��@�Ӥ@�ӱa�J�x�s�� --> �̫᪺���b�i�H�Ψ��list�� sizz�T�{ �D�p���S���� (��M�O�̫�)
		 */
		// ����� QuestionHeaderList
		List<HeaderInfo> QuestionHeaderList = headerInfoDao.findByQuestionNumber(questionNumber);
		// �s�إ� replyList �ΨӦs�񵥵����Ϋ᪺Reply
		List<String> replyList = new ArrayList<>();
		String[] replyArray = reply.split(",");
		for (String item : replyArray) {
			String str = item.trim();
			replyList.add(str);
		}
		for (int i = 0; i < QuestionHeaderList.size(); i++) {
			Integer HeaderNumber = QuestionHeaderList.get(i).getHeaderNumber();
			// ���A�ɶ��������D (ex: �벼�|���إ� & �벼�w���� �� �b�e�ݴN�L�k�g�J�F)
			Reply replyInfo = new Reply(questionNumber, HeaderNumber, replyList.get(i),
					QuestionHeaderList.get(i).getQuestionType(), userName, userPhone, userEmail, userAge, userGender,
					userInfo1.getAnswerAutoId());
			replyDao.save(replyInfo);
		}
		return new QuestionSystemRes("���\");
	}

	// 9. �p��o���D�ئ��h�� %
	@Override
	public QuestionSystemRes getAnswerCount(Integer questionNumber, Integer headerNumber, String questionType) {

		List<Reply> replyList = replyDao.findAllByQuestionNumberAndHeaderNumberAndQuestionType(questionNumber,
				headerNumber, questionType);

		int aTotle = 0;
		int bTotle = 0;
		int cTotle = 0;
		int dTotle = 0;
		int eTotle = 0;

		if (questionType.contains("���")) {
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
			// �p��ʤ���
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
		if (questionType.contains("�h��")) {
			// �p�G�O�h�� ��new �@�ӷs��list �P�_ ���S���ݭn����
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
			// �p��ʤ���
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

		return new QuestionSystemRes("��r�T���L�k�έp");
	}

//	// 10.�p��έp
//	@Override
//	public QuestionSystemRes getHappyNewYearCount(Integer questionNumber) {
//		
//		// ��o�� Question ��info �������X�Ӹ˦� Optional
//		Optional<Question> questionOp = questionDao.findAllByQuestionNumber(questionNumber);
//		
//		if (!questionOp.isPresent()) {
//			return new QuestionSystemRes(null, QuestionSystemCode.QUESTION_ISNOT_EXSIT.getMessage());
//		}
//		// ���o�D��
//		Question question = questionOp.get();
//		String state = "�벼��";
//		// �p�G�}�l�ɶ��b�{�b���� �A state = �|���}��벼
//		if (question.getQuestionStarTime().isAfter(LocalDate.now())) {
//			state = "�|���}�l";
//			// �p�G�}�l�ɶ��b�{�b���� �A state = �����벼
//		} else if (question.getQuestionEndTime().isBefore(LocalDate.now())) {
//			state = "�w����";
//			// �������~���O��ܧ벼��
//		} else {
//			state = "�벼��";
//		}
//		// �n�^�ǳ�@�D�ؤ����p�D�ت�Info
//		// �]�t headerNumber questionHeader questionType List<ChooseLast> chooseLastList;
//		List<QuestionResCount> questionResCountList = new ArrayList<>();
//		// ���b��k�� ��X �ŦX�o��questioNumber ����T
//		List<Reply> replyList = replyDao.findByQuestionNumber(questionNumber);
//		for (Reply reply : replyList) {
//			int totle = 0;
//			Map<String, Integer> count = new HashMap<>();
////			for (HeaderInfo headerInfo : headerList) {
//			// �s�J ���Ϋ�Ҧ������ ��","���}
//			// �s�� �b�D�ؤ����Ϋ᪺�ﶵ --> �̭��񪺬O�Ҧ���choose
//			List<String> headerChooseList = new ArrayList<>();
//			// �s�񦳦^���o���D�ؤ��Ϋ᪺���� --> �{�b�s��̫᪺�Ҧ��o�D�o�쪺����list
//			List<String> replyChooseList = new ArrayList<>();
//			// ���X�Ҧ��o���D�����o�p�D���Ҧ���T
//			List<Reply> totleReply = replyDao.findByQuestionNumberAndHeaderNumber(questionNumber,
//					reply.getHeaderNumber());
//			List<HeaderInfo> totleHeader = headerInfoDao.findAllByQuestionNumberAndHeaderNumber(questionNumber,
//					reply.getHeaderNumber());
//			Optional<HeaderInfo> Header = headerInfoDao.findByQuestionNumberAndHeaderNumber(questionNumber,
//					reply.getHeaderNumber());
//			// �o��list �O�n��m �ﶵ�� (�w�g�إߦbvo.)
//			List<ChooseLast> chooseLastList = new ArrayList<>();
//			
//			// �b���O���γo�D�o�쪺�Ҧ������ܦ��@��list --> replyChooseList
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
//					// ��C�ӿﶵ���K�[��reply�̭� (�N��C�ӿﶵ���|�Q�إߤ@��list -->chooseLast)
//					replyChooseList.add(lastH);
//				}
//			}
//			
//			for (String replyitem : replyChooseList) {
//				count.put(replyitem, count.getOrDefault(replyitem, 0) + 1);
//				totle++;
//			}
//			// �p�⵪�ץe��
//			for (Map.Entry<String, Integer> entry : count.entrySet()) {
//				
//				String answer = entry.getKey();
//				int acount = entry.getValue() - 1;
//				int lastTotle = totle - headerChooseList.size();
//				double percentage = 100.0 * acount / lastTotle;
//				
//				ChooseLast chooseLast = new ChooseLast(answer, percentage, acount, lastTotle);
//				// �o�̭���m���O�C�ӿﶵ��ʤ���
//				chooseLastList.add(chooseLast);
//			}
//			/*
//			 * // �n�^�ǳ�@�D�ؤ����p�D�ت�Info // �]�t headerNumber questionHeader questionType
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
	// 10.�p��έp
	@Override
	public QuestionSystemRes getHappyNewYearCount(Integer questionNumber) {

		// ��o�� Question ��info �������X�Ӹ˦� Optional
		Optional<Question> questionOp = questionDao.findAllByQuestionNumber(questionNumber);

		if (!questionOp.isPresent()) {
			return new QuestionSystemRes(null, QuestionSystemCode.QUESTION_ISNOT_EXSIT.getMessage());
		}
		// ���o�D��
		Question question = questionOp.get();
		String state = "�벼��";
		// �p�G�}�l�ɶ��b�{�b���� �A state = �|���}��벼
		if (question.getQuestionStarTime().isAfter(LocalDate.now())) {
			state = "�|���}�l";
			// �p�G�}�l�ɶ��b�{�b���� �A state = �����벼
		} else if (question.getQuestionEndTime().isBefore(LocalDate.now())) {
			state = "�w����";
			// �������~���O��ܧ벼��
		} else {
			state = "�벼��";
		}
		// �n�^�ǳ�@�D�ؤ����p�D�ت�Info
		// �]�t headerNumber questionHeader questionType List<ChooseLast> chooseLastList;
		List<QuestionResCount> questionResCountList = new ArrayList<>();
		// ���b��k�� ��X �ŦX�o��questioNumber ����T
//		List<Reply> replyList = replyDao.findByQuestionNumber(questionNumber);
		List<HeaderInfo> replyList = headerInfoDao.findByQuestionNumber(questionNumber);

		for (HeaderInfo reply : replyList) {
			int totle = 0;
			Map<String, Integer> count = new HashMap<>();
//			for (HeaderInfo headerInfo : headerList) {
			// �s�J ���Ϋ�Ҧ������ ��","���}
			// �s�� �b�D�ؤ����Ϋ᪺�ﶵ --> �̭��񪺬O�Ҧ���choose
			List<String> headerChooseList = new ArrayList<>();
			// �s�񦳦^���o���D�ؤ��Ϋ᪺���� --> �{�b�s��̫᪺�Ҧ��o�D�o�쪺����list
			List<String> replyChooseList = new ArrayList<>();
			// ���X�Ҧ��o���D�����o�p�D���Ҧ���T
			List<Reply> totleReply = replyDao.findByQuestionNumberAndHeaderNumber(questionNumber,
					reply.getHeaderNumber());
			List<HeaderInfo> totleHeader = headerInfoDao.findAllByQuestionNumberAndHeaderNumber(questionNumber,
					reply.getHeaderNumber());
			Optional<HeaderInfo> Header = headerInfoDao.findByQuestionNumberAndHeaderNumber(questionNumber,
					reply.getHeaderNumber());
			// �o��list �O�n��m �ﶵ�� (�w�g�إߦbvo.)
			List<ChooseLast> chooseLastList = new ArrayList<>();

			// �b���O���γo�D�o�쪺�Ҧ������ܦ��@��list --> replyChooseList
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
					// ��C�ӿﶵ���K�[��reply�̭� (�N��C�ӿﶵ���|�Q�إߤ@��list -->chooseLast)
					replyChooseList.add(lastH);
				}
			}

			for (String replyitem : replyChooseList) {
				count.put(replyitem, count.getOrDefault(replyitem, 0) + 1);
				totle++;
			}
			// �p�⵪�ץe��
			for (Map.Entry<String, Integer> entry : count.entrySet()) {

				String answer = entry.getKey();
				int acount = entry.getValue() - 1;
				int lastTotle = totle - headerChooseList.size();
				double percentage = 100.0 * acount / lastTotle;

				ChooseLast chooseLast = new ChooseLast(answer, percentage, acount, lastTotle);
				// �o�̭���m���O�C�ӿﶵ��ʤ���
				chooseLastList.add(chooseLast);
			}
			/*
			 * // �n�^�ǳ�@�D�ؤ����p�D�ت�Info // �]�t headerNumber questionHeader questionType
			 * List<ChooseLast> chooseLastList; List<QuestionResCount> questionResCount =new
			 * ArrayList<>();
			 */
			QuestionResCount questionResCountLast = new QuestionResCount(reply.getHeaderNumber(),
					Header.get().getQuestionHeader(), reply.getQuestionType(), chooseLastList);
			questionResCountList.add(questionResCountLast);
		}

		return new QuestionSystemRes(question, questionResCountList, state);
	}

	// �j�M 1. �̷� questioNumber �j�M userName answer_createtime (user_info)
	@Override
	public QuestionSystemRes getReplyUser(Integer questioNumber) {
		return null;
	}

	// �j�M 2. �̷� questioNumber userName ���o��g����椺�e (�n�jauid )(replyDao)
	// �ּg�L�o�i���
	@Override
	public QuestionSystemRes getReplyUserInfo(Integer questioNumber) {
		return null;
	}

}
