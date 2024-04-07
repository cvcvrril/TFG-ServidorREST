package com.example.servidorrestinesmr.domain.services;

import com.example.servidorrestinesmr.domain.model.UbiDTO;
import com.example.servidorrestinesmr.domain.model.error.ErrorSec;
import io.vavr.control.Either;

import java.util.List;

public interface ServiceUbi {

    Either<ErrorSec, List<UbiDTO>> getAll();

}
