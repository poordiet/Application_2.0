package com.example.demo.Service;

import com.example.demo.Models.StateProvince;

import java.util.List;

public interface StateProvinceService {

    List<StateProvince> findAll();

    StateProvince findByStateId(int stateId);
}
