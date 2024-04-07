package com.example.servidorrestinesmr.data.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ubicaciones")
@NamedQueries({
        @NamedQuery(name = "GET_ALL_UBIS", query = "from UbiEntity")
})
public class UbiEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "lat", nullable = false)
    private double lat;
    @Column(name = "lon", nullable = false)
    private double lon;
    @Column(name = "id_user", nullable = false)
    private int idUser;


}
