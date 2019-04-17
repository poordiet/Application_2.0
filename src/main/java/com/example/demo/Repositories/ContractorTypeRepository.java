package com.example.demo.Repositories;

import com.example.demo.Models.ContractorType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ContractorTypeRepository extends CrudRepository<ContractorType,Integer> {
    ContractorType findByContractorTypeId(int contractorId);

    List<ContractorType> findAll();

}
