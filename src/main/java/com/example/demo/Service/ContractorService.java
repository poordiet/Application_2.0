package com.example.demo.Service;

import com.example.demo.Models.*;
import com.example.demo.Presentation.ServiceOrderPresentation;
import com.example.demo.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

@Service
public class ContractorService {
    @Autowired
    ContractorRepository contractorRepository;


    @Autowired
    CountryRepository countryRepository;

    @Autowired
    CountryService countryService;

    @Autowired
    StateProvinceRepository stateProvinceRepository;

    @Autowired
    StateProvinceService stateProvinceService;

    @Autowired
    ContractorStatusRepository contractorStatusRepository;

    @Autowired
    ContractorTypeRepository contractorTypeRepository;

    @Autowired
    SkillRepository skillRepository;

    @Autowired
    ContractorSkillRepository contractorSkillRepository;

    @Autowired
    AccessLevelRepository accessLevelRepository;


    public List<Contractor> findAll() {
        return contractorRepository.findAll();
    }

    public List<ServiceOrderPresentation> getContractors(List<Contractor> contractors) {

        List<ServiceOrderPresentation> serviceOrderPresentations = new ArrayList<>();


        for (Contractor contractor1 : contractors) {


            ServiceOrderPresentation serviceOrderPresentation = new ServiceOrderPresentation();


            serviceOrderPresentation.setContractorHireDate(contractor1.getContractorHireDate());
            serviceOrderPresentation.setContractorId(contractor1.getContractorId());
            serviceOrderPresentation.setContractorFname(contractor1.getContractorFname());
            serviceOrderPresentation.setContractorLname(contractor1.getContractorLname());
            serviceOrderPresentation.setContractorEmail(contractor1.getContractorEmail());
            serviceOrderPresentation.setContractorPhone(contractor1.getContractorPhone());
            serviceOrderPresentation.setContractorCity(contractor1.getContractorCity());
            serviceOrderPresentation.setStateName(contractor1.getStateProvince().getStateName());
            serviceOrderPresentation.setContractorAvailability(contractor1.getContractorAvailability());
            serviceOrderPresentation.setContractorStatus(contractor1.getContractorStatus().getContractorStatus());

            serviceOrderPresentations.add(serviceOrderPresentation);


        }
        return serviceOrderPresentations;

    }

    public void saveContractorForm(ServiceOrderPresentation serviceOrderPresentation){

        Contractor contractor1 = new Contractor();
        contractor1.setContractorFname(serviceOrderPresentation.getContractorFname());
        contractor1.setContractorLname(serviceOrderPresentation.getContractorLname());
        contractor1.setContractorEmail(serviceOrderPresentation.getContractorEmail());
        contractor1.setContractorPhone(serviceOrderPresentation.getContractorPhone());
        contractor1.setContractorAddress(serviceOrderPresentation.getContractorAddress());
        contractor1.setContractorCity(serviceOrderPresentation.getContractorCity());
        contractor1.setContractorZip(serviceOrderPresentation.getContractorZip());

        //state
        StateProvince stateProvince = stateProvinceRepository.findByStateId(serviceOrderPresentation.getStateId());
        contractor1.setStateProvince(stateProvince);

        //country
        Country country = countryRepository.findByCountryId(serviceOrderPresentation.getCountryId());
        contractor1.setCountry(country);

        //type
        ContractorType contractorType = contractorTypeRepository.findByContractorTypeId(serviceOrderPresentation.getContractorTypeId());
        contractor1.setContractorType(contractorType);

        //status
        ContractorStatus contractorStatus = contractorStatusRepository.findByContractorStatusId(serviceOrderPresentation.getContractorStatusId());
        contractor1.setContractorStatus(contractorStatus);

       contractor1.setContractorAvailability(serviceOrderPresentation.getContractorAvailability());

        //access level
        AccessLevel accessLevel = accessLevelRepository.findByAccLevelId(serviceOrderPresentation.getAccLevelId());
        contractor1.setAccessLevel(accessLevel);

        //username and password
        contractor1.setContractorUsername(serviceOrderPresentation.getContractorUsername());
        contractor1.setContractorPassword(serviceOrderPresentation.getContractorPassword());

        //hire date
        java.sql.Date currentSqlDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        contractor1.setContractorHireDate(currentSqlDate);

       contractorRepository.save(contractor1);

       //skill
        //System.out.println(serviceOrderPresentation.getSkills());
        for (Skill skill: serviceOrderPresentation.getSkills()) {

            ContractorSkill contractorSkill = new ContractorSkill();
            contractorSkill.setContractor(contractor1);
           // System.out.println(contractorSkill.getContractor());
            contractorSkill.setSkill(skill);
            //System.out.println(contractorSkill.getSkill());

            contractorSkillRepository.save(contractorSkill);


        }












    }

