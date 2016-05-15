package bank.account.dao;

import bank.account.domain.Account;

public interface AccountDao {
	
	public Account findAccountById(long id);
	public Account findAccountByUserId(long userId);
	public void updateAccountBalance(Account account);
	public void createAccount(Account account);
	

}
