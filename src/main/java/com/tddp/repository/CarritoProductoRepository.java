package com.tddp.repository;

import com.tddp.model.CarritoProductoKey;
import com.tddp.model.CarritoProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarritoProductoRepository extends JpaRepository<CarritoProducto, CarritoProductoKey> {
}
