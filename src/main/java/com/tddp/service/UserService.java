package com.tddp.service;

import com.tddp.model.User;

public interface UserService {

    void save(User user);

    User findByUsername(String username);

}
