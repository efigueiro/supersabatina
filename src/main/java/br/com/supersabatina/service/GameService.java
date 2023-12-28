package br.com.supersabatina.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import br.com.supersabatina.model.dao.QuestionDao;

public class GameService {

	/*
	 * The defineInterval method is used to define the interval time in days for the
	 * user to review the study of the selected question. The calculation is based
	 * on the number of correct answers. 
	 * 
	 * The calculation will only be applied if the number of correct answers reaches 
	 * the values defined in the method.
	 */

	public void defineInterval(long questionGroupId, long questionId, int correctAnswerCount) {

		if (correctAnswerCount == 3) {
			processRevisionDate(questionGroupId, questionId, 1);
		} else if (correctAnswerCount == 6) {
			processRevisionDate(questionGroupId, questionId, 3);
		} else if (correctAnswerCount == 9) {
			processRevisionDate(questionGroupId, questionId, 6);
		} else if (correctAnswerCount == 12) {
			processRevisionDate(questionGroupId, questionId, 14);
		} else if (correctAnswerCount == 15) {
			processRevisionDate(questionGroupId, questionId, 30);
		} else if (correctAnswerCount == 18) {
			processRevisionDate(questionGroupId, questionId, 60);
		} else if (correctAnswerCount == 21) {
			processRevisionDate(questionGroupId, questionId, 90);
		}
	}

	/*
	 * The processRevisionDate method was created to update the revision date. The
	 * review date is changed by adding the number of days the selected question
	 * will be paused.
	 */
	
	public void processRevisionDate(long questionGroupId, long questionId, int days) {

		String revisionDate = "";
		QuestionDao questionDao = new QuestionDao();
		revisionDate = questionDao.retrieveRevisionDate(questionId, questionGroupId);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		try {

			Date date = format.parse(revisionDate);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.DAY_OF_YEAR, days);

			Date newDate = calendar.getTime();
			revisionDate = format.format(newDate);
			
			questionDao.updateRevisionDate(revisionDate, questionId, questionGroupId);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
