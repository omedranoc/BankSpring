package bank.user.dao;

import bank.user.domain.User;

public interface UserDao {
	
	public User findUserByUsername(String username);
	public User findUserById(long userId);
	public void createUser(User user);

}
