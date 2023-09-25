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
				logger.error("Não foi possível fazer a leitura de arquivo de configuração do banco de dados.", ex);
				Messenger.addDangerMessage("Não foi possível fazer a leitura de arquivo de configuração do banco de dados.");
			} catch (NullPointerException ex) {
				logger.error("Não foi possível encontrar o arquivo de configuração do banco de dados.", ex);
				Messenger.addDangerMessage("Não foi possível encontrar o arquivo de configuração do banco de dados.");
			}
		}
		return properties;
	}

	public static String getProperty(String key) {
		try {
			return getProperties().getProperty(key);
		} catch (Exception ex) {
			logger.error("Não foi possível encontrar a chave e o valor solicitado.", ex);
			Messenger.addDangerMessage("Não foi possível encontrar a chave e o valor solicitado.");
			return null;
		}
	}

}
