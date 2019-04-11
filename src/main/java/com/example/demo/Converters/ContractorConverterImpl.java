package com.example.demo.Converters;

import com.example.demo.Models.Contractor;
import com.example.demo.Models.Svc;
import com.example.demo.Repositories.ContractorRepository;
import com.example.demo.Repositories.SvcRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ContractorConverterImpl implements ContractorConverter{

    @Autowired
    ContractorRepository contractorRepository;

    @Override
    public Contractor convert(String id)
    {
        System.out.println("trying to convert id = " + id + " into a contractor");

        int parseId = Integer.parseInt(id);

        return contractorRepository.findByContractorId(parseId);

    }
}
