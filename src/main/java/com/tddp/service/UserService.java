package com.tddp.service;

import com.tddp.model.Role;
import com.tddp.model.User;

import java.util.List;

public interface UserService {

    void save(User user , List<Role>roles);

    User findByUsername(String username);

}
