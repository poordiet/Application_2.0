package com.example.demo.Service;

import com.example.demo.Models.Svc;

import java.util.List;

public interface SvcService {
    List<Svc> findAll();

    Svc findBySvcId(int Id);

    List<Svc> findActiveSvcs();
}
