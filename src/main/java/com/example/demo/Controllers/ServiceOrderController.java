package com.example.demo.Controllers;

import com.example.demo.Models.*;
import com.example.demo.Presentation.ServiceOrderPresentation;
import com.example.demo.Repositories.ContactTypeRepository;
import com.example.demo.Repositories.ContractorRepository;
import com.example.demo.Repositories.ServiceOrderRepository;
import com.example.demo.Repositories.SvcRepository;
import com.example.demo.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@RequestMapping("/service_orders")
@Controller
public class ServiceOrderController {

    @Autowired
    ServiceOrderRepository serviceOrderRepository;
    @Autowired
    SvcRepository svcRepository;
    @Autowired
    ServiceOrderServiceImpl serviceOrderService;
    @Autowired
    StateProvinceService stateProvinceService;
    @Autowired
    CountryService countryService;
    @Autowired
    SvcService svcService;
    @Autowired
    ContractorRepository contractorRepository;
    @Autowired
    CustomerSiteService customerSiteService;
    @Autowired
    ContactService contactService;
    @Autowired
    ContactTypeRepository contactTypeRepository;

    @GetMapping("/search")
    public String searchServiceOrder(Model theModel)
    {

        List<ServiceOrderPresentation> serviceOrderPresentations = serviceOrderService.getServiceOrderPresentation(serviceOrderService.findAll());

        theModel.addAttribute("serviceOrderPresentations",serviceOrderPresentations);

        return("service_order_search");
    }

    @GetMapping(value = "/delete")
    public String deleteServiceOrder(@RequestParam("svoId") int svoId)
    {

        ServiceOrderStatus serviceOrderStatus = serviceOrderService.findServiceOrderStatusBySvoStatusId(3);

       ServiceOrder serviceOrder = serviceOrderService.findServiceOrderBySvoId(svoId);
       serviceOrder.setServiceOrderStatus(serviceOrderStatus);
       serviceOrderService.saveServiceOrder(serviceOrder);

        return "redirect:/service_orders/search";
    }

    @GetMapping(value="/showFormForAddCurrentCustomer")
    public String showFormForAddCurrentCustomer(Model theModel,@RequestParam("custSiteId") int custSiteId)
    {
        CustomerSite customerSite = customerSiteService.findCustomerSiteByCustSiteId(custSiteId);

        ServiceOrderPresentation serviceOrderPresentation = serviceOrderService.addCustomerSite(customerSite);
        System.out.println(serviceOrderPresentation.getCustomerSite().getCustSiteName());
        System.out.println(serviceOrderPresentation.getCustomerSite().getCountry().getCountryName());
        System.out.println(serviceOrderPresentation.getCustomerSite().getCustSiteEmail());
        System.out.println(serviceOrderPresentation.getCustSiteEmail());

        List<Contact> contacts = contactService.findByCustSiteId(customerSite);
        List<Svc> svcs = svcService.findAll();

        List<ContactType> contactTypes = contactTypeRepository.findAll();

        theModel.addAttribute("contactTypes", contactTypes);
        theModel.addAttribute("svcs", svcs);
        theModel.addAttribute("customer", serviceOrderPresentation);
        theModel.addAttribute("contacts",contacts);

        return ("addServiceOrderCurrentCustomer");
    }

    @PostMapping("/addNewCustomer")
    public String addServiceOrder(@ModelAttribute("serviceOrderPresentation") ServiceOrderPresentation serviceOrderPresentation, final RedirectAttributes redirectAttributes)
    {

        // Save
        serviceOrderService.saveServiceOrderFromForm(serviceOrderPresentation);

        // Passes the model attribute to the showFormForAssigningContractors method to be used there
        redirectAttributes.addFlashAttribute("serviceOrderPresentation", serviceOrderPresentation);

        return "redirect:/service_orders/showFormForAssigningContractors";
    }

    @GetMapping("/showFormForAddNewCustomer")
    public String showFormForAddNewCustomer(Model theModel)
    {

        ServiceOrderPresentation serviceOrderPresentation = new ServiceOrderPresentation();
        theModel.addAttribute("serviceOrderPresentation",serviceOrderPresentation);

        List<Svc> svcs = svcService.findAll();
        theModel.addAttribute("svcs", svcs);

        List<Country> countries = countryService.findAll();
        theModel.addAttribute("countries", countries);

        List<StateProvince> stateProvinces = stateProvinceService.findAll();
        theModel.addAttribute("states", stateProvinces);

        return ("addServiceOrderNewCustomer");
    }



    @GetMapping("/serviceOrderSelection")
    public String serviceOrderSelection(Model theModel)
    {

        List<ServiceOrderPresentation> customers = serviceOrderService.getServiceOrderPresentation(serviceOrderService.findAll());

        theModel.addAttribute("customers",customers);

        List<Svc> svcs = svcService.findAll();
        theModel.addAttribute("svcs", svcs);

        List<Country> countries = countryService.findAll();
        theModel.addAttribute("countries", countries);

        List<StateProvince> stateProvinces = stateProvinceService.findAll();
        theModel.addAttribute("states", stateProvinces);

        return ("addServiceOrderSelection");
    }


    @GetMapping("/showFormForAssigningContractors")
    public String showFormForAssigningContractors(Model theModel, @ModelAttribute("serviceOrderPresentation") ServiceOrderPresentation serviceOrderPresentation)
    {
        theModel.addAttribute("serviceOrderPresentation",serviceOrderPresentation);

        List<Contractor> contractors = contractorRepository.findActiveContractors();
        theModel.addAttribute("contractors", contractors);

        List<Svc> svcs = serviceOrderPresentation.getSvcs();
        theModel.addAttribute("svcs", svcs);


        return ("addServiceOrderAssignContractors");
    }

/*
    @GetMapping("/add")
    public String handleAddServiceOrder(Model theModel,@ModelAttribute("serviceOrderPresentation") ServiceOrderPresentation serviceOrderPresentation,
                                        @ModelAttribute("stateProvince") StateProvince stateProvince, @ModelAttribute("country") Country country,
                                        @ModelAttribute("svc") Svc svc)
    {

        List<Svc> svcs = svcService.findAll();
        theModel.addAttribute("svcs", svcs);

        List<Country> countries = countryService.findAll();
        theModel.addAttribute("countries", countries);

        List<StateProvince> stateProvinces = stateProvinceService.findAll();
        theModel.addAttribute("states", stateProvinces);

        serviceOrderService.saveServiceOrderFromForm(serviceOrderPresentation);

        return "redirect:/addServiceOrder";
    }
*/
}

