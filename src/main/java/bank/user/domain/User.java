package bank.user.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import bank.account.domain.Account;
import bank.user.dto.UserDto;

@Entity
@Table(name = "BANK_USER")
public class User {
	@Id
	@SequenceGenerator(allocationSize = 1, name = "bankUserSeq", sequenceName = "B_U_SEQ")
	@GeneratedValue(generator = "bankUserSeq", strategy = GenerationType.SEQUENCE)
	private long id;

	@Column(unique = true)
	private String username;
	private String password;

	@OneToOne(mappedBy="user")
	private Account account;

	public User() {
		super();
	}

	public User(UserDto userDto) {
		super();
		this.username = userDto.getUsername();
		this.password = userDto.getPassword();
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + "]";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
