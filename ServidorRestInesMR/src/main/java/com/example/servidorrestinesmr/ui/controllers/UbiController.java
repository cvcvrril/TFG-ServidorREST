package com.example.servidorrestinesmr.ui.controllers;

import com.example.servidorrestinesmr.domain.model.NewUbiDTO;
import com.example.servidorrestinesmr.domain.model.UbiDTO;
import com.example.servidorrestinesmr.domain.services.ServiceUbi;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ubi")
public class UbiController {

    private final ServiceUbi service;

    @GetMapping("/all")
    public List<UbiDTO> getAllUbis() {
        return service.getAll().getOrElseThrow(() -> new RuntimeException());
    }

    @GetMapping("/ubicacion")
    public UbiDTO getbyIdUbi(@RequestParam("id") int id){
        return service.getByIdUbi(id).getOrElseThrow(() -> new RuntimeException());
    }

    @GetMapping("/ubiuser")
    public List<UbiDTO> getAllUbisByUserId(@RequestParam("id_user")int idUser){
        return service.getAllByUserId(idUser).get();
    }

    @DeleteMapping("/delete")
    public int deleteUbi(@RequestParam("id") int id){
        return service.deleteUbi(id).get();
    }

    @PostMapping("/add")
    public UbiDTO addUbi(@RequestBody NewUbiDTO nuevaUbi){
        return service.addUbi(nuevaUbi).get();
    }
}
