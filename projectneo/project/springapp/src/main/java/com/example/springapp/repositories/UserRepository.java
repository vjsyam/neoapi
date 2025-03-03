package com.example.springapp.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import com.example.springapp.entities.User;

import java.util.Optional;
import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    @NonNull
    Page<User> findAll(@NonNull Pageable pageable);

    List<User> findByName(String name);
}
