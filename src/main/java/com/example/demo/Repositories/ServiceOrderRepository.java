package com.example.demo.Repositories;

import com.example.demo.ServiceOrder;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ServiceOrderRepository extends CrudRepository<ServiceOrder,Integer> {

    List<ServiceOrder> findAllByOrderByDateScheduledDesc();
}
