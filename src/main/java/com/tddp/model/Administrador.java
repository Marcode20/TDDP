package com.tddp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "tbl_administrador")
@Entity
public class Administrador {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long administrador_id;
    private String username;
    private String password;
    @Transient
    private String passwordConfirm;

    @ManyToMany
    private Set<Role> roles;

}
