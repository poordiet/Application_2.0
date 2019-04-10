package com.example.demo.Repositories;

import com.example.demo.Models.Country;
import com.example.demo.Models.Svc;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SvcRepository extends CrudRepository<Svc,Integer> {
    List<Svc> findAll();

    Svc findBySvcId(int Id);
}
