
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
import br.com.supersabatina.model.entity.User;
import br.com.supersabatina.util.Messenger;

public class QuestionDao extends BaseDao {

	final static Logger logger = LogManager.getLogger(QuestionDao.class.getName());

	public void create(Question question, long questionGroupId) {

		String sql = "INSERT INTO question(user_id, subject, visibility, question, answer)" 
		           + "values(?,?,?,?,?);";

		try {
			Connection conn = this.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setLong(1, question.getUser().getUserId());
			pstm.setString(2, question.getSubject());
			pstm.setString(3, question.getVisibility());
			pstm.setString(4, question.getQuestion());
			pstm.setString(5, question.getAnswer());
			pstm.execute();
			pstm.close();
			conn.close();
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			Messenger.addDangerMessage(ex.getMessage());
		}
	}

	public Question retrieveByQuestionAndUserId(Question q, long userId) {

		Question question = new Question();
		User user = new User();
		String sql = "SELECT * FROM question WHERE question=? and user_id=?";

		try {
			Connection conn = this.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, q.getQuestion());
			pstm.setLong(2, userId);
			ResultSet rs = pstm.executeQuery();
			if (rs.next()) {
				question.setQuestionId(rs.getLong("question_id"));
				question.setAnswer(rs.getString("answer"));
				question.setQuestion(rs.getString("question"));
				question.setSubject(rs.getString("subject"));
				question.setVisibility(rs.getString("visibility"));
				user.setUserId(rs.getLong("user_id"));
				question.setUser(user);
			}

		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			Messenger.addDangerMessage(ex.getMessage());
		}

