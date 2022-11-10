package com.bankaccount.application.entity.repository;

import com.bankaccount.application.entity.query.BankAccountQueryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountRepository extends CrudRepository<BankAccountQueryEntity, String> {
}
