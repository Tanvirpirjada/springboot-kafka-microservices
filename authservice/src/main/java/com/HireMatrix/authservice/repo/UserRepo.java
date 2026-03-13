package com.HireMatrix.authservice.repo;

import com.HireMatrix.authservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Integer> {
    Optional<User> findByEmail(String encrypt);
}
