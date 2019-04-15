package com.example.demo.Converters;

import com.example.demo.Models.Contractor;
import com.example.demo.Models.CustomerSite;
import com.example.demo.Models.CustomerSiteHw;
import com.example.demo.Presentation.HwPresentation;
import com.example.demo.Repositories.ContractorRepository;
import com.example.demo.Repositories.CustomerSiteHwRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HwWorkedOnConverterImpl implements HwWorkedOnConverter{

    @Autowired
    CustomerSiteHwRepository customerSiteHwRepository;

    @Override
    public HwPresentation convert(String id)
    {
        System.out.println("trying to convert id = " + id + " into a HwPresentation");

        int parseId = Integer.parseInt(id);

        customerSiteHwRepository.findById(parseId);

        HwPresentation hwPresentation = new HwPresentation();
        hwPresentation.setCustSiteHwId(Integer.parseInt(id));

        return hwPresentation;

    }
}
