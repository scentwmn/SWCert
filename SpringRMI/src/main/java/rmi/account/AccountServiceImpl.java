package rmi.account;

import java.util.ArrayList;
import java.util.List;

public class AccountServiceImpl implements AccountService {
	
	private static List<Account> accounts = new ArrayList<Account>();

	public void insertAccount(Account acc) {
		accounts.add(acc);
		
		System.out.println("[server] accounts size : " + (accounts==null? 0 : accounts.size()));
	}

	public List<Account> getAccounts(String name) {
		return accounts;
	}

}