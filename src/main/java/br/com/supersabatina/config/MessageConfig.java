package br.com.supersabatina.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.supersabatina.model.dao.BaseDao;
import br.com.supersabatina.util.Messenger;

public class MessageConfig {

	final static Logger logger = LogManager.getLogger(BaseDao.class.getName());
	private static Properties properties = null;

	private static Properties getProperties() {
		if (properties == null) {
			InputStream i = MessageConfig.class.getResourceAsStream("/application.properties");
			properties = new Properties();
			try {
				properties.load(i);
				i.close();
			} catch (IOException ex) {
				// Messages for exceptions have been added here as for this exception the file must not exist to load the messages.
				logger.error("Não foi possível fazer a leitura do arquivo de configurações do sistema.", ex);
				Messenger.addDangerMessage("Não foi possível fazer a leitura do arquivo de configurações do sistema.");
			} catch (NullPointerException ex) {
				// Messages for exceptions have been added here as for this exception the file must not exist to load the messages.
				logger.error("Não foi possível encontrar o arquivo de configurações do sistema.", ex);
				Messenger.addDangerMessage("Não foi possível encontrar o arquivo de configurações do sistema.");
			}
		}
		return properties;
	}

	public static String getProperty(String key) {
		try {
			return getProperties().getProperty(key);
		} catch (Exception ex) {
			// Messages for exceptions have been added here as for this exception the file must not exist to load the messages.
			logger.error("Não foi possível encontrar a chave e o valor solicitado.", ex);
			Messenger.addDangerMessage("Não foi possível encontrar a chave e o valor solicitado.");
			return null;
		}
	}

}
