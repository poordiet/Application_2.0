package com.example.demo.Repositories;

import com.example.demo.Models.Contractor;
import com.example.demo.Models.ServiceOrder;
import com.example.demo.Models.StateProvince;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContractorRepository extends CrudRepository<Contractor,Integer> {

    List<Contractor> findAll();

    @Query(value = "select * from CONTRACTOR where contractor_status_id=1;", nativeQuery = true)
    List<Contractor> findActiveContractors();

    Contractor findByContractorId(int contractorId);

    @Query(value = "select * from CONTRACTOR where CONTRACTOR.contractor_status_id <> 8 order by CONTRACTOR.state_id, CONTRACTOR.contractor_status_id, CONTRACTOR.contractor_lname, CONTRACTOR.contractor_fname;", nativeQuery = true)
    List<Contractor> findAllNotDeleted();

    @Query(value="select * from CONTRACTOR where contractor_status_id = 1 AND DATEDIFF(day,contractor_hire_date,getdate()) between 0 AND 30 AND contractor_leave_date IS NULL ORDER BY contractor_city,contractor_lname", nativeQuery = true)
    List<Contractor> findAllContractorsGained();

    @Query(value = "select * from contractor where contractor_status_id =  1 AND state_id = :#{#stateProvince.stateId}",nativeQuery = true)
    List<Contractor> findContractorByState(@Param("stateProvince") StateProvince stateProvince);


}
