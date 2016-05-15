package bank.user.service;

import bank.user.dto.UserDto;

public interface UserService {
	
	public UserDto authenticateUser(UserDto userDto);
	public void registerUser(UserDto userDto);

}
