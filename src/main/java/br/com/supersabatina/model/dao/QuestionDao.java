package br.com.supersabatina.model.dao;

import java.sql.Connection;
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
	
	public Question retrieveByQuestionAndAnswer(Question q, long userId) {

		Question question = new Question();
		User user = new User();
		String sql = "SELECT * FROM question WHERE question=? and answer=? and user_id=?";

		try {
			Connection conn = this.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, q.getQuestion());
			pstm.setString(2, q.getAnswer());
			pstm.setLong(3, userId);
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

	public List<Question> retrieveAll(User authenticated) {

		List<Question> questionList = new ArrayList<Question>();
		String sql = "SELECT * FROM question WHERE user_id = ?";

		try {
			Connection conn = this.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setLong(1, authenticated.getUserId());
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				Question question = new Question();
				question.setQuestionId(rs.getLong("question_id"));
				question.setAnswer(rs.getString("answer"));
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
}
