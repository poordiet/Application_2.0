package com.example.demo;

import com.example.demo.Repositories.ContractorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/contractors")
@Controller
public class ContractorController {

    @Autowired
    ContractorRepository contractorRepository;

    @GetMapping("/displayContractor")
    public String displayContractor(Model theModel){
        contractorRepository.findAll();

        Iterable<Contractor> theContractors = contractorRepository.findAll();
        theModel.addAttribute("contractors",theContractors);


        return ("/front-end-master_2.0/front-end/service_order_search");
    }
}
