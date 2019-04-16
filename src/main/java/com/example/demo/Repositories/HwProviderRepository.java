package com.example.demo.Repositories;

import com.example.demo.Models.HwProvider;
import com.example.demo.Models.HwSeries;
import com.example.demo.Presentation.HwPresentation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HwProviderRepository extends CrudRepository<HwProvider, Integer> {

    // Retrieve all Active hw providers
    @Query(value = "select * from HW_PROVIDER where hw_provider_status_id = 1 ORDER BY hw_provider_name" , nativeQuery = true)
    List<HwProvider> findAllHwProviders();

    HwProvider findById(int id);
}
