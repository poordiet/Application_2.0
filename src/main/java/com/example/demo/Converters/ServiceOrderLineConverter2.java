package com.example.demo.Converters;

import com.example.demo.Models.ServiceOrderLine;
import com.example.demo.Models.Svc;
import com.example.demo.Repositories.SvcRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ServiceOrderLineConverter2 implements ServiceOrderLineConverter {

    @Autowired
    SvcRepository svcRepository;

    @Override
    public Svc convert(String id)
    {
        System.out.println("trying to convert id = " + id + " into a serviceOrderLine");

        int parseId = Integer.parseInt(id);

        return svcRepository.findBySvcId(parseId);

    }

}
