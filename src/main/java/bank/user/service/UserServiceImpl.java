package bank.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bank.account.dao.AccountDao;
import bank.account.domain.Account;
import bank.user.dao.UserDao;
import bank.user.domain.User;
import bank.user.dto.UserDto;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDaoImpl;
	
	@Autowired
	private AccountDao accountDaoImpl;

	
	public UserDto authenticateUser(UserDto userDto) {

		User user = userDaoImpl.findUserByUsername(userDto.getUsername());

		if (user != null) {

			UserDto userFromDb = new UserDto(userDaoImpl.findUserByUsername(userDto.getUsername()), false);

			if (userFromDb != null && userFromDb.getPassword().equals(userDto.getPassword())) {
				userDto.setId(userFromDb.getId());
				userDto.setAuthenticated(true);
			}

		} else {
			userDto.setAuthenticated(false);
		}

		return userDto;
	}


	public void registerUser(UserDto userDto) {

		User user = new User(userDto);

		userDaoImpl.createUser(user);

		userDto.setId(user.getId());
		
		Account acct = new Account();
		
		acct.setUser(user);
		
		accountDaoImpl.createAccount(acct);

	}

}
