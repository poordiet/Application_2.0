package com.example.demo.Service;

import com.example.demo.Models.StateProvince;
import com.example.demo.Repositories.StateProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StateProvinceServiceImpl implements StateProvinceService {

    @Autowired
    StateProvinceRepository stateProvinceRepository;

    @Override
    public List<StateProvince> findAll(){
        return stateProvinceRepository.findAll();
    }

    public StateProvince findByStateId(int stateId){
        return stateProvinceRepository.findByStateId(stateId);
    }
}
