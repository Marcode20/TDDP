package com.tddp.service;

import com.tddp.model.Administrador;

public interface UserService {

    void save(Administrador administrador);

    Administrador findByUsername(String username);

}
