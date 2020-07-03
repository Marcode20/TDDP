package com.tddp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "tbl_marca")
@Entity
public class Marca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer marca_id;
    @Column
    private String nombres;
    @Column
    private String descripcion;

    @OneToMany(mappedBy = "marca")
    private List<Producto> productos;



}
