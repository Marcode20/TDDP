package com.tddp.service;

import com.tddp.exception.ResourceNotFoundException;
import com.tddp.model.OrdenCompra;
import com.tddp.repository.OrdenCompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrdenCompraServiceImpl implements  OrdenCompraService{

    @Autowired
    OrdenCompraRepository ordenCompraRepository;

    @Override
    public OrdenCompra createOrdenCompra(OrdenCompra ordenCompra) {
        return ordenCompraRepository.save(ordenCompra);
    }

    @Override
    public OrdenCompra updateOrdenCompra(OrdenCompra ordenCompra) {
        return ordenCompraRepository.save(ordenCompra);
    }

    @Override
    public List<OrdenCompra> GetOrdenCompra() {
        return ordenCompraRepository.findAll();
    }

    @Override
    public OrdenCompra GetOrdenCompraById(Integer orden_compra_id) {
        Optional<OrdenCompra> ordenCompra = ordenCompraRepository.findById(orden_compra_id);
        if (ordenCompra.isPresent()){
            return ordenCompra.get();
        }else {
            throw new ResourceNotFoundException("ERROR :" + orden_compra_id);
        }
    }

    @Override
    public void deleteOrdenCompra(Integer orden_compra_id) {
        Optional<OrdenCompra> ordenCompra = ordenCompraRepository.findById(orden_compra_id);
        if (ordenCompra.isPresent()){
            ordenCompraRepository.delete(ordenCompra.get());
        }else {
            throw new ResourceNotFoundException("ERROR :" + orden_compra_id);
        }
    }
}
