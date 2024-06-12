package com.example.servidorrestinesmr.domain.services.impl;

import com.example.servidorrestinesmr.data.dao.DaoUbi;
import com.example.servidorrestinesmr.data.model.entities.UbiEntity;
import com.example.servidorrestinesmr.domain.model.NewUbiDTO;
import com.example.servidorrestinesmr.domain.model.UbiDTO;
import com.example.servidorrestinesmr.domain.model.error.ErrorSec;
import com.example.servidorrestinesmr.domain.services.ServiceUbi;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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

    @Mock
    private DaoUbi daoUbi;
    private ServiceUbi serviceUbi;

    @BeforeEach
    void setUp() {
        serviceUbi = new ServiceUbiImpl(daoUbi);
    }


    /**
     * Test 1 - getAll
     **/

    @Test
    void getAll() {

        // Configurar el comportamiento de daoUbi.getAll() para devolver un valor simulado
        List<UbiDTO> mockedUbiList = new ArrayList<>();
        when(daoUbi.getAll()).thenReturn(Either.right(mockedUbiList));

        // Datos simulados
        mockedUbiList.add(new UbiDTO(46, 40.42436829348128, -3.7009334002974414, "Bar Pepe"));
        mockedUbiList.add(new UbiDTO(57, 40.42280310370885, -3.7062382472899285, "Bar Lisa"));

        // Llamar al método que estás probando
        List<UbiDTO> userEntityList = serviceUbi.getAll().getOrElseThrow(() -> new RuntimeException());
        Either<ErrorSec, List<UbiDTO>> result = serviceUbi.getAll();

        // Verificar el resultado esperado
        assertThat(result.get()).hasSize(2);
        assertThat(result.isRight()).isTrue();
    }

    /**
     * Test 2 - getByIdUbi
     **/

    @Test
    void getByIdUbi() {
        // Datos simulados
        int id = 1;
        UbiDTO mockedUbi = new UbiDTO(1, 40.123, -3.456, "Bar Pepe");

        // Configurar el comportamiento del DAO
        when(daoUbi.getByIdUbi(id)).thenReturn(Either.right(mockedUbi));

        // Llamar al método que estás probando
        Either<ErrorSec, UbiDTO> result = serviceUbi.getByIdUbi(id);

        // Verificar el resultado esperado
        assertThat(result.isRight()).isTrue();
        assertThat(result.get()).isEqualTo(mockedUbi);
    }

    /**
     * Test 3 - getAllByUserId
     **/

    @Test
    void getAllByUserId() {
        // Datos simulados
        int userId = 1;
        List<UbiDTO> mockedUbiList = new ArrayList<>();
        mockedUbiList.add(new UbiDTO(1, 40.123, -3.456, "Bar Pepe"));
        mockedUbiList.add(new UbiDTO(2, 40.789, -3.987, "Bar Lisa"));

        // Configurar el comportamiento del DAO
        when(daoUbi.getAllByUserId(userId)).thenReturn(Either.right(mockedUbiList));

        // Llamar al método que estás probando
        Either<ErrorSec, List<UbiDTO>> result = serviceUbi.getAllByUserId(userId);

        // Verificar el resultado esperado
        assertThat(result.isRight()).isTrue();
        assertThat(result.get()).isEqualTo(mockedUbiList);
    }

    @Test
    void deleteUbi() {
        // Datos simulados
        int id = 1;
        int deletedCount = 1;

        // Configurar el comportamiento del DAO
        when(daoUbi.deleteUbi(id)).thenReturn(Either.right(deletedCount));

        // Llamar al método que estás probando
        Either<ErrorSec, Integer> result = serviceUbi.deleteUbi(id);

        // Verificar el resultado esperado
        assertThat(result.isRight()).isTrue();
        assertThat(result.get()).isEqualTo(deletedCount);
    }

    @Test
    void addUbi() {
        // Datos simulados
        NewUbiDTO newUbi = new NewUbiDTO( 40.123, -3.456, 1, "Bar Pepe");
        UbiDTO addedUbi = new UbiDTO(45, 40.123, -3.456, "Bar Lisa");

        // Configurar el comportamiento del DAO
        when(daoUbi.addUbi(newUbi)).thenReturn(Either.right(addedUbi));

        // Llamar al método que estás probando
        Either<ErrorSec, UbiDTO> result = serviceUbi.addUbi(newUbi);

        // Verificar el resultado esperado
        assertThat(result.isRight()).isTrue();
        assertThat(result.get()).isEqualTo(addedUbi);
    }

}