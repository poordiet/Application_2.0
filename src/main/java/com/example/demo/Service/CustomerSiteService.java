package com.example.demo.Service;

import com.example.demo.Models.CustomerSite;
import com.example.demo.Models.ServiceOrder;

import java.util.List;

public interface CustomerSiteService {

    public void saveCustomerSite(CustomerSite customerSite);

    CustomerSite findCustomerSiteByCustSiteId(int custSiteId);

    List<CustomerSite> findActiveCustomerSiteOrderByNameThenNumber();
}
