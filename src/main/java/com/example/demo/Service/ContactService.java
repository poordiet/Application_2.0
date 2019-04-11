package com.example.demo.Service;

import com.example.demo.Models.Contact;
import com.example.demo.Models.CustomerSite;
import com.example.demo.Models.ServiceOrder;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContactService {

    public void saveContact(Contact contact);

   List<Contact> findByCustSite(@Param("customerSite") CustomerSite customerSite);

    Contact findByContactId(int contactId);


}
