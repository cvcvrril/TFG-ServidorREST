package com.example.servidorrestinesmr.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewUbiDTO {
    private double lat;
    private double lon;
    private int idUser;
    private String nombre;
}
