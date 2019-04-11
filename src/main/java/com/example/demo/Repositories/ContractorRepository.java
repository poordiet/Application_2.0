package com.example.demo.Repositories;

import com.example.demo.Models.Contractor;
import com.example.demo.Models.ServiceOrder;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ContractorRepository extends CrudRepository<Contractor,Integer> {

    List<Contractor> findAll();

    @Query(value = "select * from CONTRACTOR where contractor_status_id=1;", nativeQuery = true)
    List<Contractor> findActiveContractors();
}
