package com.tddp.service;

import com.tddp.model.Carrito;

import java.util.List;

public interface CarritoService {

    Carrito createCarrito (Carrito carrito);
    Carrito updateCarrito (Carrito carrito);
    List<Carrito> GetCarritoALL();
    Carrito GetCarritoById(Integer carrito_id);
    void deleteCarrito(Integer carrito_id);
}
