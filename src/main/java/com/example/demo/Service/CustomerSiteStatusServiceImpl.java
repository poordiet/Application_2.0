package com.example.demo.Service;

import com.example.demo.Models.CustomerSiteStatus;
import com.example.demo.Repositories.CustomerSiteRepository;
import com.example.demo.Repositories.CustomerSiteStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomerSiteStatusServiceImpl implements CustomerSiteStatusService {

    @Autowired
    CustomerSiteStatusRepository customerSiteStatusRepository;
    public CustomerSiteStatus findByCustSiteStatusId(int custSiteStatusId)
    {
     return customerSiteStatusRepository.findByCustSiteStatusId(custSiteStatusId);
    }
}
