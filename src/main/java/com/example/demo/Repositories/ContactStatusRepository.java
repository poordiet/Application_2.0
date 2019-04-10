package com.example.demo.Repositories;

import com.example.demo.Models.ContactStatus;
import org.springframework.data.repository.CrudRepository;

public interface ContactStatusRepository extends CrudRepository<ContactStatus, Integer> {
    ContactStatus findByContactStatusId(int contactStatusId);
}
