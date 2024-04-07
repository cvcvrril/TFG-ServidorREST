package com.example.servidorrestinesmr.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UbiDTO {
    private int id;
    private double lat;
    private double lon;
}
