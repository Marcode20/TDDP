package com.tddp.service;


import com.tddp.exception.ResourceNotFoundException;
import com.tddp.model.Producto;
import com.tddp.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductoServiceImpl implements ProductoService{

    @Autowired
    ProductoRepository productoRepository;

    @Override
    public Producto createProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public Producto updateProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public List<Producto> getProductoall() {
        return productoRepository.findAll();
    }

    @Override
    public Producto getProductoById(Integer producto_id) {
        Optional<Producto> byId = productoRepository.findById(producto_id);
        if(byId.isPresent()){return byId.get();
        }else {
            throw new ResourceNotFoundException("Error: " + producto_id);
        }
    }

    @Override
    public void deleteProducto(Integer producto_id) {
        Optional<Producto> byId = productoRepository.findById(producto_id);
        if(byId.isPresent()){
            productoRepository.delete(byId.get());
        }else {
            throw new ResourceNotFoundException("Error" + producto_id);
        }
    }
}
