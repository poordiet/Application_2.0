package com.example.demo.Converters;

import com.example.demo.Models.Contractor;
import com.example.demo.Models.Svc;
import org.springframework.core.convert.converter.Converter;

public interface ContractorConverter extends Converter<String, Contractor> {
    public Contractor convert(String id);
}
