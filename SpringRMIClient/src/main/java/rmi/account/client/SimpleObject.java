package rmi.account.client;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import rmi.account.Account;
import rmi.account.AccountService;

public class SimpleObject {
	AccountService accountService;

	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

	public void addAccount(String name) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContextClient.xml");
		AccountService accountService = (AccountService) context.getBean("accountService");
        
		Account account = new Account();
		account.setName(name);
		
		accountService.insertAccount(account);
	}
	
	public void printAccounts() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContextClient.xml");
		AccountService accountService = (AccountService) context.getBean("accountService");

		List<Account> accounts = accountService.getAccounts("");
		for (Account account : accounts) {
			System.out.println(account);
		}
	}

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContextClient.xml");

		SimpleObject obj = new SimpleObject();
		
		obj.addAccount("ABC");
		obj.addAccount("DEF");
		
		obj.printAccounts();
	}
}
