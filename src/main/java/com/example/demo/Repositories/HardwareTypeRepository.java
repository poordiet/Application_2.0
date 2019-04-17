package com.example.demo.Repositories;

import com.example.demo.Models.HwType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HardwareTypeRepository extends CrudRepository<HwType, Integer> {
    List<HwType> findAll();
}
