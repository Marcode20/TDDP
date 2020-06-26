package com.tddp.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.tddp.model.Administrador;
import com.tddp.repository.RoleRepository;
import com.tddp.repository.UserRepository;

import java.util.HashSet;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public void save(Administrador administrador) {
        String passwordEncode = bCryptPasswordEncoder.encode(administrador.getPassword());
        administrador.setPassword(passwordEncode);
        administrador.setRoles(new HashSet<>(roleRepository.findAll())); //TODO pending to review
        userRepository.save(administrador);
    }

    @Override
    public Administrador findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
