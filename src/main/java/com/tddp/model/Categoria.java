package com.tddp.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "tbl_categoria")
public class Categoria {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoria_id;

    @Column
    private String nombre;

    @Column
    private String estado;

    @OneToMany(mappedBy = "categoria")
    private List<Producto> productos;

}




