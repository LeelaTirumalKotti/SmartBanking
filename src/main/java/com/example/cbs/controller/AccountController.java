package com.example.cbs.controller;

import com.example.cbs.domain.Account;
import com.example.cbs.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/open/{userId}")
    public ResponseEntity<Account> openAccount(@PathVariable Long userId,
                                               @RequestParam String accountType) {
        return ResponseEntity.ok(accountService.openAccount(userId, accountType));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Account>> getAccounts(@PathVariable Long userId) {
        return ResponseEntity.ok(accountService.getAccountsByUserId(userId));
    }
}
