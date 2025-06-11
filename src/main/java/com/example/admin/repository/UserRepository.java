package com.example.admin.repository;

import com.example.admin.model.entity.User;
import jakarta.validation.constraints.Email;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {


    List<User> findByDateTimeBetween(LocalDateTime startDateTime, LocalDateTime endDateTime);

    Optional<User> findByEmail(String email);

    boolean existsUserByLogin(String login);

    boolean existsUserByEmail(@Email String email);

    Optional<User> findUserByLogin(String login);

    Optional<User> findUserByPassword(String password);

    User findUserById(UUID id);
}
