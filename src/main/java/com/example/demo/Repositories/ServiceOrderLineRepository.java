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

}
