package com.tddp.service;

import com.tddp.exception.ResourceNotFoundException;
import com.tddp.model.Producto;
import com.tddp.model.Role;
import com.tddp.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService{

    final
    RoleRepository roleRepository;


    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    @Override
    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role getRoleById(Integer id) {
        Optional<Role> role = roleRepository.findById(id);
        if(role.isPresent()){return role.get();
        }else {
            throw new ResourceNotFoundException("Error: " + id);
        }
    }
}
