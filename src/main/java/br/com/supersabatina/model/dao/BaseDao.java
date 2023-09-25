package br.com.supersabatina.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.supersabatina.config.DataBaseConfig;
import br.com.supersabatina.util.Messenger;

public class BaseDao {

	final static Logger logger = LogManager.getLogger(BaseDao.class.getName());

	public Connection getConnection() {

		try {
			Class.forName(DataBaseConfig.getProperty("driver"));
		} catch (ClassNotFoundException ex) {
			logger.error("Não foi possível carregar driver de banco de dados.", ex);
			Messenger.addDangerMessage("Não foi possível carregar driver de banco de dados.");
		}

		Connection connection = null;

		try {

			connection = DriverManager.getConnection(DataBaseConfig.getProperty("url"),
					DataBaseConfig.getProperty("user"), DataBaseConfig.getProperty("password"));
		} catch (Exception ex) {
			logger.error("Problemas para realizar conexão com o banco de dados.", ex);
			Messenger.addDangerMessage("Problemas para realizar conexão com o banco de dados.");
		}

		return connection;
	}

}
