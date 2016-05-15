package bank.account.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bank.account.dao.AccountDao;
import bank.account.domain.Account;
import bank.account.dto.AccountDto;
import bank.user.dao.UserDao;

@Service
@Transactional
public class AccountServiceImpl implements AccountService{

	@Autowired
	private AccountDao accountDaoImpl;
	@Autowired
	private UserDao userDaoImpl;

	
	public AccountDto findAccountByUserId(long userId) {
		
		Account acct = accountDaoImpl.findAccountById(userId);
		
		AccountDto accountDto = null;
		
		if(acct != null){
			accountDto = new AccountDto(acct);
		}

		return accountDto;
	}

	
	public AccountDto findAccountByAccountId(long accountId) {
		Account acct = accountDaoImpl.findAccountById(accountId);
		
		AccountDto accountDto = null;
		
		if(acct != null){
			accountDto = new AccountDto(acct);
		}

		return accountDto;
	}

	
	public AccountDto deposit(AccountDto accountDto) {
		Account account = accountDaoImpl.findAccountById(accountDto.getId());
		
		account.setBalance(account.getBalance() + accountDto.getAmount());
		
		accountDaoImpl.updateAccountBalance(account);
		
		return new AccountDto(account);
	}

	
	public AccountDto withdraw(AccountDto accountDto) {
		Account account = accountDaoImpl.findAccountById(accountDto.getId());
		
		account.setBalance(account.getBalance() - accountDto.getAmount());
		
		accountDaoImpl.updateAccountBalance(account);
		
		return new AccountDto(account);
	}

	
	
}
