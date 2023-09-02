package br.com.supersabatina.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.supersabatina.config.DataBaseConfig;
import br.com.supersabatina.config.MessageConfig;
import br.com.supersabatina.util.Messenger;

public class BaseDao {

	final static Logger logger = LogManager.getLogger(BaseDao.class.getName());

	public Connection getConnection() {

		try {
			Class.forName(DataBaseConfig.getProperty("driver"));
		} catch (ClassNotFoundException ex) {
			logger.error(MessageConfig.getProperty("classnotfoundexception.driver"), ex);
			Messenger.addDangerMessage(MessageConfig.getProperty("classnotfoundexception.driver"));
		}

		Connection connection = null;

		try {

			connection = DriverManager.getConnection(DataBaseConfig.getProperty("url"),
					DataBaseConfig.getProperty("user"), DataBaseConfig.getProperty("password"));
		} catch (Exception ex) {
			logger.error(MessageConfig.getProperty("exception.connection"), ex);
			Messenger.addDangerMessage(MessageConfig.getProperty("exception.connection"));
		}

		return connection;
	}

}
