package br.com.supersabatina.service;

import br.com.supersabatina.model.dao.QuestionDao;

public class GameService {
	
	public void processRevisionDate( long questionGroupId, long questionId) {
		
		int correctAnswerCount = 0;
		String revisionDate = "";
		
		QuestionDao questionDao = new QuestionDao();
		
		correctAnswerCount = questionDao.retrieveCorrectAnswerCount(questionId, questionGroupId);
		revisionDate = questionDao.retrieveRevisionDate(questionId, questionGroupId);

		
		
	}

}
