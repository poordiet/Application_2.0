package com.example.demo.Repositories;

import com.example.demo.Models.Incident;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IncidentRepository extends CrudRepository<Incident, Integer> {

    @Query(value="select * from INCIDENT where incident_status_id != 5 AND DATEDIFF(day,incident_date,getdate()) between 0 AND 30 ORDER BY incident_date ASC",nativeQuery = true)
    List<Incident> findMonthlyIncident();

}
