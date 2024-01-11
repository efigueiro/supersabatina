package br.com.supersabatina.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.supersabatina.model.entity.Question;
import br.com.supersabatina.model.entity.QuestionGroup;
import br.com.supersabatina.model.entity.User;
import br.com.supersabatina.util.Messenger;

public class GameDao extends BaseDao {

	final static Logger logger = LogManager.getLogger(GameDao.class.getName());
	
	// Retrieve only one random question
	public Question retrieveQuestionRandom(long questionGroupId, User authenticated) {

		Question question = new Question();
		String sql = "SELECT * "
				   + "FROM question "
				   + "INNER JOIN question_group_question ON question_group_question.question_id = question.question_id "
				   + "WHERE question_group_question.question_group_id = ? "
				   + "AND CURRENT_DATE >= question_group_question.revision_date "
				   + "ORDER BY RANDOM() "
		           + "LIMIT 1;";

		try {
			Connection conn = this.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setLong(1, questionGroupId);
			ResultSet rs = pstm.executeQuery();
			if (rs.next()) {
				question.setQuestionId(rs.getLong("question_id"));
				question.setAnswer(rs.getString("answer"));
				question.setSubject(rs.getString("subject"));
				question.setQuestion(rs.getString("question"));
				question.setUser(authenticated);
				question.setVisibility(rs.getString("visibility"));
			}

		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			Messenger.addDangerMessage(ex.getMessage());
		}
		
		return question;
	}
	
	// Retrieve number_correct_answer from question_group_question
	public int retrieveCorrectAnswerCount(long questionId, long questionGroupId) {

		int correctAnswerCount = 0;
		String sql = "SELECT * "
				   + "FROM question_group_question "
				   + "WHERE question_group_question.question_id = ? "
				   + "AND question_group_question.question_group_id = ?;";

		try {
			Connection conn = this.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setLong(1, questionId);
			pstm.setLong(2, questionGroupId);
			ResultSet rs = pstm.executeQuery();
			if (rs.next()) {
				correctAnswerCount = rs.getInt("number_correct_answer");
			}

		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			Messenger.addDangerMessage(ex.getMessage());
		}
		
		return correctAnswerCount;
	}
	
	// Retrieve number_incorrect_answer from question_group_question
	public int retrieveIncorrectAnswerCount(long questionId, long questionGroupId) {

		int incorrectAnswerCount = 0;
		String sql = "SELECT * "
				   + "FROM question_group_question "
				   + "WHERE question_group_question.question_id = ? "
				   + "AND question_group_question.question_group_id = ?;";

		try {
			Connection conn = this.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setLong(1, questionId);
			pstm.setLong(2, questionGroupId);
			ResultSet rs = pstm.executeQuery();
			if (rs.next()) {
				incorrectAnswerCount = rs.getInt("number_incorrect_answer");
			}

		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			Messenger.addDangerMessage(ex.getMessage());
		}
		
		return incorrectAnswerCount;
	}
	
