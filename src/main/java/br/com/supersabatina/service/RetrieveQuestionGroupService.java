package br.com.supersabatina.service;

import java.util.ArrayList;
import java.util.List;

import br.com.supersabatina.model.dao.QuestionGroupDao;
import br.com.supersabatina.model.entity.QuestionGroup;
import br.com.supersabatina.model.entity.User;

public class RetrieveQuestionGroupService {

	public List<QuestionGroup> retrieveByTitle(String title, User authenticated) {

		List<QuestionGroup> questionGroupList = new ArrayList<QuestionGroup>();
		QuestionGroupDao questionGroupDao = new QuestionGroupDao();

		questionGroupList = questionGroupDao.retrieveByTitle(title, authenticated);

		return questionGroupList;

	}

}
