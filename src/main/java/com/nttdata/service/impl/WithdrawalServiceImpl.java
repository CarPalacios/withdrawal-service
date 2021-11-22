package com.nttdata.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.model.Withdrawal;
import com.nttdata.repository.IRepository;
import com.nttdata.repository.IWithdrawalRepository;
import com.nttdata.service.IWithdrawalService;

@Service
public class WithdrawalServiceImpl extends CRUDServiceImpl<Withdrawal, String> implements IWithdrawalService {

	@Autowired
	private IWithdrawalRepository repository;

	@Override
	protected IRepository<Withdrawal, String> getRepository() {
		return repository;
	}

}