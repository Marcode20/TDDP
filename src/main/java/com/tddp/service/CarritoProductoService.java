package com.tddp.service;

import com.tddp.model.Carrito;
import com.tddp.model.CarritoProducto;
import com.tddp.model.CarritoProductoKey;
import com.tddp.model.Producto;

import java.util.List;

public interface CarritoProductoService {

    CarritoProducto createCarritoProducto(CarritoProducto carritoProducto);
    CarritoProducto updateCarritoProducto(CarritoProducto carritoProducto);
    List<CarritoProducto> GetCarritoProductoAll();
    CarritoProducto GetCarritoProductoById(CarritoProductoKey carritoProductoKey);
    void DeleteProducto(CarritoProductoKey carritoProductoKey);
}
