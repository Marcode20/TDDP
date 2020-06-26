package com.tddp.service;

public interface SecurityService {

    String findLoggedInUsername();

    void autoLogin(String username, String password);

}
