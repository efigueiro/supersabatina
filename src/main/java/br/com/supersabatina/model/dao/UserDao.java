package br.com.supersabatina.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.supersabatina.config.MessageConfig;
import br.com.supersabatina.model.entity.User;

public class UserDao extends BaseDao {

	final static Logger logger = LogManager.getLogger(UserDao.class.getName());

	// review
	public String create(User user) throws Exception {
		String message = "";
		Connection conn = this.getConnection();
		String sql = "insert into users(email, password, userName)" + "values(?,?,?);";
		try {
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, user.getEmail());
			pstm.setString(2, user.getPassword());
			pstm.setString(3, user.getUserName());
			pstm.execute();
			pstm.close();
			conn.close();

			message = MessageConfig.getProperty("message.success");
		} catch (Exception e) {
			message = e + " " + MessageConfig.getProperty("message.error");
			conn.close();
		}
		return message;

	}

	public User retrieveByUserName(User user) {
		
		User userSelected = new User();
		String sql = "select * from users where userName = ?";

		try {
			Connection conn = this.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, user.getUserName());
			ResultSet rs = pstm.executeQuery();
			if(rs.next()) {
				userSelected.setEmail(rs.getString("email"));
				userSelected.setPassword(rs.getString("password"));
				userSelected.setUserId(rs.getLong("user_id"));
				userSelected.setUserName(rs.getString("userName"));
			}
			
		} catch (Exception e) {
			
		}

		return userSelected;

	}

	public User retrieveByEmail(User user) {
		return null;
	}
}
