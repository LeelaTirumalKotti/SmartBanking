package com.example.cbs.controller;

import com.example.cbs.domain.Account;
import com.example.cbs.dto.AccountResponse;
import com.example.cbs.dto.AuthResponse;
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
    public ResponseEntity<?> openAccount(@PathVariable Long userId,
                                               @RequestParam String accountType) {
        AccountResponse response=accountService.openAccount(userId,accountType);
        if(!response.isSuccess()){
            return ResponseEntity.badRequest().body(response.getMessage());
        }
//        return ResponseEntity.ok(accountService.openAccount(userId, accountType).getAccount());
        return ResponseEntity.ok(response.getAccount());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Account>> getAccounts(@PathVariable Long userId) {
        return ResponseEntity.ok(accountService.getAccountsByUserId(userId));
    }
}
