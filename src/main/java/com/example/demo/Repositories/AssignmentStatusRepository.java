package com.example.demo.Repositories;

import com.example.demo.Models.AssignmentStatus;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AssignmentStatusRepository extends CrudRepository<AssignmentStatus,Integer> {

    AssignmentStatus findById(int Id);
}
