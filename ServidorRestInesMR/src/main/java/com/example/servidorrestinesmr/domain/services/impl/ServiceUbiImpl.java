package com.example.servidorrestinesmr.domain.services.impl;

import com.example.servidorrestinesmr.data.dao.DaoUbi;
import com.example.servidorrestinesmr.domain.model.UbiDTO;
import com.example.servidorrestinesmr.domain.model.error.ErrorSec;
import com.example.servidorrestinesmr.domain.services.ServiceUbi;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceUbiImpl implements ServiceUbi {

    private final DaoUbi daoUbi;

    @Override
    public Either<ErrorSec, List<UbiDTO>> getAll() {
        return daoUbi.getAll();
    }

    @Override
    public Either<ErrorSec, UbiDTO> getByIdUbi(int id) {
        return daoUbi.getByIdUbi(id);
    }
}
