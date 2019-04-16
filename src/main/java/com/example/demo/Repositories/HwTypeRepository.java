package com.example.demo.Repositories;

import com.example.demo.Models.HwSeries;
import com.example.demo.Models.HwType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HwTypeRepository extends CrudRepository<HwType, Integer> {

    List<HwType> findAllByOrderByHwTypeId();

}
