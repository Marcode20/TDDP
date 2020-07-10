package com.tddp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.tddp.model.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);

    @Query("SELECT u FROM User u join u.roles r WHERE u.username = :username and r.name='USER'")
    public List<User> findUser(@Param("username") String username);

    @Query("SELECT u FROM User u join u.roles r WHERE u.username = :username and r.name='ADMIN'")
    public List<User> findAdmin(@Param("username") String username);
}
