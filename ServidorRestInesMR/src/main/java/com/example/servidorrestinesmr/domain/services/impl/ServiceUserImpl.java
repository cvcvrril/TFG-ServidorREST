package com.example.servidorrestinesmr.domain.services.impl;


import com.example.servidorrestinesmr.data.dao.DaoUser;
import com.example.servidorrestinesmr.data.model.UserResponse;
import com.example.servidorrestinesmr.domain.model.error.ErrorSec;
import com.example.servidorrestinesmr.domain.services.ServiceUser;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ServiceUserImpl implements ServiceUser {

    private final DaoUser daoUser;

    @Override
    public Either<ErrorSec, UserResponse> getUserById(int id) {
        return daoUser.getUserById(id);
    }

}
