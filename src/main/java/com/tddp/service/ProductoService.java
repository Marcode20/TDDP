package com.tddp.service;

import com.tddp.model.Producto;

import java.util.List;

public interface ProductoService {

    Producto createProducto(Producto producto);
    Producto updateProducto(Producto producto);
    List<Producto> getProductoall();
    Producto getProductoById(Integer producto_id);
    void deleteProducto(Integer producto_id);

}
