package com.example.demo.Service;

import com.example.demo.Models.*;
import com.example.demo.Presentation.ServiceOrderPresentation;
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
}

