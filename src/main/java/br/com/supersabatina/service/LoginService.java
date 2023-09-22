package br.com.supersabatina.service;

import org.apache.commons.lang3.StringUtils;

import br.com.supersabatina.model.dao.UserDao;
import br.com.supersabatina.model.entity.User;
import br.com.supersabatina.util.Encrypt;
import br.com.supersabatina.util.Messenger;

public class LoginService {

	public User login(User user) {

		User authenticated = new User();
		UserDao userDao = new UserDao();
		String comparePassword = "";

		// Get user password, encrypt and store it into compare password variable
		if (StringUtils.isNotEmpty(user.getPassword())) {
			comparePassword = Encrypt.encrypt(user.getPassword());
		}

		// Find user
		authenticated = userDao.retrieveByEmail(user);

		// Check if user was found and password match

		if (StringUtils.isNotEmpty(authenticated.getPassword())) {

			if (comparePassword.equals(authenticated.getPassword())) {
				Messenger.setSuccessTrue();
			} else {
				Messenger.addWarningMessage("Usuário ou password incorreto.");
				Messenger.setSuccessFalse();
			}

		} else {
			Messenger.addWarningMessage("Usuário ou password incorreto.");
			Messenger.setSuccessFalse();
		}

		return authenticated;
	}
}
