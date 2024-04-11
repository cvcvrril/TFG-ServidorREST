package com.example.servidorrestinesmr.ui.controllers;

import com.example.servidorrestinesmr.domain.model.UbiDTO;
import com.example.servidorrestinesmr.domain.services.ServiceUbi;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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


}
