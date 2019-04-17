package com.example.demo.Service;

import com.example.demo.Models.*;
import com.example.demo.Presentation.HwPresentation;
import com.example.demo.Presentation.ServiceOrderPresentation;
import com.example.demo.Repositories.HwInventoryRepository;
import com.example.demo.Repositories.HwSvoLineRepository;
import com.example.demo.Repositories.ServiceOrderLineRepository;
import com.example.demo.Repositories.SvcRepository;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class ReportsService {

    @Autowired
    SvcRepository svcRepository;

    @Autowired
    ServiceOrderLineRepository serviceOrderLineRepository;

    @Autowired
    HwSvoLineRepository hwSvoLineRepository;

    @Autowired
    HwInventoryRepository hwInventoryRepository;

    public List<ServiceOrderPresentation> getServicesProvidedPresentation() {
        List<ServiceOrderPresentation> serviceOrderPresentations = new ArrayList<>();

        List<Svc> svcs = svcRepository.findActiveSvcs();

        for (Svc svc : svcs) {
            ServiceOrderPresentation serviceOrderPresentation = new ServiceOrderPresentation();
            serviceOrderPresentation.setSvcId(svc.getSvcId());
            serviceOrderPresentation.setSvcName(svc.getSvcName());
            serviceOrderPresentation.setSvcStatus(svc.getServiceStatus().getSvcStatus());
            serviceOrderPresentation.setSvcType(svc.getServiceType().getSvcType());

            Set<RequiredSkill> requiredSkills = svc.getRequiredSkills();
            List<Skill> skills = new ArrayList<>();

            for (RequiredSkill requiredSkill : requiredSkills) {
                Skill skill = new Skill();
                skill = requiredSkill.getSkill();
                skills.add(skill);
            }

            int count = 0;

           String skillString = new String();
            for (Skill skill : skills) {

                if (count > 0) {
                    skillString += ", ";
                }

                skillString += skill.getSkill();

                count++;

            }

            serviceOrderPresentation.setSkillString(skillString);

            serviceOrderPresentations.add(serviceOrderPresentation);
        }

        return serviceOrderPresentations;

    }


    public List<HwPresentation> getHardwareSales(){
        List<HwPresentation> hwPresentations = new ArrayList<>();

        List<HwSvoLine> hwSvoLines = hwSvoLineRepository.findAllHwSale();
        List<HwInventory> hwInventories = new ArrayList<>();

        Double totalSalePrice = 0.0;

        for(HwSvoLine hwSvoLine: hwSvoLines) {
            HwPresentation hwPresentation = new HwPresentation();
            HwInventory hwInventory = hwInventoryRepository.findByHwSerialNumber(hwSvoLine.getCustomerSiteHw().getCustSiteSerialNumber());

            hwPresentation.setHwSalePrice(hwInventory.getHwSalePrice());
            hwPresentation.setHwModel(hwSvoLine.getCustomerSiteHw().getHwModel().getHwModel());
            hwPresentation.setHwSeriesName(hwSvoLine.getCustomerSiteHw().getHwModel().getHwSeries().getHwSeriesName());
            hwPresentation.setHwManuName(hwSvoLine.getCustomerSiteHw().getHwModel().getHwSeries().getHwManufacturer().getHwManuName());
            hwPresentation.setHwType(hwSvoLine.getCustomerSiteHw().getHwModel().getHwSeries().getHwType().getHwType());
            hwPresentation.setHwSerialNumber(hwSvoLine.getCustomerSiteHw().getCustSiteSerialNumber());
            hwPresentation.setHwSaleDate(hwSvoLine.getServiceOrderLine().getServiceOrder().getDateFinished());

            hwPresentations.add(hwPresentation);

        }


        return hwPresentations;
    }
}

