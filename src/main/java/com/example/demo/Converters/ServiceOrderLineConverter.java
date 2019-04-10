package com.example.demo.Converters;

import com.example.demo.Models.ServiceOrderLine;
import com.example.demo.Models.Svc;
import org.springframework.core.convert.converter.Converter;


public interface ServiceOrderLineConverter extends Converter<String, Svc> {
    public Svc convert(String id);
}
