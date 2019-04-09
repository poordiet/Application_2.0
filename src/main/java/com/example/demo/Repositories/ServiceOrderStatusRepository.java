package com.example.demo.Repositories;

import com.example.demo.Models.ServiceOrderStatus;
import org.springframework.data.repository.CrudRepository;

public interface ServiceOrderStatusRepository extends CrudRepository<ServiceOrderStatus,Integer>{

        ServiceOrderStatus findServiceOrderStatusBySvoStatusId(int svoStatusId);


}
