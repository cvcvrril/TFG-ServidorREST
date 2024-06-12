package com.example.servidorrestinesmr.data.model.entities;

import com.example.servidorrestinesmr.utils.Constantes;
import com.example.servidorrestinesmr.utils.QueriesHQL;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = Constantes.UBICACIONES_TABLA)
@NamedQueries({
        @NamedQuery(name = Constantes.GET_ALL_UBIS, query = QueriesHQL.FROM_UBI_ENTITY),
        @NamedQuery(name = Constantes.GET_ALL_UBIS_BY_IDUSER, query = QueriesHQL.FROM_UBI_ENTITY_WHERE_ID_USER_ID_USER)
})
public class UbiEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = Constantes.LAT, nullable = false)
    private double lat;
    @Column(name = Constantes.LON, nullable = false)
    private double lon;
    @Column(name = Constantes.ID_USER_SEPARADO, nullable = false)
    private int idUser;
    @Column(name = Constantes.NOMBRE)
    private String nombre;
}
