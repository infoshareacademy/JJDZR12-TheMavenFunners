package com.isa.tasktrackerwebapp.repository;

import com.isa.tasktrackerwebapp.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLogin(String login);
    boolean existsByEmail(String email);
    boolean existsByLogin(String login);
}
