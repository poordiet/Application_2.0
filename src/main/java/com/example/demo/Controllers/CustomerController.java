package com.example.demo.Controllers;

import com.example.demo.Models.*;
import com.example.demo.Presentation.CustomerPresentation;
import com.example.demo.Presentation.ServiceOrderPresentation;
import com.example.demo.Repositories.*;
import com.example.demo.Service.CountryService;
import com.example.demo.Service.CustomerService;
import com.example.demo.Service.StateProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@RequestMapping(value = "/customers", method={RequestMethod.GET, RequestMethod.POST})
@Controller
public class CustomerController {

    @Autowired
    CustomerSiteRepository customerSiteRepository;

    @Autowired
    CustomerSiteStatusRepository customerSiteStatusRepository;

    @Autowired
    CustomerSiteTypeRepository customerSiteTypeRepository;

    @Autowired
    CountryRepository countryRepository;

    @Autowired
    CountryService countryService;

    @Autowired
    StateProvinceRepository stateProvinceRepository;

    @Autowired
    StateProvinceService stateProvinceService;

    @Autowired
    CustomerService customerService;

    @GetMapping("/search")
    public String searchCustomer(Model theModel)
    {

        List<CustomerPresentation> customerPresentation = customerService.getCustomerPresentation(customerSiteRepository.findCustomerSiteByABunch());

        theModel.addAttribute("customerPresentations",customerPresentation);

        return("search_customer_main");
    }

    @GetMapping("/showAddNewCustomer")
    public String addCustomerForm(Model theModel)
    {

        CustomerPresentation customerPresentation = new CustomerPresentation();

        theModel.addAttribute("customerPresentation",customerPresentation);

        List<Country> countries = countryService.findAll();
        theModel.addAttribute("countries", countries);

        List<StateProvince> stateProvinces = stateProvinceService.findAll();
        theModel.addAttribute("states", stateProvinces);

        List<CustomerSiteType> customerSiteTypes = customerSiteTypeRepository.findAll();
        theModel.addAttribute("customerSiteTypes", customerSiteTypes);


        return("add_customer");
    }


    @PostMapping("/addNewCustomer")
    public String addNewCustomer(@ModelAttribute("customerPresentation") CustomerPresentation customerPresentation, final RedirectAttributes redirectAttributes)
    {
        System.out.println(customerPresentation.getCustSiteEmail());

        // Save
        customerService.saveCustomerSiteFromForm(customerPresentation);

        // Passes the model attribute to the showFormForAssigningContractors method to be used there
        redirectAttributes.addFlashAttribute("customerPresentation", customerPresentation);

        return "redirect:/customers/search";
    }

    @GetMapping("/customerSiteProfile")
    public String showCustomerProfile(@RequestParam("custSiteId") int custSiteId, Model theModel)
    {
        ServiceOrderPresentation serviceOrderPresentation = customerService.getServiceOrderPresentationById(custSiteId);

        theModel.addAttribute("serviceOrderPresentation",serviceOrderPresentation);

        List<Contact> contacts = serviceOrderPresentation.getContacts();
        theModel.addAttribute("contacts", contacts);

        return("customer_profile");
    }

    @GetMapping(value = "/delete")
    public String deleteCustomer(@RequestParam("custSiteId") int custSiteId)
    {

        CustomerSiteStatus customerSiteStatus = customerSiteStatusRepository.findByCustSiteStatusId(4);

        CustomerSite customerSite = customerSiteRepository.findCustomerSiteByCustSiteId(custSiteId);
        customerSite.setCustomerSiteStatus(customerSiteStatus);
        customerSiteRepository.save(customerSite);

        return "redirect:/customers/search";
    }


}