	// Update number_correct_question from question_group_question
	public void updateCorrectAnswer(int correctAnswerCount, long questionId, long questionGroupId) {

		String sql = "UPDATE question_group_question "
				   + "SET number_correct_answer = ? "
				   + "WHERE question_group_question.question_id = ? "
				   + "AND question_group_question.question_group_id = ?;";

		try {
			Connection conn = this.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, correctAnswerCount);
			pstm.setLong(2, questionId);
			pstm.setLong(3, questionGroupId);
			pstm.execute();
			pstm.close();
			conn.close();
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			Messenger.addDangerMessage(ex.getMessage());
		}
	}
	
	// Update number_incorrect_question from question_group_question
	public void updateIncorrectAnswer(int incorrectAnswerCount, long questionId, long questionGroupId) {

		String sql = "UPDATE question_group_question "
				   + "SET number_incorrect_answer = ? "
				   + "WHERE question_group_question.question_id = ? "
				   + "AND question_group_question.question_group_id = ?;";

		try {
			Connection conn = this.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, incorrectAnswerCount);
			pstm.setLong(2, questionId);
			pstm.setLong(3, questionGroupId);
			pstm.execute();
			pstm.close();
			conn.close();
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			Messenger.addDangerMessage(ex.getMessage());
		}
	}
	
	// Count for activity_record table
	public int countActivityRecord(User authenticated) {

		int count = 0;
		String sql = "SELECT count(*) AS count FROM activity_record "
				   + "WHERE user_id = ? "
				   + "AND activity_record.activity_date = CURRENT_DATE;";

		try {
			Connection conn = this.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setLong(1, authenticated.getUserId());
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				count = rs.getInt("count");
			}

		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			Messenger.addDangerMessage(ex.getMessage());
		}

		return count;
	}
	
	// Create activity record
	public void createActivityRecord(User authenticated, int numberCorrectAnswer, int numberIncorrectAnswer) {

		String sql = "INSERT INTO activity_record(user_id, activity_date, number_correct_answer, number_incorrect_answer)" 
		           + "values(?,CURRENT_DATE,?,?);";

		try {
			Connection conn = this.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setLong(1, authenticated.getUserId());
			pstm.setInt(2, numberCorrectAnswer);
			pstm.setInt(3, numberIncorrectAnswer);
			pstm.execute();
			pstm.close();
			conn.close();
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			Messenger.addDangerMessage(ex.getMessage());
		}
	}
	
	// Retrieve number_correct_answer by date
	public int retrieveCorrectAnswerCountByDate(User authenticated) {

		int correctAnswerCount = 0;
		String sql = "SELECT * "
				   + "FROM activity_record "
				   + "WHERE activity_record.user_id = ? "
				   + "AND activity_record.activity_date = CURRENT_DATE;";

		try {
			Connection conn = this.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setLong(1, authenticated.getUserId());
			ResultSet rs = pstm.executeQuery();
			if (rs.next()) {
				correctAnswerCount = rs.getInt("number_correct_answer");
			}

		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			Messenger.addDangerMessage(ex.getMessage());
		}
		
		return correctAnswerCount;
	}
	
	// Retrieve number_incorrect_answer by date
	public int retrieveIncorrectAnswerCountByDate(User authenticated) {

		int incorrectAnswerCount = 0;
		String sql = "SELECT * "
				   + "FROM activity_record "
				   + "WHERE activity_record.user_id = ? "
				   + "AND activity_record.activity_date = CURRENT_DATE;";

		try {
			Connection conn = this.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setLong(1, authenticated.getUserId());
			ResultSet rs = pstm.executeQuery();
			if (rs.next()) {
				incorrectAnswerCount = rs.getInt("number_incorrect_answer");
			}

		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			Messenger.addDangerMessage(ex.getMessage());
		}
		
		return incorrectAnswerCount;
	}
	
	// Update number_correct_answer by date
	public void updateCorrectAnswerByDate(int numberCorrectAnswer, User authenticated) {

		String sql = "UPDATE activity_record "
				   + "SET number_correct_answer = ? "
				   + "WHERE activity_record.activity_date = CURRENT_DATE "
				   + "AND activity_record.user_id = ?;";

		try {
			Connection conn = this.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, numberCorrectAnswer);
			pstm.setLong(2, authenticated.getUserId());
			pstm.execute();
			pstm.close();
			conn.close();
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			Messenger.addDangerMessage(ex.getMessage());
		}
	}
	
	// Update number_incorrect_answer by date
	public void updateIncorrectAnswerByDate(int numberIncorrectAnswer, User authenticated) {

		String sql = "UPDATE activity_record "
				   + "SET number_incorrect_answer = ? "
				   + "WHERE activity_record.activity_date = CURRENT_DATE "
				   + "AND activity_record.user_id = ?;";

		try {
			Connection conn = this.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, numberIncorrectAnswer);
			pstm.setLong(2, authenticated.getUserId());
			pstm.execute();
			pstm.close();
			conn.close();
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			Messenger.addDangerMessage(ex.getMessage());
		}
	}
	
	// Retrieve revision date
	public String retrieveRevisionDate(long questionId, long questionGroupId) {

		String revisionDate = "";
		String sql = "SELECT * "
				   + "FROM question_group_question "
				   + "WHERE question_group_question.question_id = ? "
				   + "AND question_group_question.question_group_id = ?;";

		try {
			Connection conn = this.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setLong(1, questionId);
			pstm.setLong(2, questionGroupId);
			ResultSet rs = pstm.executeQuery();
			if (rs.next()) {
				revisionDate = rs.getString("revision_date");
			}

		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			Messenger.addDangerMessage(ex.getMessage());
		}
		
		return revisionDate;
	}
	
	// Update revision_date from question_group_question
	public void updateRevisionDate(String revisionDate, long questionId, long questionGroupId) {
		
		Date formatedRevisionDate = Date.valueOf(revisionDate);

		String sql = "UPDATE question_group_question "
				   + "SET revision_date = ? "
				   + "WHERE question_group_question.question_id = ? "
				   + "AND question_group_question.question_group_id = ?;";

		try {
			Connection conn = this.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setDate(1, formatedRevisionDate);
			pstm.setLong(2, questionId);
			pstm.setLong(3, questionGroupId);
			pstm.execute();
			pstm.close();
			conn.close();
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			Messenger.addDangerMessage(ex.getMessage());
		}
	}
}

