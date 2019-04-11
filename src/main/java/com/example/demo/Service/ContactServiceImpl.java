package com.example.demo.Service;

import com.example.demo.Models.Contact;
import com.example.demo.Models.CustomerSite;
import com.example.demo.Models.ServiceOrder;
import com.example.demo.Repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    ContactRepository contactRepository;

    public void saveContact(Contact contact)
    {
            contactRepository.save(contact);
    }

    @Override
    public List<Contact> findByCustSite(@Param("customerSite") CustomerSite customerSite){
        return contactRepository.findByCustSite(customerSite);
    };

    @Override
    public Contact findByContactId(int contactId)
    {
       return contactRepository.findByContactId(contactId);
    }
}
