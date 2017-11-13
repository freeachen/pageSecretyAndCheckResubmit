package com.pingan.bill.sysmanage.org.service;

import com.pingan.bill.sysmanage.org.entity.Account;
public interface AccountService extends BaseService<Account> {
	 public Account login(Account account);
}
