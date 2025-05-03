package br.com.supersabatina.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import br.com.supersabatina.model.dao.GameDao;
import br.com.supersabatina.model.dao.QuestionDao;
import br.com.supersabatina.model.entity.User;
import br.com.supersabatina.util.Statistic;

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
		GameDao gameDao = new GameDao();
		revisionDate = gameDao.retrieveRevisionDate(questionId, questionGroupId);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		try {

			Date date = format.parse(revisionDate);
			Calendar calendar = Calendar.getInstance();
			/*
			 * calendar.setTime(date); 
			 * Commented so that the date returned from the database
			 * is not taken into account when calculating the revision date. 
			 * In this case, the current date will be used.
			 */
			calendar.add(Calendar.DAY_OF_YEAR, days);

			Date newDate = calendar.getTime();
			revisionDate = format.format(newDate);
			
			gameDao.updateRevisionDate(revisionDate, questionId, questionGroupId);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// Processing number_correct_answer by question
	public void ProcessNumberCorrectAnswerByQuestion(long questionId, long questionGroupId) {
		int correctAnswerCount = 0;
		int incorrectAnswerCount = 0;
		
		GameDao gameDao = new GameDao();
		
		correctAnswerCount = gameDao.retrieveCorrectAnswerCount(questionId, questionGroupId);
		incorrectAnswerCount = gameDao.retrieveIncorrectAnswerCount(questionId, questionGroupId);
		correctAnswerCount = correctAnswerCount + 1;
		
		gameDao.updateCorrectAnswer(correctAnswerCount, questionId, questionGroupId);
		
		this.defineInterval(questionGroupId, questionId, correctAnswerCount);
		
	}
	
	// Processing number_incorrect_answer by question
	public void ProcessNumberIncorrectAnswerByQuestion(long questionId, long questionGroupId) {
		int correctAnswerCount = 0;
		int incorrectAnswerCount = 0;
		
		GameDao gameDao = new GameDao();
		
		correctAnswerCount = gameDao.retrieveCorrectAnswerCount(questionId, questionGroupId);
		incorrectAnswerCount = gameDao.retrieveIncorrectAnswerCount(questionId, questionGroupId);
		incorrectAnswerCount = incorrectAnswerCount + 1;
		
		gameDao.updateIncorrectAnswer(incorrectAnswerCount, questionId, questionGroupId);
		
	}
	
	// Return success rate by question
	public String sucessRateByQuestion(long questionId, long questionGroupId) {
		int correctAnswerCount = 0;
		int incorrectAnswerCount = 0;
		
		GameDao gameDao = new GameDao();
		
		correctAnswerCount = gameDao.retrieveCorrectAnswerCount(questionId, questionGroupId);
		incorrectAnswerCount = gameDao.retrieveIncorrectAnswerCount(questionId, questionGroupId);
		
		return Statistic.successRate(correctAnswerCount, incorrectAnswerCount);
	}
	
	// Return failure rate by question
	public String failureRateByQuestion(long questionId, long questionGroupId) {
		int correctAnswerCount = 0;
		int incorrectAnswerCount = 0;
		
		GameDao gameDao = new GameDao();
		
		correctAnswerCount = gameDao.retrieveCorrectAnswerCount(questionId, questionGroupId);
		incorrectAnswerCount = gameDao.retrieveIncorrectAnswerCount(questionId, questionGroupId);
		
		return Statistic.failureRate(correctAnswerCount, incorrectAnswerCount);
	}
	
	// Processing number_correct_answer by date
	public void ProcessNumberCorrectAnswerByDate(User authenticated) {
		
		GameDao gameDao = new GameDao();
		
		// Check if there are records for the current date and user
		if(gameDao.countActivityRecord(authenticated) > 0) {
			
			int correctAnswerCount = 0;
			int incorrectAnswerCount = 0;
			
			correctAnswerCount = gameDao.retrieveCorrectAnswerCountByDate(authenticated);
			incorrectAnswerCount = gameDao.retrieveIncorrectAnswerCountByDate(authenticated);
			correctAnswerCount = correctAnswerCount +1;
			
			gameDao.updateCorrectAnswerByDate(correctAnswerCount, authenticated);
			
		} else {
			int correctAnswerCount = 0;
			int incorrectAnswerCount = 0;
			
			correctAnswerCount = correctAnswerCount +1;
			
			gameDao.createActivityRecord(authenticated, correctAnswerCount, incorrectAnswerCount);
			
		}
	}
	
	// Processing number_incorrect_answer by date
	public void ProcessNumberIncorrectAnswerByDate(User authenticated) {
		
		GameDao gameDao = new GameDao();
		
		// Check if there are records for the current date and user
		if(gameDao.countActivityRecord(authenticated) > 0) {
			
			int correctAnswerCount = 0;
			int incorrectAnswerCount = 0;
			
			correctAnswerCount = gameDao.retrieveCorrectAnswerCountByDate(authenticated);
			incorrectAnswerCount = gameDao.retrieveIncorrectAnswerCountByDate(authenticated);
			incorrectAnswerCount = incorrectAnswerCount +1;
			
			gameDao.updateIncorrectAnswerByDate(incorrectAnswerCount, authenticated);
			
		} else {
			int correctAnswerCount = 0;
			int incorrectAnswerCount = 0;
			
			incorrectAnswerCount = incorrectAnswerCount +1;
			
			gameDao.createActivityRecord(authenticated, correctAnswerCount, incorrectAnswerCount);
			
		}
	}
	
	// Return success rate by date
	public String sucessRateByDate(User authenticated) {
		int correctAnswerCount = 0;
		int incorrectAnswerCount = 0;
		
		GameDao gameDao = new GameDao();
		
		correctAnswerCount = gameDao.retrieveCorrectAnswerCountByDate(authenticated);
		incorrectAnswerCount = gameDao.retrieveIncorrectAnswerCountByDate(authenticated);
		
		return Statistic.successRate(correctAnswerCount, incorrectAnswerCount);
	}
	
	// Return failure rate by date
	public String failureRateByDate(User authenticated) {
		int correctAnswerCount = 0;
		int incorrectAnswerCount = 0;
		
		GameDao gameDao = new GameDao();
		
		correctAnswerCount = gameDao.retrieveCorrectAnswerCountByDate(authenticated);
		incorrectAnswerCount = gameDao.retrieveIncorrectAnswerCountByDate(authenticated);
		
		return Statistic.failureRate(correctAnswerCount, incorrectAnswerCount);
	}
	
	// Return total questions by date
	public int totalQuestionByDate(User authenticated) {
		
		int correctAnswerCount = 0;
		int incorrectAnswerCount = 0;
		
		GameDao gameDao = new GameDao();
		
		correctAnswerCount = gameDao.retrieveCorrectAnswerCountByDate(authenticated);
		incorrectAnswerCount = gameDao.retrieveIncorrectAnswerCountByDate(authenticated);
		
		int totalQuestion = correctAnswerCount + incorrectAnswerCount;
		
		return totalQuestion;
	}
	
	// Check carefully if the methods below are still valid
	
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
