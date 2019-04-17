package com.example.demo.Repositories;

import com.example.demo.Models.AccessLevel;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AccessLevelRepository extends CrudRepository<AccessLevel, Integer> {
    AccessLevel findByAccLevelId(int accLevelId);

    List<AccessLevel> findAll();
}
