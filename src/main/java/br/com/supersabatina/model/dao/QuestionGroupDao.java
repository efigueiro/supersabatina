package br.com.supersabatina.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.supersabatina.model.entity.QuestionGroup;
import br.com.supersabatina.model.entity.User;
import br.com.supersabatina.util.Messenger;

public class QuestionGroupDao extends BaseDao {

	final static Logger logger = LogManager.getLogger(QuestionGroupDao.class.getName());

	public void create(QuestionGroup questionGroup) {

		String sql = "INSERT INTO question_group(user_id, title, description)" + "values(?,?,?);";

		try {
			Connection conn = this.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setLong(1, questionGroup.getUser().getUserId());
			pstm.setString(2, questionGroup.getTitle());
			pstm.setString(3, questionGroup.getDescription());
			pstm.execute();
			pstm.close();
			conn.close();
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			Messenger.addDangerMessage(ex.getMessage());
		}
	}

	public List<QuestionGroup> retrieveByTitle(String title, User authenticated) {

		List<QuestionGroup> questionGroupList = new ArrayList<QuestionGroup>();
		String sql = "SELECT * FROM question_group WHERE title ILIKE ? and user_id = ?";

		try {
			Connection conn = this.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, "%" + title + "%");
			pstm.setLong(2, authenticated.getUserId());
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				QuestionGroup questionGroup = new QuestionGroup();
				questionGroup.setQuestionGroupId(rs.getLong("question_group_id"));
				questionGroup.setTitle(rs.getString("title"));
				questionGroup.setDescription(rs.getString("description"));
				questionGroupList.add(questionGroup);
			}

		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			Messenger.addDangerMessage(ex.getMessage());
		}

		return questionGroupList;
	}

	public QuestionGroup retrieveById(long questionGroupId, long userId) {

		QuestionGroup questionGroupSelected = new QuestionGroup();
		String sql = "SELECT * FROM question_group WHERE question_group_id=? and user_id=?";

		try {
			Connection conn = this.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setLong(1, questionGroupId);
			pstm.setLong(2, userId);
			ResultSet rs = pstm.executeQuery();
			if (rs.next()) {
				questionGroupSelected.setQuestionGroupId(rs.getLong("question_group_id"));
				questionGroupSelected.setTitle(rs.getString("title"));
				questionGroupSelected.setDescription(rs.getString("description"));
			}

		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			Messenger.addDangerMessage(ex.getMessage());
		}

		return questionGroupSelected;
	}

	public void update(QuestionGroup questionGroup) {

		String sql = "UPDATE question_group SET title=?, description=? WHERE user_id=? and question_group_id=?;";

		try {
			Connection conn = this.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, questionGroup.getTitle());
			pstm.setString(2, questionGroup.getDescription());
			pstm.setLong(3, questionGroup.getUser().getUserId());
			pstm.setLong(4, questionGroup.getQuestionGroupId());
			pstm.execute();
			pstm.close();
			conn.close();
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			Messenger.addDangerMessage(ex.getMessage());
		}
	}

	public void delete(long questionGroupId, long userId) {

		String sql = "DELETE FROM question_group WHERE question_group_id=? and user_id=?;";

		try {
			Connection conn = this.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setLong(1, questionGroupId);
			pstm.setLong(2, userId);
			pstm.execute();
			pstm.close();
			conn.close();
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			Messenger.addDangerMessage(ex.getMessage());
		}
	}
}
