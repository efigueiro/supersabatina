package br.com.supersabatina.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import br.com.supersabatina.model.dao.QuestionDao;
import br.com.supersabatina.model.dao.QuestionGroupDao;
import br.com.supersabatina.model.entity.Question;
import br.com.supersabatina.model.entity.User;
import br.com.supersabatina.util.Messenger;

public class QuestionService {

	public void create(Question question, long questionGroupId) {

		QuestionDao questionDao = new QuestionDao();
		Question questionTest = new Question();

		// Checking if question already exist before create a new question.
		questionTest = questionDao.retrieveByQuestionAndUserId(question, question.getUser().getUserId());

		if (StringUtils.isEmpty(questionTest.getQuestion())) {
			questionDao.create(question, questionGroupId);
			questionTest = questionDao.retrieveByQuestionAndUserId(question, question.getUser().getUserId());
			questionDao.createQuestionGroupQuestion(questionTest, questionGroupId);

			if (Messenger.messageList.isEmpty()) {
				Messenger.addSuccessMessage("Pergunta criada com sucesso!");
			}
		} else {
			Messenger.addWarningMessage(
					"A pergunta que você tentou criar já existe, por este motivo não foi possível completar a gravação.");
		}
	}

	public List<Question> retrieveByQuestion(String question, User authenticated, String visibility, int offset) {

		List<Question> questionList = new ArrayList<Question>();
		QuestionDao questionDao = new QuestionDao();

		if (visibility.equals("all")) {
			questionList = questionDao.retrieveAll(question, authenticated, offset);
		}

		if (visibility.equals("private")) {
			questionList = questionDao.retrievePrivate(question, authenticated, offset);
		}

		if (!questionList.isEmpty()) {
			for (Question formattedQuestion : questionList) {
				String html = formattedQuestion.getQuestion().replaceAll("(\r\n|\n)", "<br>");
				formattedQuestion.setQuestion(html);
			}
		}

		return questionList;
	}

	public int retrieveByQuestionCount(String question, User authenticated, String visibility) {

		int count = 0;
		QuestionDao questionDao = new QuestionDao();

		if (visibility.equals("all")) {
			count = questionDao.retrieveAllCount(question, authenticated);
		}

		if (visibility.equals("private")) {
			count = questionDao.retrievePrivateCount(question, authenticated);
		}

		return count;
	}

	public int count(User authenticated) {
		QuestionDao questionDao = new QuestionDao();
		return questionDao.count(authenticated);
	}

	// Retrieve questions by questionGroup, authenticated user and offset
	public List<Question> retrieveQuestionList(long questionGroupId, User authenticated, int offset) {
		List<Question> questionList = new ArrayList<Question>();
		QuestionDao questionDao = new QuestionDao();
		questionList = questionDao.retrieveQuestionList(questionGroupId, authenticated, offset);

		if (!questionList.isEmpty()) {
			for (Question formattedQuestion : questionList) {
				String html = formattedQuestion.getQuestion().replaceAll("(\r\n|\n)", "<br>");
				formattedQuestion.setQuestion(html);
			}
		}
		return questionList;
	}

	// Retrieve questions by questionGroup, authenticated user
	public List<Question> retrieveQuestionList(long questionGroupId, User authenticated) {
		List<Question> questionList = new ArrayList<Question>();
		QuestionDao questionDao = new QuestionDao();
		questionList = questionDao.retrieveQuestionList(questionGroupId, authenticated);

		if (!questionList.isEmpty()) {
			for (Question formattedQuestion : questionList) {
				String html = formattedQuestion.getQuestion().replaceAll("(\r\n|\n)", "<br>");
				formattedQuestion.setQuestion(html);
			}
		}
		return questionList;
	}

	// Retrieve count questions by questionGroup and authenticated user
	public int retrieveQuestionListCount(long questionGroupId, User authenticated) {
		int count = 0;
		QuestionDao questionDao = new QuestionDao();
		count = questionDao.retrieveQuestionListCount(questionGroupId, authenticated);
		return count;
	}

	// Remove the selected question from question group
	public void removeQuestion(long questionGroupId, long questionId, User authenticated) {
		QuestionGroupDao questionGroupDao = new QuestionGroupDao();
		questionGroupDao.removeFromQuestionGroupQuestion(questionGroupId, questionId, authenticated);
		Messenger.addSuccessMessage("Pergunta removida com sucesso!");
	}

	// Retrieve a random question
	public Question retrieveQuestionRandom(long questionGroupId, User authenticated) {
		Question question = new Question();
		QuestionDao questionDao = new QuestionDao();
		question = questionDao.retrieveQuestionRandom(questionGroupId, authenticated);

		if (StringUtils.isNotEmpty(question.getQuestion())) {
			String html = question.getQuestion().replaceAll("(\r\n|\n)", "<br>");
			question.setQuestion(html);
		}

		return question;
	}

	// Retrieve a random question
	public Question retrieveQuestionById(long questionId) {
		Question question = new Question();
		QuestionDao questionDao = new QuestionDao();
		question = questionDao.retrieveQuestionById(questionId);

		if (StringUtils.isNotEmpty(question.getQuestion())) {
			String html = question.getAnswer().replaceAll("(\r\n|\n)", "<br>");
			question.setAnswer(html);
			html = question.getQuestion().replaceAll("(\r\n|\n)", "<br>");
			question.setQuestion(html);
		}

		return question;
	}
	
	// Retrieve number_correct_answer from question_group_question
	public int correctAnswerCount(long questionId, long questionGroupId) {
		QuestionDao questionDao = new QuestionDao();
		return questionDao.retrieveCorrectAnswerCount(questionId, questionGroupId);
	}
	
	// Update number_correct_question from question_group_question
	public void updateCorrectAnswer(int correctAnswerCount, long questionId, long questionGroupId) {
		QuestionDao questionDao = new QuestionDao();
		questionDao.updateCorrectAnswer(correctAnswerCount, questionId, questionGroupId);
	}
	
	// Retrieve number_incorrect_answer from question_group_question
	public int incorrectAnswerCount(long questionId, long questionGroupId) {
		QuestionDao questionDao = new QuestionDao();
		return questionDao.retrieveIncorrectAnswerCount(questionId, questionGroupId);
	}
	
	// Update number_incorrect_question from question_group_question - working here
	public void updateIncorrectAnswer(int correctAnswerCount, long questionId, long questionGroupId) {
		QuestionDao questionDao = new QuestionDao();
		questionDao.updateIncorrectAnswer(correctAnswerCount, questionId, questionGroupId);
	}
}
