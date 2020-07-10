package com.tddp.service;

import com.tddp.model.Role;

public interface RoleService {
    Role createRole(Role role);
    Role getRoleById(Integer id);
}
