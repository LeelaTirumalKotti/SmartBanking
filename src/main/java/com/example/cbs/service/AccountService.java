package com.example.cbs.service;

import com.example.cbs.domain.Account;
import com.example.cbs.domain.AccountType;
import com.example.cbs.domain.User;
import com.example.cbs.repo.AccountRepository;
import com.example.cbs.repo.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final  AccountRepository accountRepository;
    private final UserRepository userRepository;


    @Transactional
    public Account openAccount(Long userId, String accountType) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id " + userId));

        Account account = new Account();
        account.setUser(user);
        account.setAccountType(AccountType.valueOf(accountType.toUpperCase()));
        account.setBalance(BigDecimal.ZERO);

        return accountRepository.save(account);
    }


    public List<Account> getAccountsByUserId(Long userId) {
        return accountRepository.findByUserId(userId);
    }


    public Account getAccountById(Long accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found with id " + accountId));
    }


    @Transactional
    public Account updateBalance(Long accountId, BigDecimal newBalance) {
        Account account = getAccountById(accountId);
        account.setBalance(newBalance);
        return accountRepository.save(account);
    }
}
