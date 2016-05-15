package bank.account.service;

import bank.account.dto.AccountDto;

public interface AccountService {
	
	public AccountDto findAccountByUserId(long userId);
	public AccountDto findAccountByAccountId(long accountId);
	public AccountDto deposit(AccountDto accountDto);
	public AccountDto withdraw(AccountDto accountDto);

}
