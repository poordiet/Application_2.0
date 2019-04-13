package com.example.demo.Service;

import com.example.demo.Models.CustomerSite;
import com.example.demo.Repositories.CustomerSiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerSiteServiceImpl implements CustomerSiteService {
    @Autowired
    CustomerSiteRepository customerSiteRepository;

    public void saveCustomerSite(CustomerSite customerSite){
        customerSiteRepository.save(customerSite);
    }

    @Override
    public CustomerSite findCustomerSiteByCustSiteId(int custSiteId){
        return customerSiteRepository.findCustomerSiteByCustSiteId(custSiteId);
    }

    @Override
    public List<CustomerSite> findActiveCustomerSiteOrderByNameThenNumber()
    {
        return customerSiteRepository.findActiveCustomerSiteOrderByNameThenNumber();
    }
}
