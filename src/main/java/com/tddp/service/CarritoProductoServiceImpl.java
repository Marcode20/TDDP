package com.tddp.service;

import com.tddp.exception.ResourceNotFoundException;
import com.tddp.model.*;
import com.tddp.repository.CarritoProductoRepository;
import com.tddp.repository.CarritoRepository;
import com.tddp.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CarritoProductoServiceImpl implements  CarritoProductoService{


    @Autowired
    CarritoProductoRepository carritoProductoRepository;

    @Autowired
    CarritoRepository carritoRepository;

    @Autowired
    ProductoRepository productoRepository;

    @Override
    public CarritoProducto createCarritoProducto(CarritoProducto carritoProducto) {
        return carritoProductoRepository.save(carritoProducto);
    }

    @Override
    public CarritoProducto updateCarritoProducto(CarritoProducto carritoProducto) {
        return carritoProductoRepository.save(carritoProducto);
    }

    @Override
    public List<CarritoProducto> GetCarritoProductoAll() {
        return carritoProductoRepository.findAll();
    }

    @Override
    public CarritoProducto GetCarritoProductoById(CarritoProductoKey carritoProductoKey) {
        Optional<CarritoProducto> carritoProductokeys = carritoProductoRepository.findById(carritoProductoKey);
        if (carritoProductokeys.isPresent()){
            return carritoProductokeys.get();
        }else {
            throw new ResourceNotFoundException("Error:" + carritoProductoKey);
        }
    }

    @Override
    public void DeleteProducto(CarritoProductoKey carritoProductoKey) {
        Optional<CarritoProducto> carritoProductokeys = carritoProductoRepository.findById(carritoProductoKey);
        if (carritoProductokeys.isPresent()){
            carritoProductoRepository.delete(carritoProductokeys.get());
        }else {
            throw new ResourceNotFoundException("Error:" + carritoProductoKey);
        }
    }

    @Override
    public Integer carritoProductoExists(Integer id, List<CarritoProducto> carritoProductoList) {
        for (Integer i = 0; i < carritoProductoList.size(); i++){
            if(carritoProductoList.get(i).getProducto().getProducto_id() == id){
                return i;
            }
        }
        return -1;
    }
}
