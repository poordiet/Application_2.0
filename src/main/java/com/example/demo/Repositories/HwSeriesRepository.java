package com.example.demo.Repositories;

import com.example.demo.Models.HwManufacturer;
import com.example.demo.Models.HwSeries;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HwSeriesRepository extends CrudRepository<HwSeries, Integer> {

    // Retrieve all Non Deleted HwSeries
    @Query(value = "select * from HW_SERIES where hw_series_status_id != 4 ORDER BY hw_series_name" , nativeQuery = true)
    List<HwSeries> findAllHwSeries();

}