		return question;
	}

	public List<Question> retrieveAll(String question, User authenticated, int offset) {

		List<Question> questionList = new ArrayList<Question>();
		String sql = "SELECT * "
				   + "FROM question "
				   + "WHERE ((question.question ILIKE ?) OR (question.subject ILIKE ?)) "
				   + "AND ((question.visibility = 'public') OR (question.visibility = 'private' AND question.user_id = ?)) "
				   + "LIMIT 10 OFFSET ?;";

		try {
			Connection conn = this.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, "%" + question + "%");
			pstm.setString(2, "%" + question + "%");
			pstm.setLong(3, authenticated.getUserId());
			pstm.setInt(4, offset);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				Question q = new Question();
				User user = new User();
				q.setQuestionId(rs.getLong("question_id"));
				q.setAnswer(rs.getString("answer"));
				q.setQuestion(rs.getString("question"));
				q.setSubject(rs.getString("subject"));
				q.setVisibility(rs.getString("visibility"));
				user.setUserId(rs.getLong("user_id"));
				q.setUser(user);
				questionList.add(q);
			}

		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			Messenger.addDangerMessage(ex.getMessage());
		}

		return questionList;
	}
	
	public int retrieveAllCount(String question, User authenticated) {
		
		int count = 0;
		String sql = "SELECT count(*) "
				   + "FROM question "
				   + "WHERE ((question.question ILIKE ?) OR (question.subject ILIKE ?)) "
				   + "AND ((question.visibility = 'public') OR (question.visibility = 'private' AND question.user_id = ?))";

		try {
			Connection conn = this.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, "%" + question + "%");
			pstm.setString(2, "%" + question + "%");
			pstm.setLong(3, authenticated.getUserId());
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

	public List<Question> retrievePrivate(String question, User authenticated, int offset) {

		List<Question> questionList = new ArrayList<Question>();
		String sql = "SELECT * "
				   + "FROM question "
				   + "WHERE ((question.question ILIKE ?) OR (question.subject ILIKE ?)) "
				   + "AND user_id = ? "
				   + "LIMIT 10 OFFSET ?;";

		try {
			Connection conn = this.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, "%" + question + "%");
			pstm.setString(2, "%" + question + "%");
			pstm.setLong(3, authenticated.getUserId());
			pstm.setInt(4, offset);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				Question q = new Question();
				User user = new User();
				q.setQuestionId(rs.getLong("question_id"));
				q.setAnswer(rs.getString("answer"));
				q.setQuestion(rs.getString("question"));
				q.setSubject(rs.getString("subject"));
				q.setVisibility(rs.getString("visibility"));
				user.setUserId(rs.getLong("user_id"));
				q.setUser(user);
				questionList.add(q);
			}

		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			Messenger.addDangerMessage(ex.getMessage());
		}

		return questionList;
	}
	
	public int retrievePrivateCount(String question, User authenticated) {

		int count = 0;
		String sql = "SELECT count(*) "
				   + "FROM question "
				   + "WHERE ((question.question ILIKE ?) OR (question.subject ILIKE ?)) "
				   + "AND user_id = ?; ";

		try {
			Connection conn = this.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, "%" + question + "%");
			pstm.setString(2, "%" + question + "%");
			pstm.setLong(3, authenticated.getUserId());
			ResultSet rs = pstm.executeQuery();
			if (rs.next()) {
				count = rs.getInt("count");
			}

		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			Messenger.addDangerMessage(ex.getMessage());
		}

		return count;
	}
	
	public List<Question> retrieveAllByQuestionAndQuestionGroup(String question, long questionGroupId, User authenticated, int offset) {

		List<Question> questionList = new ArrayList<Question>();
		String sql = "SELECT question.* "
				   + "FROM question "
				   + "LEFT JOIN question_group_question "
				   + "ON question.question_id = question_group_question.question_id "
				   + "AND question_group_question.question_group_id = ? "
				   + "WHERE "
				   + "    (question.question ILIKE ? OR question.subject ILIKE ?) "
				   + "    AND ((question.visibility = 'public' OR (question.visibility = 'private' AND question.user_id = ?)) "
				   + "    AND question_group_question.question_id IS NULL) "
				   + "LIMIT 10 OFFSET ?;";

		try {
			Connection conn = this.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setLong(1, questionGroupId);
			pstm.setString(2, "%" + question + "%");
			pstm.setString(3, "%" + question + "%");
			pstm.setLong(4, authenticated.getUserId());
			pstm.setInt(5, offset);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				Question q = new Question();
				User user = new User();
				q.setQuestionId(rs.getLong("question_id"));
				q.setAnswer(rs.getString("answer"));
				q.setQuestion(rs.getString("question"));
				q.setSubject(rs.getString("subject"));
				q.setVisibility(rs.getString("visibility"));
				user.setUserId(rs.getLong("user_id"));
				q.setUser(user);
				questionList.add(q);
			}

		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			Messenger.addDangerMessage(ex.getMessage());
		}

		return questionList;
	}
	
	public int retrieveAllByQuestionAndQuestionGroupCount(String question, long questionGroupId, User authenticated) {
		
		int count = 0;
		String sql = "SELECT COUNT(*) "
				   + "FROM question "
				   + "LEFT JOIN question_group_question "
				   + "ON question.question_id = question_group_question.question_id "
				   + "AND question_group_question.question_group_id = ? "
				   + "WHERE "
				   + "    (question.question ILIKE ? OR question.subject ILIKE ?) "
				   + "    AND ((question.visibility = 'public' OR (question.visibility = 'private' AND question.user_id = ?)) "
				   + "    AND question_group_question.question_id IS NULL);";

		try {
			Connection conn = this.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setLong(1, questionGroupId);
			pstm.setString(2, "%" + question + "%");
			pstm.setString(3, "%" + question + "%");
			pstm.setLong(4, authenticated.getUserId());
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
	
	public List<Question> retrievePrivateByQuestionAndQuestionGroup(String question, long questionGroupId, User authenticated, int offset) {

		List<Question> questionList = new ArrayList<Question>();
		String sql = "SELECT question.* "
				   + "FROM question "
				   + "LEFT JOIN question_group_question "
				   + "ON question.question_id = question_group_question.question_id "
				   + "AND question_group_question.question_group_id = ? "
				   + "WHERE (question.question ILIKE ? OR question.subject ILIKE ?) "
				   + "AND question.user_id = ? "
				   + "AND question_group_question.question_id IS NULL "
				   + "LIMIT 10 "
				   + "OFFSET ?;";

		try {
			Connection conn = this.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setLong(1, questionGroupId);
			pstm.setString(2, "%" + question + "%");
			pstm.setString(3, "%" + question + "%");
			pstm.setLong(4, authenticated.getUserId());
			pstm.setInt(5, offset);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				Question q = new Question();
				User user = new User();
				q.setQuestionId(rs.getLong("question_id"));
				q.setAnswer(rs.getString("answer"));
				q.setQuestion(rs.getString("question"));
				q.setSubject(rs.getString("subject"));
				q.setVisibility(rs.getString("visibility"));
				user.setUserId(rs.getLong("user_id"));
				q.setUser(user);
				questionList.add(q);
			}

		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			Messenger.addDangerMessage(ex.getMessage());
		}

		return questionList;
	}
	
	public int retrievePrivateByQuestionAndQuestionGroupCount(String question, long questionGroupId, User authenticated) {
		
		int count = 0;
		String sql = "SELECT COUNT(*) "
				   + "FROM question "
				   + "LEFT JOIN question_group_question "
				   + "ON question.question_id = question_group_question.question_id "
				   + "AND question_group_question.question_group_id = ? "
				   + "WHERE (question.question ILIKE ? OR question.subject ILIKE ?) "
				   + "AND question.user_id = ? "
				   + "AND question_group_question.question_id IS NULL;";

		try {
			Connection conn = this.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setLong(1, questionGroupId);
			pstm.setString(2, "%" + question + "%");
			pstm.setString(3, "%" + question + "%");
			pstm.setLong(4, authenticated.getUserId());
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
	

	/*
	 * public List<Question> retrievePublicByQuestion(String question, User
	 * authenticated) {
	 * 
	 * List<Question> questionList = new ArrayList<Question>(); String sql =
	 * "SELECT * FROM question WHERE ((question ILIKE ?) or (subject ILIKE ?)) and ((user_id = ?) and (visibility='public'))"
	 * ;
	 * 
	 * try { Connection conn = this.getConnection(); PreparedStatement pstm =
	 * conn.prepareStatement(sql); pstm.setString(1, "%" + question + "%");
	 * pstm.setString(2, "%" + question + "%"); pstm.setLong(3,
	 * authenticated.getUserId()); ResultSet rs = pstm.executeQuery(); while
	 * (rs.next()) { Question q = new Question();
	 * q.setQuestionId(rs.getLong("question_id"));
	 * q.setAnswer(rs.getString("answer")); q.setQuestion(rs.getString("question"));
	 * q.setSubject(rs.getString("subject"));
	 * q.setVisibility(rs.getString("visibility")); questionList.add(q); }
	 * 
	 * } catch (Exception ex) { logger.error(ex.getMessage(), ex);
	 * Messenger.addDangerMessage(ex.getMessage()); }
	 * 
	 * return questionList; }
	 */

	/*
	 * public List<Question> retrieveAll(User authenticated) {
	 * 
	 * List<Question> questionList = new ArrayList<Question>(); String sql =
	 * "SELECT * FROM question WHERE user_id = ?";
	 * 
	 * try { Connection conn = this.getConnection(); PreparedStatement pstm =
	 * conn.prepareStatement(sql); pstm.setLong(1, authenticated.getUserId());
	 * ResultSet rs = pstm.executeQuery(); while (rs.next()) { Question question =
	 * new Question(); question.setQuestionId(rs.getLong("question_id"));
	 * question.setAnswer(rs.getString("answer"));
	 * question.setQuestion(rs.getString("question"));
	 * question.setUser(authenticated);
	 * question.setVisibility(rs.getString("visibility"));
	 * questionList.add(question); }
	 * 
	 * } catch (Exception ex) { logger.error(ex.getMessage(), ex);
	 * Messenger.addDangerMessage(ex.getMessage()); }
	 * 
	 * return questionList; }
	 */

	/*
	 * public int count(User authenticated) {
	 * 
	 * int count = 0; String sql =
	 * "SELECT count(*) AS count FROM question WHERE user_id = ?";
	 * 
	 * try { Connection conn = this.getConnection(); PreparedStatement pstm =
	 * conn.prepareStatement(sql); pstm.setLong(1, authenticated.getUserId());
	 * ResultSet rs = pstm.executeQuery(); while (rs.next()) { count =
	 * rs.getInt("count"); }
	 * 
	 * } catch (Exception ex) { logger.error(ex.getMessage(), ex);
	 * Messenger.addDangerMessage(ex.getMessage()); }
	 * 
	 * return count; }
	 */

	// QuestionGroupQuestion table
	public void createQuestionGroupQuestion(Question question, long questionGroupId) {

		String sql = "INSERT INTO question_group_question(question_id, question_group_id, revision_date, number_correct_answer, number_incorrect_answer)" 
		+ "values(?,?,CURRENT_DATE,?,?);";

		try {
			Connection conn = this.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setLong(1, question.getQuestionId());
			pstm.setLong(2, questionGroupId);
			pstm.setInt(3, 0);
			pstm.setInt(4, 0);
			pstm.execute();
			pstm.close();
			conn.close();
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			Messenger.addDangerMessage(ex.getMessage());
		}
	}

	// QuestionGroupQuestion table
	public void createQuestionGroupQuestion(long questionId, long questionGroupId) {

		String sql = "INSERT INTO question_group_question(question_id, question_group_id, revision_date, number_correct_answer, number_incorrect_answer)" 
		+ "values(?,?,CURRENT_DATE,?,?);";

		try {
			Connection conn = this.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setLong(1, questionId);
			pstm.setLong(2, questionGroupId);
			pstm.setInt(3, 0);
			pstm.setInt(4, 0);
			pstm.execute();
			pstm.close();
			conn.close();
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			Messenger.addDangerMessage(ex.getMessage());
		}
	}
	
	// Retrieve all questions by questionGroupId, authenticated user and offset
	public List<Question> retrieveQuestionList(long questionGroupId, User authenticated, int offset) {

		List<Question> questionList = new ArrayList<Question>();
		String sql = "SELECT question.question_id AS question_id, answer AS answer, question.subject AS subject, question.question AS question, question.visibility AS visibility "
				   + "FROM question_group "
				   + "INNER JOIN question_group_question "
				   + "ON question_group.question_group_id = question_group_question.question_group_id "
				   + "INNER JOIN question "
				   + "ON question.question_id = question_group_question.question_id "
				   + "AND question_group.question_group_id = ? "
				   + "AND question_group.user_id = ? "
				   + "LIMIT 10 OFFSET ? ";

		try {
			Connection conn = this.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setLong(1, questionGroupId);
			pstm.setLong(2, authenticated.getUserId());
			pstm.setInt(3, offset);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				Question question = new Question();
				question.setQuestionId(rs.getLong("question_id"));
				question.setAnswer(rs.getString("answer"));
				question.setSubject(rs.getString("subject"));
				question.setQuestion(rs.getString("question"));
				question.setUser(authenticated);
				question.setVisibility(rs.getString("visibility"));
				questionList.add(question);
			}

		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			Messenger.addDangerMessage(ex.getMessage());
		}
		
		return questionList;
	}
	
	// Retrieve all questions by questionGroupId, authenticated user
	public List<Question> retrieveQuestionList(long questionGroupId, User authenticated) {

		List<Question> questionList = new ArrayList<Question>();
		String sql = "SELECT question.question_id AS question_id, answer AS answer, question.subject AS subject, question.question AS question, question.visibility AS visibility"
				+ " FROM question_group"
				+ " INNER JOIN question_group_question"
				+ " ON question_group.question_group_id = question_group_question.question_group_id"
				+ " INNER JOIN question"
				+ " ON question.question_id = question_group_question.question_id"
				+ " AND question_group.question_group_id = ?"
				+ " AND question_group.user_id = ?";

		try {
			Connection conn = this.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setLong(1, questionGroupId);
			pstm.setLong(2, authenticated.getUserId());
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				Question question = new Question();
				question.setQuestionId(rs.getLong("question_id"));
				question.setAnswer(rs.getString("answer"));
				question.setSubject(rs.getString("subject"));
				question.setQuestion(rs.getString("question"));
				question.setUser(authenticated);
				question.setVisibility(rs.getString("visibility"));
				questionList.add(question);
			}

		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			Messenger.addDangerMessage(ex.getMessage());
		}
		
		return questionList;
	}
	
	// Retrieve count all questions by questionGroupId and authenticated user
	public int retrieveQuestionListCount(long questionGroupId, User authenticated) {

		int count = 0;
		String sql = "SELECT COUNT(*)"
				+ " FROM question_group"
				+ " INNER JOIN question_group_question"
				+ " ON question_group.question_group_id = question_group_question.question_group_id"
				+ " INNER JOIN question"
				+ " ON question.question_id = question_group_question.question_id"
				+ " AND question_group.question_group_id = ?"
				+ " AND question_group.user_id = ?";

		try {
			Connection conn = this.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setLong(1, questionGroupId);
			pstm.setLong(2, authenticated.getUserId());
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
	
	// Retrieve a question object by questionGroupId, questionId and authenticated user
	public Question retrieveQuestion(long questionGroupId, long questionId, User authenticated) {

		Question question = new Question();
		String sql = "SELECT * "
				   + "FROM question "
				   + "INNER JOIN question_group_question ON question.question_id = question_group_question.question_id "
				   + "WHERE question.question_id = ? "
				   + "AND question_group_question.question_group_id = ? "
		           + "AND question.user_id = ?;";

		try {
			Connection conn = this.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setLong(1, questionId);
			pstm.setLong(2, questionGroupId);
			pstm.setLong(3, authenticated.getUserId());
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
	
	// Retrieve question by question id
	public Question retrieveQuestionById(long questionId) {

		Question question = new Question();
		String sql = "SELECT * "
				   + "FROM question "
				   + "WHERE question.question_id = ?; ";

		try {
			Connection conn = this.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setLong(1, questionId);
			ResultSet rs = pstm.executeQuery();
			if (rs.next()) {
				question.setQuestionId(rs.getLong("question_id"));
				question.setAnswer(rs.getString("answer"));
				question.setSubject(rs.getString("subject"));
				question.setQuestion(rs.getString("question"));
				question.setVisibility(rs.getString("visibility"));
			}

		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			Messenger.addDangerMessage(ex.getMessage());
		}
		
		return question;
	}
	
	// Update question by question_id
	public void update(Question question) {

		String sql = "UPDATE question "
				   + "SET visibility = ? , "
				   + "subject = ?, "
				   + "question = ?, "
				   + "answer = ? "
				   + "WHERE question_id = ?;";

		try {
			Connection conn = this.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, question.getVisibility());
			pstm.setString(2, question.getSubject());
			pstm.setString(3, question.getQuestion());
			pstm.setString(4, question.getAnswer());
			pstm.setLong(5, question.getQuestionId());
			pstm.execute();
			pstm.close();
			conn.close();
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			Messenger.addDangerMessage(ex.getMessage());
		}
	}
	
	// Delete question
	public void deleteFromQuestionGroupQuestion(Question question) {

		String sql = "DELETE FROM question_group_question " 
		           + "WHERE question_group_question.question_id = ?";
		
		try {
			Connection conn = this.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setLong(1, question.getQuestionId());
			pstm.execute();
			pstm.close();
			conn.close();
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			Messenger.addDangerMessage(ex.getMessage());
		}
	}
	
	// Delete question
	public void delete(Question question) {
		
		this.deleteFromQuestionGroupQuestion(question);

		String sql = "DELETE FROM question " 
		           + "WHERE question.question_id = ?";
		
		try {
			Connection conn = this.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setLong(1, question.getQuestionId());
			pstm.execute();
			pstm.close();
			conn.close();
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			Messenger.addDangerMessage(ex.getMessage());
		}
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

