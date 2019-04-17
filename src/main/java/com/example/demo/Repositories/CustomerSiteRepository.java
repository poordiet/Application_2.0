package com.example.demo.Repositories;

import com.example.demo.Models.Contact;
import com.example.demo.Models.CustomerSite;
import com.example.demo.Models.ServiceOrder;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface
CustomerSiteRepository extends CrudRepository<CustomerSite, Integer> {
    CustomerSite findCustomerSiteByCustSiteId(int custSiteId);

    // Retrieve all ACTIVE customer sites and order by Customer name then Customer Number
    @Query(value = "select * from CUSTOMER_SITE where cust_site_status_id = 1 \n" +
            "Order by CUSTOMER_SITE.state_id, cust_site_city, cust_site_name, cust_site_number;", nativeQuery = true)
    List<CustomerSite> findActiveCustomerSiteOrderByNameThenNumber();

    @Query(value = "select * from CUSTOMER_SITE where CUSTOMER_SITE.cust_site_status_id <> 4 order by CUSTOMER_SITE.state_id, CUSTOMER_SITE.cust_site_status_id, CUSTOMER_SITE.cust_site_name, CUSTOMER_SITE.cust_site_number;", nativeQuery = true)
    List<CustomerSite> findCustomerSiteByABunch();

    List<CustomerSite> findAllByOrderByCustSiteId();

    @Query(value="select * from CUSTOMER_SITE where cust_site_status_id =1 AND DATEDIFF(day,cust_site_start,getdate()) between 0 AND 30 ORDER BY cust_site_start ASC", nativeQuery=true)
    List<CustomerSite> findAllCustomersGainedMonthly();

}
