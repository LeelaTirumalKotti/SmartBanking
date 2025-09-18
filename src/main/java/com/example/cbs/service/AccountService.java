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
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final  AccountRepository accountRepository;
    private final UserRepository userRepository;
    private String accountType;

    @Transactional
    public Account openAccount(Long userId, String accountType) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id " + userId));

        Account account = new Account();
        account.setUser(user);
        account.setAccountNumber(generateAccountNumber());
        this.accountType=accountType;
        account.setAccountType(AccountType.valueOf(accountType.toUpperCase()));
        account.setBalance(BigDecimal.ZERO);

        return accountRepository.save(account);
    }

//    public void setAccountType(String accountType){
//        this.accountType=accountType;
//    }

    private String generateAccountNumber() {
        if(accountType!=null)
        return  this.accountType.toUpperCase().substring(0,4)+"-"+ UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        return "ACC-"+UUID.randomUUID().toString().substring(0,8);
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
