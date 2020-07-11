package com.tddp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.jni.Local;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "tbl_carrito")
public class Carrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer carrito_id;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime date;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "orden_compra_id")
    private OrdenCompra ordenCompra;

    @OneToMany(mappedBy = "carrito")
    private List<CarritoProducto> carritoProducto;

    @Column
    private Integer isActive;

    @Override
    public String toString() {
        return "carritoToString";
    }
}
