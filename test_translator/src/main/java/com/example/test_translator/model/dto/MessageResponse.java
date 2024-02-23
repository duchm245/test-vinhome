package com.example.test_translator.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
public class MessageResponse {
    String message;
    int status = 200;
    Object data;
    long totalPages = 0;
    long totalItems = 0;


    public MessageResponse(String message, int status, Object data) {
        this.message = message;
        this.status = status;
        this.data = data;
    }
}
