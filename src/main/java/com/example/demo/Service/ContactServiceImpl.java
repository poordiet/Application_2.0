package com.example.demo.Service;

import com.example.demo.Models.Contact;
import com.example.demo.Repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    ContactRepository contactRepository;

    public void saveContact(Contact contact)
    {
            contactRepository.save(contact);
    }
}
