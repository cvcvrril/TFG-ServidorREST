package com.example.servidorrestinesmr.data.dao;

import com.example.servidorrestinesmr.domain.model.UbiDTO;
import com.example.servidorrestinesmr.domain.model.error.ErrorSec;
import io.vavr.control.Either;

import java.util.List;

public interface DaoUbi {

    Either<ErrorSec, List<UbiDTO>> getAll();

    Either<ErrorSec, UbiDTO> getByIdUbi(int id);

}
