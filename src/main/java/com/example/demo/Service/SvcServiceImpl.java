package com.example.demo.Service;

import com.example.demo.Models.Svc;
import com.example.demo.Repositories.SvcRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SvcServiceImpl implements SvcService {

    @Autowired
    SvcRepository svcRepository;

    public List<Svc> findAll(){
        return svcRepository.findAll();
    }

    public Svc findBySvcId(int Id)
    {
     return  svcRepository.findBySvcId(Id);
    }

    public List<Svc> findActiveSvcs()
    {
     return svcRepository.findActiveSvcs();
    }
}
