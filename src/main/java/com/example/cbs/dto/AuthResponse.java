package com.example.cbs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {
    private String token;
    private String role;
    private long id;

//    public AuthResponse(String token,String role){
//        this.token=token;
//        this.role=role;
//    }
}


