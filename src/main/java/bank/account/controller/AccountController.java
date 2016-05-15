package bank.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bank.account.dto.AccountDto;
import bank.account.service.AccountService;

@RestController
@RequestMapping(value = "account")
public class AccountController {

	@Autowired
	private AccountService accountServiceImpl;

	@RequestMapping(value = "findByAccountId", method = { RequestMethod.GET }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<AccountDto> findAccountById(@RequestParam(value = "accountId") long accountId) {

		ResponseEntity<AccountDto> re = null;

		AccountDto accountDto = accountServiceImpl.findAccountByAccountId(accountId);

		if (accountDto != null) {
			re = new ResponseEntity<AccountDto>(accountDto, HttpStatus.OK);
		} else {
			re = new ResponseEntity<AccountDto>(accountDto, HttpStatus.NOT_FOUND);
		}

		return re;
	}

	@RequestMapping(value = "findByUserId", method = { RequestMethod.GET }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<AccountDto> findAccountByUserId(@RequestParam(value = "userId") long userId) {

		ResponseEntity<AccountDto> re = null;
		System.out.println("Getting account: " + userId);

		AccountDto accountDto = accountServiceImpl.findAccountByUserId(userId);
		
		System.out.println(accountDto);

		if (accountDto != null) {
			re = new ResponseEntity<AccountDto>(accountDto, HttpStatus.OK);
		} else {
			re = new ResponseEntity<AccountDto>(accountDto, HttpStatus.NOT_FOUND);
		}

		return re;
	}

	@RequestMapping(value = "deposit", method = { RequestMethod.POST }, consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<AccountDto> deposit(@RequestBody AccountDto accountDto) {

		ResponseEntity<AccountDto> re = null;

		AccountDto responseDto = accountServiceImpl.deposit(accountDto);

		if (accountDto != null) {
			re = new ResponseEntity<AccountDto>(responseDto, HttpStatus.OK);
		} else {
			re = new ResponseEntity<AccountDto>(responseDto, HttpStatus.NOT_FOUND);
		}

		return re;

	}

	@RequestMapping(value = "withdraw", method = { RequestMethod.POST }, consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<AccountDto> withdraw(@RequestBody AccountDto accountDto) {

		ResponseEntity<AccountDto> re = null;

		AccountDto responseDto = accountServiceImpl.withdraw(accountDto);

		if (accountDto != null) {
			re = new ResponseEntity<AccountDto>(responseDto, HttpStatus.OK);
		} else {
			re = new ResponseEntity<AccountDto>(responseDto, HttpStatus.NOT_FOUND);
		}

		return re;
	}

}
