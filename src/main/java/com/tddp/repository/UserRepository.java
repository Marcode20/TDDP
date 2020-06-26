package com.tddp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tddp.model.Administrador;

@Repository
public interface UserRepository extends JpaRepository<Administrador, Long> {

    Administrador findByUsername(String username);

}
