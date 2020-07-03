package com.tddp.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@EqualsAndHashCode
@Embeddable
@Data
public class CarritoProductoKey implements Serializable {
    @Column(name = "carrito_id")
    private Integer  carritoId;

    @Column(name = "producto_id")
    private Integer productoId;
}
