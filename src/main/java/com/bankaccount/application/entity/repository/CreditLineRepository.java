package com.bankaccount.application.entity.repository;

import com.bankaccount.application.entity.CreditLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditLineRepository extends JpaRepository<CreditLine, String> {
}
