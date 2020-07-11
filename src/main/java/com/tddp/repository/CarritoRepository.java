package com.tddp.repository;

import com.tddp.model.Carrito;
import com.tddp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarritoRepository extends JpaRepository<Carrito, Integer> {

    @Query("SELECT u FROM Carrito u  WHERE u.isActive = 1")
    public Carrito findActiveCarrito();


}
