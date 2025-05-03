package br.com.supersabatina.service;

import org.apache.commons.lang3.StringUtils;

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
			Messenger.addWarningMessage("O nome de usuário já existe no sistema, por favor escolha outro nome.");
			uniqueUserName = false;
		}

		// Is unique email?
		compare = null;
		compare = userDao.retrieveByEmail(user);

		if (StringUtils.isNotEmpty(compare.getEmail())) {
			Messenger.addWarningMessage("O email selecionado já esta cadastrado no sistema, por favor escolha outro email.");
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
				Messenger.addSuccessMessage("Obrigado por criar uma conta conosco! Faça login e comece a utilizar.");
				Messenger.setSuccessTrue();
			} else {
				Messenger.addWarningMessage("Não foi possível completar a solicitação.");
				Messenger.setSuccessTrue();
			}
		}
	}
}