    public ServiceOrderPresentation getServiceOrderPresentationById(int contractorId) {

        Contractor contractor = contractorRepository.findByContractorId(contractorId);



        ServiceOrderPresentation serviceOrderPresentation = new ServiceOrderPresentation();
        serviceOrderPresentation.setContractorId(contractor.getContractorId());
        serviceOrderPresentation.setContractorFname(contractor.getContractorFname());
        serviceOrderPresentation.setContractorLname(contractor.getContractorLname());
        serviceOrderPresentation.setContractorEmail(contractor.getContractorEmail());
        serviceOrderPresentation.setContractorPhone(contractor.getContractorPhone());
        serviceOrderPresentation.setContractorAddress(contractor.getContractorAddress());
        serviceOrderPresentation.setContractorCity(contractor.getContractorCity());
        serviceOrderPresentation.setContractorZip(contractor.getContractorZip());
        serviceOrderPresentation.setCountryName(contractor.getCountry().getCountryName());
        serviceOrderPresentation.setStateName(contractor.getStateProvince().getStateName());
        serviceOrderPresentation.setContractorStatus(contractor.getContractorStatus().getContractorStatus());
        serviceOrderPresentation.setContractorType(contractor.getContractorType().getContractorType());
        serviceOrderPresentation.setContractorAvailability(contractor.getContractorAvailability());
        serviceOrderPresentation.setAccLevelName(contractor.getAccessLevel().getAccLevelName());
        serviceOrderPresentation.setContractorUsername(contractor.getContractorUsername());
        serviceOrderPresentation.setContractorPassword(contractor.getContractorPassword());

//        //skills hopefully
        Set<ContractorSkill> contractorSkills = contractor.getContractorSkills();
        List<Skill> skills = new ArrayList<>();




        for(ContractorSkill contractorSkill:contractorSkills)
        {

            Skill skill = skillRepository.findById(contractorSkill.getSkill().getSkillId());

            skills.add(skill);
        }

        serviceOrderPresentation.setSkillList(skills);


        return serviceOrderPresentation;
    }

    public List<Skill> getContractorSkills(int contractorId)
    {
        Contractor contractor = contractorRepository.findByContractorId(contractorId);

//        //skills hopefully
        Set<ContractorSkill> contractorSkills = contractor.getContractorSkills();
        List<Skill> skills = new ArrayList<>();




        for(ContractorSkill contractorSkill:contractorSkills)
        {

            Skill skill = skillRepository.findById(contractorSkill.getSkill().getSkillId());

            skills.add(skill);
        }

       return skills;
    }


    public void updateContractor(ServiceOrderPresentation serviceOrderPresentation)
    {

        Contractor contractor =  contractorRepository.findByContractorId(serviceOrderPresentation.getContractorId());

        contractor.setContractorFname(serviceOrderPresentation.getContractorFname());
        contractor.setContractorLname(serviceOrderPresentation.getContractorLname());
        contractor.setContractorEmail(serviceOrderPresentation.getContractorEmail());
        contractor.setContractorPhone(serviceOrderPresentation.getContractorPhone());
        contractor.setContractorAddress(serviceOrderPresentation.getContractorAddress());
        contractor.setContractorCity(serviceOrderPresentation.getContractorCity());
        contractor.setContractorZip(serviceOrderPresentation.getContractorZip());
        contractor.setContractorAvailability(serviceOrderPresentation.getContractorAvailability());
        contractor.setContractorUsername(serviceOrderPresentation.getContractorUsername());
        contractor.setContractorPassword(serviceOrderPresentation.getContractorPassword());

        contractorRepository.save(contractor);

    }

}
