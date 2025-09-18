package com.example.cbs.service;

import com.example.cbs.domain.Account;
import com.example.cbs.domain.AccountType;
import com.example.cbs.domain.User;
import com.example.cbs.dto.AccountResponse;
import com.example.cbs.repo.AccountRepository;
import com.example.cbs.repo.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final  AccountRepository accountRepository;
    private final UserRepository userRepository;
    private AccountType accountType;

    @Transactional
    public AccountResponse openAccount(Long userId, String accountType) {
        User user = userRepository.findById(userId)
                .orElse(null);

        if (user == null) {
            return new AccountResponse(false, "User not found with id " + userId);
        }

        AccountType type;
        try {
            type = AccountType.valueOf(accountType.toUpperCase());
        } catch (IllegalArgumentException e) {
            return new AccountResponse(false, "Invalid account type: " + accountType);
        }

        boolean exists = accountRepository.existsByUserIdAndAccountType(userId, type);
        if (exists) {
            return new AccountResponse(false, "User already has an account of type " + accountType);
        }

        Account account = new Account();
        account.setUser(user);
        account.setAccountType(type);
        account.setAccountNumber(generateAccountNumber(type));
        account.setBalance(BigDecimal.ZERO);

        Account savedAccount = accountRepository.save(account);
        return new AccountResponse(true, "Account created successfully", savedAccount);
    }

//    public void setAccountType(String accountType){
//        this.accountType=accountType;
//    }

//    private String generateAccountNumber(AccountType type) {
//        if(accountType!=null)
//            return  accountType.name().toUpperCase().substring(0,4)+"-"+ UUID.randomUUID().toString().substring(0, 8).toUpperCase();
//        return "ACC-"+UUID.randomUUID().toString().substring(0,8);
//    }


    private String generateAccountNumber(AccountType accountType) {
        return accountType.name().substring(0, 4) + "-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
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
