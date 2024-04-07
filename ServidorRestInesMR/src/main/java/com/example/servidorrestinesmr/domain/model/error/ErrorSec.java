package com.example.servidorrestinesmr.domain.model.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorSec {

    private int numError;
    private String message;
    private LocalDateTime dateStamp;

}
