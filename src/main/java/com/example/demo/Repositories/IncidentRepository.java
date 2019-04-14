package com.example.demo.Repositories;

import com.example.demo.Models.Incident;
import org.springframework.data.repository.CrudRepository;

public interface IncidentRepository extends CrudRepository<Incident, Integer> {

}
