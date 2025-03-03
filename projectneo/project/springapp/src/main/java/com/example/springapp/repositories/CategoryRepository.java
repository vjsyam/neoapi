package com.example.springapp.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import com.example.springapp.entities.Category;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT c FROM Category c WHERE LOWER(c.name) = LOWER(:name)")
    Category findByNameIgnoreCase(String name);

    @NonNull
    Page<Category> findAll(@NonNull Pageable pageable);

    List<Category> findAllByOrderByNameAsc();

    Category findByName(String name);
}
