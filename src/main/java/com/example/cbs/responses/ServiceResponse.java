package com.example.cbs.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceResponse<T> {
    private String status;
    private String message;
    private T data;
}
