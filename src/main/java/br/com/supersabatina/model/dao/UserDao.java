package br.com.supersabatina.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.supersabatina.model.entity.User;
import br.com.supersabatina.util.Messenger;

public class UserDao extends BaseDao {

	final static Logger logger = LogManager.getLogger(UserDao.class.getName());

	public void create(User user) {

		String sql = "INSERT INTO users(user_name, email, password, avatar, visibility, tutorial, description)"
				+ "values(?,?,?,?,?,?,?);";

		try {
			Connection conn = this.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, user.getUserName());
			pstm.setString(2, user.getEmail());
			pstm.setString(3, user.getPassword());
			pstm.setString(4, user.getAvatar());
			pstm.setString(5, user.getVisibility());
			pstm.setString(6, user.getTutorial());
			pstm.setString(7, user.getDescription());
			pstm.execute();
			pstm.close();
			conn.close();
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			Messenger.addDangerMessage(ex.getMessage());
		}
	}

	public User retrieveByUserName(User user) {

		User userSelected = new User();
		String sql = "SELECT * FROM users WHERE user_name ILIKE ?";

		try {
			Connection conn = this.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, user.getUserName());
			ResultSet rs = pstm.executeQuery();
			if (rs.next()) {
				userSelected.setEmail(rs.getString("email"));
				userSelected.setPassword(rs.getString("password"));
				userSelected.setUserId(rs.getLong("user_id"));
				userSelected.setUserName(rs.getString("user_name"));
				userSelected.setAvatar(rs.getString("avatar"));
				userSelected.setVisibility(rs.getString("visibility"));
				userSelected.setTutorial(rs.getString("tutorial"));
			}

		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			Messenger.addDangerMessage(ex.getMessage());
		}

		return userSelected;
	}

	public User retrieveByEmail(User user) {

		User userSelected = new User();
		String sql = "SELECT * FROM users WHERE email ILIKE ?";

		try {
			Connection conn = this.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, user.getEmail());
			ResultSet rs = pstm.executeQuery();
			if (rs.next()) {
				userSelected.setEmail(rs.getString("email"));
				userSelected.setPassword(rs.getString("password"));
				userSelected.setUserId(rs.getLong("user_id"));
				userSelected.setUserName(rs.getString("user_name"));
				userSelected.setAvatar(rs.getString("avatar"));
				userSelected.setVisibility(rs.getString("visibility"));
				userSelected.setTutorial(rs.getString("tutorial"));
			}

		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			Messenger.addDangerMessage(ex.getMessage());
		}

		return userSelected;
	}

	public void disableTutorial(long userId) {

		String sql = "UPDATE users SET tutorial='no' WHERE user_id=?;";

		try {
			Connection conn = this.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setLong(1, userId);
			pstm.execute();
			pstm.close();
			conn.close();

		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			Messenger.addDangerMessage(ex.getMessage());
		}
	}

}
