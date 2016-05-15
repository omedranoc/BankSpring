package bank.account.dto;

import bank.account.domain.Account;

public class AccountDto {

	private long id;
	private double amount;
	private double balance;
	private long userId;



	public AccountDto(Account account) {
		super();
		this.id = account.getId();
		this.balance = account.getBalance();
		this.userId = account.getUser().getId();
	}

	@Override
	public String toString() {
		return "AccountDto [id=" + id + ", amount=" + amount + ", userId=" + userId + "]";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

}
