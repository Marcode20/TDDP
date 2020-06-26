package com.tddp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "tbl_producto")
@Entity
public class Producto {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Integer producto_id;
    @Column
    private String nombres;
    @Column
    private Double precio;
    @Column
    private String descripcion;
    @Column
    private Integer stock;

}
