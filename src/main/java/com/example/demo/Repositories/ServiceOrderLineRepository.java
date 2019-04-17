package com.example.demo.Repositories;

import com.example.demo.Models.Contact;
import com.example.demo.Models.CustomerSite;
import com.example.demo.Models.ServiceOrder;
import com.example.demo.Models.ServiceOrderLine;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ServiceOrderLineRepository extends CrudRepository<ServiceOrderLine, Integer> {

    ServiceOrderLine findById(int id);

    @Query(value = "select * from SERVICE_ORDER_LINE where svo_id = :svoId", nativeQuery = true)
    List<ServiceOrderLine> findByServiceOrderId(@Param("svoId") int svoId);


    @Query(value = "select * from SERVICE_ORDER_LINE INNER JOIN SVC ON SVC.svc_id = SERVICE_ORDER_LINE.svc_id INNER JOIN SERVICE_ORDER ON SERVICE_ORDER.svo_id = SERVICE_ORDER_LINE.svo_id WHERE SERVICE_ORDER.svo_status_id != 5 AND SVC.svc_id = 14 AND DATEDIFF(day,SERVICE_ORDER.date_finished,getdate()) BETWEEN 0 AND 30 ORDER BY date_scheduled ASC", nativeQuery = true)
    List<ServiceOrderLine> findAllServiceOrderLinesHw();
}
