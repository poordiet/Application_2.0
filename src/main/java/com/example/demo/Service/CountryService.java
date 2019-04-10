package com.example.demo.Service;

import com.example.demo.Models.Country;

import java.util.List;

public interface CountryService {
    List<Country> findAll();

    Country findByCountryId(int countryId);
}
