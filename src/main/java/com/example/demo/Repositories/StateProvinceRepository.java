package com.example.demo.Repositories;

import com.example.demo.Models.StateProvince;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StateProvinceRepository extends CrudRepository<StateProvince,Integer> {

    List<StateProvince> findAll();

   StateProvince findByStateId(int stateId);
}
