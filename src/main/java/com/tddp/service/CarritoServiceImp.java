package com.tddp.service;

import com.tddp.exception.ResourceNotFoundException;
import com.tddp.model.Carrito;
import com.tddp.repository.CarritoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class CarritoServiceImp implements CarritoService{

    @Autowired
    CarritoRepository carritoRepository;

    @Override
    public Carrito createCarrito(Carrito carrito) {
        return carritoRepository.save(carrito);
    }

    @Override
    public Carrito updateCarrito(Carrito carrito) {
        return carritoRepository.save(carrito);
    }

    @Override
    public List<Carrito> GetCarritoALL() {
        return carritoRepository.findAll();
    }

    @Override
    public Carrito GetCarritoById(Integer carrito_id) {
        Optional<Carrito> carrito = carritoRepository.findById(carrito_id);
        if (carrito.isPresent()){
            return carrito.get();
        }else {
            throw  new ResourceNotFoundException("Error:" + carrito_id);
        }
    }

    @Override
    public void deleteCarrito(Integer carrito_id) {
        Optional<Carrito> carrito = carritoRepository.findById(carrito_id);
        if (carrito.isPresent()){
             carritoRepository.delete(carrito.get());
        }else {
            throw  new ResourceNotFoundException("Error:" + carrito_id);
        }
    }
}
