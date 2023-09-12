package br.com.supersabatina.service;

import org.apache.commons.lang3.StringUtils;

import br.com.supersabatina.config.MessageConfig;
import br.com.supersabatina.model.dao.UserDao;
import br.com.supersabatina.model.entity.User;
import br.com.supersabatina.util.Encrypt;
import br.com.supersabatina.util.Messenger;

public class UserService {

	public void createUser(User user) {

		UserDao userDao = new UserDao();
		User compare = new User();
		boolean uniqueUserName = true;
		boolean uniqueEmail = true;

		// Is unique user name?
		compare = userDao.retrieveByUserName(user);

		if (StringUtils.isNotEmpty(compare.getUserName())) {
			Messenger.addWarningMessage(MessageConfig.getProperty("message.username.unique"));
			uniqueUserName = false;
		}

		// Is unique email?
		compare = null;
		compare = userDao.retrieveByEmail(user);

		if (StringUtils.isNotEmpty(compare.getEmail())) {
			Messenger.addWarningMessage(MessageConfig.getProperty("message.email.unique"));
			uniqueEmail = false;
		}

		// Encrypting user password, creating user and checking if user was created.
		if (uniqueEmail && uniqueUserName) {

			String newPassword = Encrypt.encrypt(user.getPassword());
			user.setPassword(newPassword);

			userDao.create(user);

			// Checking if user was created
			compare = userDao.retrieveByEmail(user);
			if (StringUtils.isNotEmpty(compare.getEmail())) {
				Messenger.addSuccessMessage(MessageConfig.getProperty("message.success.accountcreated"));
				Messenger.setSuccessTrue();
			} else {
				Messenger.addWarningMessage(MessageConfig.getProperty("message.fail"));
				Messenger.setSuccessTrue();
			}
		}
	}
}
