package com.tddp.service;

import com.tddp.model.Role;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.tddp.model.User;
import com.tddp.repository.RoleRepository;
import com.tddp.repository.UserRepository;

import java.util.HashSet;
import java.util.List;

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
    public void save(User user, List<Role> roles) {
        String passwordEncode = bCryptPasswordEncoder.encode(user.getPassword());
        System.out.println(user.getPassword());
        System.out.println(passwordEncode);
        user.setPassword(passwordEncode);
        user.setRoles(roles);//TODO pending to review
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
