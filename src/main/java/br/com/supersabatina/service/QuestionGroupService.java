package br.com.supersabatina.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import br.com.supersabatina.model.dao.QuestionGroupDao;
import br.com.supersabatina.model.entity.QuestionGroup;
import br.com.supersabatina.model.entity.User;
import br.com.supersabatina.util.Messenger;

public class QuestionGroupService {

	public void create(QuestionGroup questionGroup) {
		QuestionGroupDao questionGroupDao = new QuestionGroupDao();
		questionGroupDao.create(questionGroup);
		Messenger.addSuccessMessage("Grupo de perguntas criado com sucesso.");
	}

	public List<QuestionGroup> retrieveByTitle(String title, User authenticated) {
		List<QuestionGroup> questionGroupList = new ArrayList<QuestionGroup>();
		QuestionGroupDao questionGroupDao = new QuestionGroupDao();
		questionGroupList = questionGroupDao.retrieveByTitle(title, authenticated);
		return questionGroupList;
	}

	public QuestionGroup retrieveById(long questionGroupId, long userId) {
		QuestionGroupDao questionGroupDao = new QuestionGroupDao();
		return questionGroupDao.retrieveById(questionGroupId, userId);
	}

	public void update(QuestionGroup questionGroup) {
		QuestionGroupDao questionGroupDao = new QuestionGroupDao();
		questionGroupDao.update(questionGroup);
		Messenger.addSuccessMessage("Grupo de questões atualizado com sucesso!");
	}

	public void delete(long questionGroupId, long userId) {
		QuestionGroupDao questionGroupDao = new QuestionGroupDao();
		questionGroupDao.delete(questionGroupId, userId);
		
		QuestionGroup checkDelete = new QuestionGroup();
		checkDelete = questionGroupDao.retrieveById(questionGroupId, userId);
		
		if(!StringUtils.isNotEmpty(checkDelete.getTitle())) {
			Messenger.addSuccessMessage("Registro excluido com sucesso!");
		}
	}
}
