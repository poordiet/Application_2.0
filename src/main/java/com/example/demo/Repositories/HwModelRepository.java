package com.example.demo.Repositories;

import com.example.demo.Models.HwModel;
import com.example.demo.Models.HwSeries;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HwModelRepository extends CrudRepository<HwModel, Integer> {

    // Retrieve all Non Deleted HwSeries
    @Query(value = "select * from HW_MODEL where hw_model_status_id != 4 ORDER BY hw_model" , nativeQuery = true)
    List<HwModel> findAllHwModels();

    HwModel findById( int id);

}
