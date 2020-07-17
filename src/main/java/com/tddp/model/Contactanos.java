package com.tddp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "tddp-contacts-table")
@Entity
public class Contactanos{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer contac_id;
    @Column
    private String nombres;
    @Column
    private  String correo;
    @Column
    private String mensaje;


}
