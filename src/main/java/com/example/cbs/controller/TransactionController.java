package com.example.cbs.controller;

import com.example.cbs.domain.Transaction;
import com.example.cbs.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/deposit/{accountId}")
    public ResponseEntity<Transaction> deposit(@PathVariable Long accountId,
                                               @RequestParam BigDecimal amount) {
        return ResponseEntity.ok(transactionService.deposit(accountId, amount));
    }

    @PostMapping("/withdraw/{accountId}")
    public ResponseEntity<Transaction> withdraw(@PathVariable Long accountId,
                                                @RequestParam BigDecimal amount) {
        return ResponseEntity.ok(transactionService.withdraw(accountId, amount));
    }

//    @PostMapping("/transfer")
//    public ResponseEntity<Transaction> transfer(@RequestParam Long fromAccountId,
//                                                @RequestParam Long toAccountId,
//                                                @RequestParam BigDecimal amount) {
//        return ResponseEntity.ok(transactionService.transfer(fromAccountId, toAccountId, amount));
//    }

    @PostMapping("/transfer")
    public ResponseEntity<List<Transaction>> transfer(@RequestParam Long fromAccountId,
                                                      @RequestParam Long toAccountId,
                                                      @RequestParam BigDecimal amount) {
        return ResponseEntity.ok(transactionService.transfer(fromAccountId, toAccountId, amount));
    }


    @GetMapping("/{accountId}")
    public ResponseEntity<List<Transaction>> getTransactions(@PathVariable Long accountId) {
        return ResponseEntity.ok(transactionService.getTransactions(accountId));
    }
}
