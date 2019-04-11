package com.example.demo.Repositories;

import com.example.demo.Models.Contact;
import com.example.demo.Models.CustomerSite;
import com.example.demo.Models.ServiceOrder;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContactRepository extends CrudRepository<Contact, Integer> {

    @Query(value = "select * from CONTACT where cust_site_id = :#{#customerSite.custSiteId}", nativeQuery = true)
    List<Contact> findByCustSite(@Param("customerSite") CustomerSite customerSite);

    Contact findByContactId(int contactId);
}
