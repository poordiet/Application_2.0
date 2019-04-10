package com.example.demo.Service;

import com.example.demo.Models.CustomerSite;
import com.example.demo.Repositories.CustomerSiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerSiteServiceImpl implements CustomerSiteService {
    @Autowired
    CustomerSiteRepository customerSiteRepository;

    public void saveCustomerSite(CustomerSite customerSite){
        customerSiteRepository.save(customerSite);
    }
}
