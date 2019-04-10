package com.example.demo.Service;

import com.example.demo.Models.Country;
import com.example.demo.Repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    CountryRepository countryRepository;

    public List<Country> findAll()
    {
        return countryRepository.findAll();
    }

    public Country findByCountryId( int countryId)
    {
        return countryRepository.findByCountryId(countryId);
    }


}
