package br.com.supersabatina.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import br.com.supersabatina.model.dao.QuestionDao;
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
	
	public List<QuestionGroup> retrieveAllByUserId(User authenticated) {
		List<QuestionGroup> questionGroupList = new ArrayList<QuestionGroup>();
		QuestionGroupDao questionGroupDao = new QuestionGroupDao();
		questionGroupList = questionGroupDao.retrieveAllByUserId(authenticated);
		return questionGroupList;
	}

	public List<QuestionGroup> retrieveByTitle(String title, User authenticated) {
		List<QuestionGroup> questionGroupList = new ArrayList<QuestionGroup>();
		QuestionGroupDao questionGroupDao = new QuestionGroupDao();
		if (StringUtils.isEmpty(title)) {
			questionGroupList = questionGroupDao.retrieveByTitle(authenticated);
		} else {
			questionGroupList = questionGroupDao.retrieveByTitle(title, authenticated);
		}
		
		return questionGroupList;
	}

	public QuestionGroup retrieveById(long questionGroupId, User Authenticated) {
		QuestionGroup questionGroup = new QuestionGroup();
		QuestionGroupDao questionGroupDao = new QuestionGroupDao();
		questionGroup = questionGroupDao.retrieveById(questionGroupId, Authenticated);
		return questionGroup;
	}

	public void update(QuestionGroup questionGroup) {
		QuestionGroupDao questionGroupDao = new QuestionGroupDao();
		questionGroupDao.update(questionGroup);
		Messenger.addSuccessMessage("Grupo de questões atualizado com sucesso!");
	}

	public void delete(long questionGroupId, User authenticated) {
		QuestionGroupDao questionGroupDao = new QuestionGroupDao();
		QuestionGroup questionGroup = new QuestionGroup();
		questionGroup = questionGroupDao.retrieveById(questionGroupId, authenticated);
		if(StringUtils.isNotEmpty(questionGroup.getTitle())) {
			questionGroupDao.delete(questionGroupId, authenticated);
			Messenger.addSuccessMessage("Registro excluido com sucesso!");
		}
	}
	
	public List<Long> retrieveQuestionIdByQuestionGroup(long questionGroupId) {
		List<Long> questionIdList = new ArrayList<Long>();
		QuestionGroupDao questionGroupDao = new QuestionGroupDao();
		questionIdList = questionGroupDao.retrieveQuestionIdByQuestionGroup(questionGroupId);
		return questionIdList;
	}
	
	public int countQuestionByQuestionGroup( long questionGroupId) {
		List<Long> questionIdList = new ArrayList<Long>();
		QuestionGroupDao questionGroupDao = new QuestionGroupDao();
		questionIdList = questionGroupDao.retrieveQuestionIdByQuestionGroup(questionGroupId);
		return questionIdList.size();
	}
	
	public int count(long questionGroupId) {
		QuestionGroupDao questionGroupDao = new QuestionGroupDao();
		return questionGroupDao.count(questionGroupId);
	}
	
	public int countQuestionGroup(long userId) {
		QuestionGroupDao questionGroupDao = new QuestionGroupDao();
		return questionGroupDao.countQuestionGroup(userId);
	}
}
