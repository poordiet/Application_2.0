package com.example.demo.Repositories;

import com.example.demo.Models.Country;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CountryRepository extends CrudRepository<Country,Integer> {
    List<Country> findAll();

    Country findByCountryId(int countryId);
}
