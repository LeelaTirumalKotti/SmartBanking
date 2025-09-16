package com.example.cbs.service;

import com.example.cbs.domain.Account;
import com.example.cbs.domain.Transaction;
import com.example.cbs.domain.TransactionType;
import com.example.cbs.repo.AccountRepository;
import com.example.cbs.repo.TransactionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountService accountService;
    private final AccountRepository accountRepository;

    @Transactional
    public Transaction deposit(Long accountId, BigDecimal amount) {
        Account account = accountService.getAccountById(accountId);
        account.setBalance(account.getBalance().add(amount));
        accountRepository.save(account);

        Transaction tx = Transaction.builder()
                .account(account)
                .type(TransactionType.DEPOSIT)
                .amount(amount)
                .timestamp(LocalDateTime.now())
                .description("Deposit of " + amount)
                .build();

        return transactionRepository.save(tx);
    }

    @Transactional
    public Transaction withdraw(Long accountId, BigDecimal amount) {
        Account account = accountService.getAccountById(accountId);
        if (account.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Insufficient funds");
        }

        account.setBalance(account.getBalance().subtract(amount));
        accountRepository.save(account);

        Transaction tx = Transaction.builder()
                .account(account)
                .type(TransactionType.WITHDRAW)
                .amount(amount)
                .timestamp(LocalDateTime.now())
                .description("Withdrawal of " + amount)
                .build();

        return transactionRepository.save(tx);
    }

    @Transactional
    public List<Transaction> transfer(Long fromAccountId, Long toAccountId, BigDecimal amount) {
        Account from = accountService.getAccountById(fromAccountId);
        Account to = accountService.getAccountById(toAccountId);

        if (from.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Insufficient funds");
        }

        from.setBalance(from.getBalance().subtract(amount));
        to.setBalance(to.getBalance().add(amount));

        accountRepository.save(from);
        accountRepository.save(to);

        Transaction txFrom = Transaction.builder()
                .account(from)
                .type(TransactionType.TRANSFER)
                .amount(amount)
                .timestamp(LocalDateTime.now())
                .description("Transferred to account " + to.getAccountNumber())
                .build();

        Transaction txTo = Transaction.builder()
                .account(to)
                .type(TransactionType.TRANSFER)
                .amount(amount)
                .timestamp(LocalDateTime.now())
                .description("Received from account " + from.getAccountNumber())
                .build();

        transactionRepository.save(txFrom);
        transactionRepository.save(txTo);

        return List.of(txFrom, txTo);
    }

    public List<Transaction> getTransactions(Long accountId) {
        return transactionRepository.findByAccountId(accountId);
    }
}
