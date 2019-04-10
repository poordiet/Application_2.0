package com.example.demo.Repositories;

import com.example.demo.Models.CustomerSiteStatus;
import com.example.demo.Models.CustomerSiteType;
import org.springframework.data.repository.CrudRepository;

public interface CustomerSiteTypeRepository extends CrudRepository<CustomerSiteType,Integer> {

    CustomerSiteType findByCustSiteTypeId(int custSiteTypeId);
}
