package com.example.demo.Repositories;

import com.example.demo.Models.ServiceOrder;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ServiceOrderRepository extends CrudRepository<ServiceOrder,Integer> {

    List<ServiceOrder> findAllByOrderByDateScheduledDesc();

    @Query(value = "select * from SERVICE_ORDER where date_started < '2019-04-18 18:00:00' AND date_finished IS NULL", nativeQuery = true)
    List<ServiceOrder> findServiceOrderInProgress();

    @Query(value = "select * from SERVICE_ORDER where svo_status_id != 5 ORDER BY svo_id DESC", nativeQuery = true)
    List<ServiceOrder> findAllByOrOrderBySvoIdDesc();

    @Query(value = "select * from SERVICE_ORDER where svo_status_id != 5 AND DATEDIFF(day,date_finished,getdate()) between 0 AND 30 ORDER BY date_scheduled ASC", nativeQuery = true)
    List<ServiceOrder> findMonthlyServiceOrders();

    ServiceOrder findServiceOrderBySvoId(int svoId);

}