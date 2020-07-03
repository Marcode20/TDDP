package com.tddp.service;

import com.tddp.model.OrdenCompra;

import java.util.List;

public interface OrdenCompraService {

    OrdenCompra createOrdenCompra (OrdenCompra ordenCompra);
    OrdenCompra updateOrdenCompra (OrdenCompra ordenCompra);
    List<OrdenCompra> GetOrdenCompra ();
    OrdenCompra GetOrdenCompraById(Integer orden_compra_id);
    void deleteOrdenCompra(Integer orden_compra_id);
}
