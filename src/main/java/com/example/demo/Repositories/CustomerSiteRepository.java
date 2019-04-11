package com.example.demo.Repositories;

import com.example.demo.Models.CustomerSite;
import com.example.demo.Models.ServiceOrder;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerSiteRepository extends CrudRepository<CustomerSite, Integer> {
    CustomerSite findCustomerSiteByCustSiteId(int custSiteId);

}
