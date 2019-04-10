package com.example.demo.Repositories;

import com.example.demo.Models.CustomerSiteStatus;
import org.springframework.data.repository.CrudRepository;

public interface CustomerSiteStatusRepository extends CrudRepository<CustomerSiteStatus,Integer> {

    CustomerSiteStatus findByCustSiteStatusId(int custSiteStatusId);
}
