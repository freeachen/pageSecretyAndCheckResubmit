package com.pingan.bill.sysmanage.org.dao;

import com.pingan.bill.sysmanage.org.entity.Account;



public interface AccountMapper extends BaseMapper<Account> {
	public Account login(Account account);

}