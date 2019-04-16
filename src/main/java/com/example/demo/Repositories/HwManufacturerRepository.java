package com.example.demo.Repositories;

import com.example.demo.Models.CustomerSite;
import com.example.demo.Models.HwManufacturer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HwManufacturerRepository extends CrudRepository<HwManufacturer,Integer> {

    // Retrieve all Non Deleted HwManufacturers
    @Query(value = "select * from HW_MANUFACTURER where hw_manu_status_id != 3 ORDER BY hw_manu_name" , nativeQuery = true)
    List<HwManufacturer> findAllHwManufacturers();
}
