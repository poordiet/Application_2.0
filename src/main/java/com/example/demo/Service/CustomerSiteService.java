package com.example.demo.Service;

import com.example.demo.Models.CustomerSite;
import com.example.demo.Models.ServiceOrder;

public interface CustomerSiteService {

    public void saveCustomerSite(CustomerSite customerSite);

    CustomerSite findCustomerSiteByCustSiteId(int custSiteId);
}
