package com.example.demo.Repositories;

import com.example.demo.Models.Country;
import com.example.demo.Models.Svc;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SvcRepository extends CrudRepository<Svc,Integer> {
    List<Svc> findAll();

    Svc findBySvcId(int Id);

    @Query(value = "select * from SVC where svc_status_id = 1 \n", nativeQuery = true)
    List<Svc> findActiveSvcs();
}
