package com.example.demo.Repositories;

import com.example.demo.Models.CustomerSite;
import com.example.demo.Models.CustomerSiteHw;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerSiteHwRepository  extends CrudRepository<CustomerSiteHw,Integer> {

    CustomerSiteHw findById(int id);

    @Query(value = "select * from CUSTOMER_SITE_HW where cust_site_id = :#{#customerSite.custSiteId}", nativeQuery = true)
    List<CustomerSiteHw> findCustomerSiteHwsByCustomerSite(@Param("customerSite") CustomerSite customerSite);

    List<CustomerSiteHw> findAll();
}
