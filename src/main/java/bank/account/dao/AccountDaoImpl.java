package bank.account.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bank.account.domain.Account;

@Repository
public class AccountDaoImpl implements AccountDao {

	@Autowired
	private SessionFactory sessionFactory;

	
	
	public Account findAccountById(long id) {
		return (Account) sessionFactory.getCurrentSession().createQuery("from Account where id = :id").setLong("id", id)
				.uniqueResult();
	}

	
	public Account findAccountByUserId(long userId) {
		return (Account) sessionFactory.getCurrentSession().createQuery("from Account where user.id = :id")
				.setLong("id", userId).uniqueResult();
	}

	
	public void updateAccountBalance(Account account) {
		sessionFactory.getCurrentSession().update(account);
	}

	
	public void createAccount(Account account) {
		sessionFactory.getCurrentSession().save(account);
	}

}
