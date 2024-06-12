package com.example.servidorrestinesmr.ui.controllers;

import com.example.servidorrestinesmr.domain.model.NewUbiDTO;
import com.example.servidorrestinesmr.domain.model.UbiDTO;
import com.example.servidorrestinesmr.domain.services.ServiceUbi;
import com.example.servidorrestinesmr.utils.Constantes;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(Constantes.UBI_PATH)
public class UbiController {


    private final ServiceUbi service;

    @GetMapping(Constantes.ALL_PATH)
    @Secured({Constantes.ROLE_ADMIN})
    public List<UbiDTO> getAllUbis() {
        return service.getAll().getOrElseThrow(() -> new RuntimeException());
    }

    @GetMapping(Constantes.UBICACION_PATH)
    @Secured({Constantes.ROLE_USER, Constantes.ROLE_ADMIN})
    public UbiDTO getbyIdUbi(@RequestParam(Constantes.ID) int id){
        return service.getByIdUbi(id).getOrElseThrow(() -> new RuntimeException());
    }

    @GetMapping(Constantes.UBIUSER_PATH)
    @Secured({Constantes.ROLE_USER, Constantes.ROLE_ADMIN})
    public List<UbiDTO> getAllUbisByUserId(@RequestParam(Constantes.ID_USER_SEPARADO)int idUser){
        return service.getAllByUserId(idUser).get();
    }

    @DeleteMapping(Constantes.DELETE_PATH)
    @Secured({Constantes.ROLE_USER, Constantes.ROLE_ADMIN})
    public int deleteUbi(@RequestParam(Constantes.ID) int id){
        return service.deleteUbi(id).get();
    }

    @PostMapping(Constantes.ADD_PATH)
    @Secured({Constantes.ROLE_USER, Constantes.ROLE_ADMIN})
    public UbiDTO addUbi(@RequestBody NewUbiDTO nuevaUbi){
        return service.addUbi(nuevaUbi).get();
    }
}
