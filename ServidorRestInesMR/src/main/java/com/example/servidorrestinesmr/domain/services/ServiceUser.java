package com.example.servidorrestinesmr.domain.services;

import com.example.servidorrestinesmr.data.model.UserResponse;
import com.example.servidorrestinesmr.domain.model.error.ErrorSec;
import io.vavr.control.Either;

import java.util.List;

public interface ServiceUser {
    Either<ErrorSec, UserResponse> getUserById(int id);
}
