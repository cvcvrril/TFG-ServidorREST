package com.example.servidorrestinesmr.domain.services.impl;

import com.example.servidorrestinesmr.data.dao.DaoUbi;
import com.example.servidorrestinesmr.data.model.entities.UbiEntity;
import com.example.servidorrestinesmr.domain.model.UbiDTO;
import com.example.servidorrestinesmr.domain.services.ServiceUbi;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@RequiredArgsConstructor
class ServiceUbiImplTest {

    @Autowired
    private ServiceUbi serviceUbi;
    @MockBean
    private DaoUbi daoUbi;


    @Test
    void getAll() {

        // Configurar el comportamiento de daoUbi.getAll() para devolver un valor simulado
        List<UbiDTO> mockedUbiList = new ArrayList<>(); // Puedes llenar esta lista con datos simulados
        when(daoUbi.getAll()).thenReturn(Either.right(mockedUbiList));

        // Simulando datos, NO ES DEFINITIVO
        mockedUbiList.add(new UbiDTO(46, 40.42436829348128, -3.7009334002974414));
        mockedUbiList.add(new UbiDTO(57, 40.42280310370885, -3.7062382472899285));

        // Llamar al método que estás probando
        List<UbiDTO> userEntityList = serviceUbi.getAll().getOrElseThrow(() -> new RuntimeException("Me cago en mi desgracia"));

        // Verificar el resultado esperado
        assertThat(userEntityList).size().isPositive();
    }
}