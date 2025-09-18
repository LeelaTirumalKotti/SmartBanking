package com.example.cbs.dto;

import com.example.cbs.domain.Account;
import lombok.Data;

@Data
public class AccountResponse {
    private boolean success;
    private String message;
    private Account account;

    public AccountResponse(boolean success, String message, Account account) {
        this.success = success;
        this.message = message;
        this.account = account;
    }

    public AccountResponse(boolean success, String message) {
        this(success, message, null);
    }

}