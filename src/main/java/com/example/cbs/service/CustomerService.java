package com.example.cbs.service;

import com.example.cbs.domain.*;
import com.example.cbs.dto.TransferRequest;
import com.example.cbs.repo.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final AccountRepository accountRepo;
    private final TransactionRepository txnRepo;

    @Transactional
    public Account deposit(Long accountId, Double amount) {
        Account acc = accountRepo.findById(accountId).orElseThrow();
        acc.setBalance(acc.getBalance().add(BigDecimal.valueOf(amount)));

        Transaction txn = Transaction.builder()
                .account(acc)
                .amount(BigDecimal.valueOf(amount))
                .timestamp(LocalDateTime.now())
                .type(TransactionType.DEPOSIT)
                .description("Deposit of " + amount)
                .build();
        txnRepo.save(txn);

        return accountRepo.save(acc);
    }

    @Transactional
    public Account withdraw(Long accountId, Double amount) {
        Account acc = accountRepo.findById(accountId).orElseThrow();
        if (acc.getBalance().compareTo(BigDecimal.valueOf(amount))<0) throw new RuntimeException("Insufficient funds");

        acc.setBalance(acc.getBalance().subtract(BigDecimal.valueOf(amount)));

        Transaction txn = Transaction.builder()
                .account(acc)
                .amount(BigDecimal.valueOf(amount))
                .timestamp(LocalDateTime.now())
                .type(TransactionType.WITHDRAW)
                .description("Withdrawal of " + amount)
                .build();
        txnRepo.save(txn);

        return accountRepo.save(acc);
    }

    @Transactional
    public String transfer(TransferRequest req) {
        Account from = accountRepo.findById(req.getFromAccountId()).orElseThrow();
        Account to = accountRepo.findById(req.getToAccountId()).orElseThrow();

        if (from.getBalance().compareTo(BigDecimal.valueOf(req.getAmount()))<0) throw new RuntimeException("Insufficient funds");

        from.setBalance(from.getBalance().subtract(BigDecimal.valueOf(req.getAmount())));
        to.setBalance(to.getBalance().add(BigDecimal.valueOf(req.getAmount())));

        Transaction txn1 = Transaction.builder()
                .account(from)
                .amount(BigDecimal.valueOf(req.getAmount()))
                .timestamp(LocalDateTime.now())
                .type(TransactionType.TRANSFER)
                .description("Transferred to account " + to.getAccountNumber())
                .build();

        Transaction txn2 = Transaction.builder()
                .account(to)
                .amount(BigDecimal.valueOf(req.getAmount()))
                .timestamp(LocalDateTime.now())
                .type(TransactionType.TRANSFER)
                .description("Received from account " + from.getAccountNumber())
                .build();

        txnRepo.save(txn1);
        txnRepo.save(txn2);

        accountRepo.save(from);
        accountRepo.save(to);

        return "Transfer successful";
    }

    public List<Transaction> getHistory(Long accountId) {
        return txnRepo.findByAccountId(accountId);
    }
}
