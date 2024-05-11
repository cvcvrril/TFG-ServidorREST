package com.example.servidorrestinesmr.data.dao;


import com.example.servidorrestinesmr.data.model.UserResponse;
import com.example.servidorrestinesmr.data.model.entities.UserEntity;
import com.example.servidorrestinesmr.domain.model.error.ErrorSec;
import io.vavr.control.Either;

import java.util.List;

/**
 * @author Inés Martínez Rodríguez
 * <br>
 * Interfaz donde se establecen los métodos para el Dao del objeto UserEntity
 * **/

public interface DaoUser {

    Either<ErrorSec, List<UserEntity>> getAll();
    Either<ErrorSec, UserResponse> getUserById(int id);
}
