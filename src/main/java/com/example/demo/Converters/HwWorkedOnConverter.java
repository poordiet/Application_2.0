package com.example.demo.Converters;

import com.example.demo.Models.Contractor;
import com.example.demo.Models.CustomerSiteHw;
import com.example.demo.Presentation.HwPresentation;
import org.springframework.core.convert.converter.Converter;

public interface HwWorkedOnConverter extends Converter<String, HwPresentation> {
    public HwPresentation convert(String id);
}
