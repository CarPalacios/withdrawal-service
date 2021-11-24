package com.nttdata.service.impl;

import com.nttdata.model.Withdrawal;
import com.nttdata.repository.Repository;
import com.nttdata.repository.WithdrawalRepository;
import com.nttdata.service.WithdrawalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**La clase WithdrawalServiceImpl extiende a import lombok.Data; e implementa IWithdrawalService.*/
@Service
public class WithdrawalServiceImpl extends    
    CrudServiceImpl<Withdrawal, String> implements WithdrawalService {

  @Autowired
  private WithdrawalRepository repository;

  @Override
  protected Repository<Withdrawal, String> getRepository() {
    return repository;
  }

}