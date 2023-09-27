package br.com.supersabatina.service;

import br.com.supersabatina.model.dao.QuestionGroupDao;
import br.com.supersabatina.model.entity.QuestionGroup;
import br.com.supersabatina.util.Messenger;

public class CreateQuestionGroupService {
	
	public void create(QuestionGroup questionGroup) {
		
		QuestionGroupDao questionGroupDao = new QuestionGroupDao();
		questionGroupDao.create(questionGroup);
		
		Messenger.addSuccessMessage("Grupo de perguntas criado com sucesso.");
	}

}
