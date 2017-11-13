package com.pingan.bill.sysmanage.org.service.impl;

import org.springframework.stereotype.Service;

import com.pingan.bill.sysmanage.org.entity.Account;
import com.pingan.bill.sysmanage.org.service.AccountService;


@Service("accountService")
public class AccountServiceImpl extends BaseServiceImpl<Account> implements AccountService {
	
	public Account login(Account account) {
		return accountMapper.login(account); //独有的,早base中没有的, 要用自己的mapper
	}
	
}
