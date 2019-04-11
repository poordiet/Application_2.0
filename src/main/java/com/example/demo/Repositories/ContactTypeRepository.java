package com.example.demo.Repositories;

import com.example.demo.Models.ContactStatus;
import com.example.demo.Models.ContactType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ContactTypeRepository extends CrudRepository<ContactType, Integer> {
    ContactType findByContactTypeId(int contactTypeId);

    List<ContactType> findAll();
}
