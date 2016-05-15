package bank.user.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bank.user.domain.User;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;


	public User findUserByUsername(String username) {

		User user = (User) sessionFactory.getCurrentSession().createQuery("from User where username = :username")
				.setString("username", username).uniqueResult();

		return user;
	}


	public void createUser(User user) {
		sessionFactory.getCurrentSession().save(user);
	}

	
	public User findUserById(long userId) {
		return (User) sessionFactory.getCurrentSession().createQuery("from User where id = :id").setLong("id", userId).uniqueResult();
	}

}
