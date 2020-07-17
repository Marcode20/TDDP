package com.tddp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "tbl_carrito_producto")
public class CarritoProducto {

    @EmbeddedId
    private CarritoProductoKey carritoProductoKey;

    @ManyToOne
    @MapsId("carrito_id")
    @JoinColumn(name = "carrito_id")
    private Carrito carrito;

    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId("producto_id")
    @JoinColumn(name = "producto_id")
    private Producto producto;


    private Integer cantidad;

    public CarritoProducto(Producto producto, Integer cantidad ) {
        this.producto = producto;
        this.cantidad = cantidad;
    }
}
