package com.elifserttas.todolist.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elifserttas.todolist.entity.Users;

public interface UserRepository extends JpaRepository<Users,Long> {
    Optional<Users> findByUserName(String username);

    List<Users> findAll();
}
