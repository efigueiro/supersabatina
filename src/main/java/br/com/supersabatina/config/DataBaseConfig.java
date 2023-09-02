package br.com.supersabatina.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.supersabatina.model.dao.BaseDao;
import br.com.supersabatina.util.Messenger;

public class DataBaseConfig {

	final static Logger logger = LogManager.getLogger(BaseDao.class.getName());
	private static Properties properties = null;

	private static Properties getProperties() {
		if (properties == null) {
			InputStream i = DataBaseConfig.class.getResourceAsStream("/database.properties");
			properties = new Properties();
			try {
				properties.load(i);
				i.close();
			} catch (IOException ex) {
				logger.error(MessageConfig.getProperty("ioexception.database.properties"), ex);
				Messenger.addDangerMessage(MessageConfig.getProperty("ioexception.database.properties"));
			} catch (NullPointerException ex) {
				logger.error(MessageConfig.getProperty("nullpointerexception.database.properties"), ex);
				Messenger.addDangerMessage(MessageConfig.getProperty("nullpointerexception.database.properties"));
			}
		}
		return properties;
	}

	public static String getProperty(String key) {
		try {
			return getProperties().getProperty(key);
		} catch (Exception ex) {
			logger.error(MessageConfig.getProperty("exception.properties"), ex);
			Messenger.addDangerMessage(MessageConfig.getProperty("exception.properties"));
			return null;
		}
	}

}
