package com.example.demo.Repositories;

import com.example.demo.Models.ContractorStatus;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ContractorStatusRepository extends CrudRepository<ContractorStatus, Integer> {
    ContractorStatus findByContractorStatusId(int contractorStatusId);

    List<ContractorStatus> findAll();
}
