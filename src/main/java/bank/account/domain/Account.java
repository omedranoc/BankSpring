package bank.account.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import bank.account.dto.AccountDto;
import bank.user.domain.User;

@Entity
@Table(name="BANK_ACCOUNT")
public class Account {
	
	@Id
	@Column(name="B_A_ID")
	@SequenceGenerator(name="bankAccountSeq", sequenceName="BANK_ACCOUNT_SEQ",allocationSize=1)
	@GeneratedValue(generator="bankAccountSeq",strategy=GenerationType.SEQUENCE)
	private long id;
	
	private double balance;
	
	@OneToOne()
	private User user;
	
	
	

	public Account() {
		super();
	}
	
	

	public Account(AccountDto accountDto, User user) {
		super();
		this.id = accountDto.getId();
		this.balance = accountDto.getBalance();
		this.user = user;
	}



	@Override
	public String toString() {
		return "Account [id=" + id + ", balance=" + balance + ", user=" + user + "]";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	

}
