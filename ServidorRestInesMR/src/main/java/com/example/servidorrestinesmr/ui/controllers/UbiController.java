package com.example.servidorrestinesmr.ui.controllers;

import com.example.servidorrestinesmr.data.model.UserResponse;
import com.example.servidorrestinesmr.domain.model.NewUbiDTO;
import com.example.servidorrestinesmr.domain.model.UbiDTO;
import com.example.servidorrestinesmr.domain.services.ServiceUbi;
import com.example.servidorrestinesmr.domain.services.ServiceUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ubi")
public class UbiController {

    private final ServiceUbi service;
    private final ServiceUser serviceUser;

    @GetMapping("/all")
    @Secured({"ROLE_ADMIN"})
    public List<UbiDTO> getAllUbis() {
        return service.getAll().getOrElseThrow(() -> new RuntimeException());
    }

    @GetMapping("/ubicacion")
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public UbiDTO getbyIdUbi(@RequestParam("id") int id){
        return service.getByIdUbi(id).getOrElseThrow(() -> new RuntimeException());
    }

    @GetMapping("/ubiuser")
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public List<UbiDTO> getAllUbisByUserId(@RequestParam("id_user")int idUser){
        return service.getAllByUserId(idUser).get();
    }

    @DeleteMapping("/delete")
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public int deleteUbi(@RequestParam("id") int id){
        return service.deleteUbi(id).get();
    }

    @PostMapping("/add")
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public UbiDTO addUbi(@RequestBody NewUbiDTO nuevaUbi){
        return service.addUbi(nuevaUbi).get();
    }
}
