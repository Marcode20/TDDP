package com.tddp.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@EqualsAndHashCode
@Embeddable
@Data
public class CarritoProductoKey {
    @Column(name = "carrito_id")
    private Integer  carritoId;

    @Column(name = "producto_id")
    private Integer productoId;
}
