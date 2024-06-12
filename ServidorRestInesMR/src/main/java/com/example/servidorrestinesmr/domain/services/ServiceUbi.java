package com.example.servidorrestinesmr.domain.services;

import com.example.servidorrestinesmr.domain.model.NewUbiDTO;
import com.example.servidorrestinesmr.domain.model.UbiDTO;
import com.example.servidorrestinesmr.domain.model.error.ErrorSec;
import io.vavr.control.Either;

import java.util.List;

public interface ServiceUbi {

    Either<ErrorSec, List<UbiDTO>> getAll();
    Either<ErrorSec, UbiDTO> getByIdUbi(int id);
    Either<ErrorSec, List<UbiDTO>> getAllByUserId(int idUser);
    Either<ErrorSec, Integer> deleteUbi(int id);
    Either<ErrorSec, UbiDTO> addUbi(NewUbiDTO nuevaUbi);

}
