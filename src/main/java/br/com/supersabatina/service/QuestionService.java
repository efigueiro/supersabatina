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

	public List<Question> retrieveByQuestion(String question, User authenticated, String visibility) {

		List<Question> questionList = new ArrayList<Question>();
		QuestionDao questionDao = new QuestionDao();

		if (visibility.equals("all")) {
			questionList = questionDao.retrieveAllByQuestion(question, authenticated);
		}

		if (visibility.equals("private")) {
			questionList = questionDao.retrievePrivateByQuestion(question, authenticated);
		}

		if (visibility.equals("public")) {
			questionList = questionDao.retrievePublicByQuestion(question, authenticated);
		}

		if (!questionList.isEmpty()) {
			for (Question formattedQuestion : questionList) {
				String html = formattedQuestion.getQuestion().replaceAll("(\r\n|\n)", "<br>");
				formattedQuestion.setQuestion(html);
			}
		}

		return questionList;
	}

	public int count(User authenticated) {
		QuestionDao questionDao = new QuestionDao();
		return questionDao.count(authenticated);
	}

	// Retrieve questions by questionGroup and authenticated user
	public List<Question> retrieveQuestionList(long questionGroupId, User authenticated) {
		List<Question> questionList = new ArrayList<Question>();
		QuestionDao questionDao = new QuestionDao();
		questionList = questionDao.retrieveQuestionList(questionGroupId, authenticated);
		if (questionList.isEmpty()) {
			Messenger.setSuccessFalse();
			Messenger.addWarningMessage("Sua pesquisa não retornou dados.");
		} else {
			Messenger.setSuccessTrue();
		}
		return questionList;
	}

	// Retrieve questions by questionGroup, questionId and authenticated user
	public List<Question> retrieveQuestionList(long questionGroupId, long questionId, User authenticated) {
		List<Question> questionList = new ArrayList<Question>();
		QuestionDao questionDao = new QuestionDao();
		questionList = questionDao.retrieveQuestionList(questionGroupId, questionId, authenticated);
		if (questionList.isEmpty()) {
			Messenger.setSuccessFalse();
			Messenger.addWarningMessage("Sua pesquisa não retornou dados.");
		} else {
			Messenger.setSuccessTrue();
		}
		return questionList;
	}

	// Tenho que verificar se a pergunta e o grupo de perguntas pertence ao usuário
	// sendo assim não preciso fazer uma query para uma lista e sim para uma questão.
	public void removeQuestion(long questionGroupId, long questionId, User authenticated) {
		List<Question> questionList = new ArrayList<Question>();
		QuestionDao questionDao = new QuestionDao();
		QuestionGroupDao questionGroupDao = new QuestionGroupDao();
		questionList = questionDao.retrieveQuestionList(questionGroupId, questionId, authenticated);
		if (questionList.isEmpty()) {
			Messenger.setSuccessFalse();
			Messenger.addWarningMessage("Sua pesquisa não retornou dados.");
		} else {
			questionGroupDao.removeFromQuestionGroupQuestion(questionGroupId, questionId, authenticated);
			Messenger.setSuccessTrue();
			Messenger.addSuccessMessage("Pergunta removida com sucesso!");
		}
	}
}
