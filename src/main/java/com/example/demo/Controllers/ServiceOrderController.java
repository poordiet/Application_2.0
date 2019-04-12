package com.example.demo.Controllers;

import com.example.demo.Models.*;
import com.example.demo.Presentation.ServiceOrderLinePresentation;
import com.example.demo.Presentation.ServiceOrderPresentation;
import com.example.demo.Repositories.*;
import com.example.demo.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Set;

@SessionAttributes({"serviceOrderPresentation","serviceOrderPresentationLine"})
@RequestMapping("/service_orders")
@Controller
public class ServiceOrderController {

    // This makes the model attribute of serviceOrderPresentation persist throughout this whole controller
    @ModelAttribute("serviceOrderPresentation")
    public ServiceOrderPresentation getServiceOrderPresentation(){
        return new ServiceOrderPresentation();
    }

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
    @Autowired
    ServiceOrderLineRepository serviceOrderLineRepository;

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
/*
    @GetMapping(value="/showFormForAddCurrentCustomer")
    public String showFormForAddCurrentCustomer(Model theModel,@RequestParam("custSiteId") int custSiteId)
    {



    }*/
/*
    @PostMapping("/addCurrentCustomer")
    public String addCurrentServiceOrder(@ModelAttribute("serviceOrderPresentation") ServiceOrderPresentation serviceOrderPresentation, final RedirectAttributes redirectAttributes)
    {

        // Save
        serviceOrderService.saveServiceOrderFromCurrentCustomerForm(serviceOrderPresentation);

        // Passes the model attribute to the showFormForAssigningContractors method to be used there
        redirectAttributes.addFlashAttribute("serviceOrderPresentation", serviceOrderPresentation);

        return "redirect:/service_orders/showFormForAssigningContractors";
    }
*/
    @PostMapping("/addNewCustomer")
    public String addNewServiceOrder(@ModelAttribute("serviceOrderPresentation") ServiceOrderPresentation serviceOrderPresentation, final RedirectAttributes redirectAttributes)
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


    // select current or new customer
    @GetMapping("/serviceOrderSelection")
    public String serviceOrderSelection(Model theModel)
    {

        //NEED TO CHANGE THIS
        List<ServiceOrderPresentation> customers = serviceOrderService.getServiceOrderPresentation(serviceOrderService.findAll());

        theModel.addAttribute("customers",customers);

        return ("addServiceOrderSelection");
    }

/*
    @GetMapping("/showFormForAssigningContractors")
    public String showFormForAssigningContractors(Model theModel, @ModelAttribute("serviceOrderPresentation") ServiceOrderPresentation serviceOrderPresentation)
    {

        theModel.addAttribute("serviceOrderPresentation",serviceOrderPresentation);

        List<Contractor> contractors = contractorRepository.findActiveContractors();
        theModel.addAttribute("contractors", contractors);

        List<Svc> svcs = svcService.findAll();
        theModel.addAttribute("svcs", svcs);

        return ("addServiceOrderAssignContractorsNew");
    }

*/

    @PostMapping("/addAnotherService")
    public String addAnotherService(Model theModel, @ModelAttribute("serviceOrderPresentation") ServiceOrderPresentation serviceOrderPresentation)
    {

        theModel.addAttribute("serviceOrderPresentation", serviceOrderPresentation);

        for(Contractor contractor : serviceOrderPresentation.getContractorList())
        {
            System.out.println(contractor.getContractorFname());
            System.out.println(contractor.getContractorId());
        }


        serviceOrderService.addContractorAssignments(serviceOrderPresentation);


        return "redirect:/service_orders/search";
    }

/*
    @PostMapping("/assignContractors")
    public String assignContractors(Model theModel, @ModelAttribute("serviceOrderPresentation") ServiceOrderPresentation serviceOrderPresentation)
    {


            theModel.addAttribute("serviceOrderPresentation", serviceOrderPresentation);

        // yay!
        System.out.println(serviceOrderPresentation.getCustSiteCity());
        System.out.println(serviceOrderPresentation.getCustSiteEmail());
        System.out.println(serviceOrderPresentation.getCustSitePhone());
        System.out.println(serviceOrderPresentation.getCustSiteZip());
        System.out.println(serviceOrderPresentation.getCountryId());
        System.out.println(serviceOrderPresentation.getStateId());
        System.out.println(serviceOrderPresentation.getContactId());
        System.out.println(serviceOrderPresentation.getContactFname());
        System.out.println(serviceOrderPresentation.getContactLname());
        System.out.println(serviceOrderPresentation.getContactType());
        System.out.println(serviceOrderPresentation.getContractorList());


        for(Contractor contractor : serviceOrderPresentation.getContractorList())
        {
            System.out.println(contractor.getContractorFname());
            System.out.println(contractor.getContractorId());
        }


        for(Svc svc : serviceOrderPresentation.getSvcs())
        {
            System.out.println(svc.getSvcId());
            System.out.println(svc.getSvcName());
        }

        // Save
        serviceOrderService.saveServiceOrderFromCurrentCustomerForm(serviceOrderPresentation);

        for(ServiceOrderLine serviceOrderLine : serviceOrderPresentation.getServiceOrderLines())
        {
            System.out.println(serviceOrderLine.getSvoLineId());
            System.out.println(serviceOrderLine.getSvc().getSvcName());
        }


        //serviceOrderService.addContractorAssignments(serviceOrderPresentation);



        return "redirect:/service_orders/search";
    }
*/







// OLD CONTROLLER METHODS - Saving in case we change our minds ------------------------------------------------------

    @GetMapping("/showFormForAddAssignment")
    public String showFormForAddAssignment(Model theModel, @ModelAttribute("serviceOrderPresentation") ServiceOrderPresentation serviceOrderPresentation)
    {

        theModel.addAttribute("serviceOrderPresentation",serviceOrderPresentation);

        for(ServiceOrderLine serviceOrderLine:serviceOrderPresentation.getServiceOrderLines())
        {
            System.out.println(serviceOrderLine.getSvoLineId());
        }

        List<ServiceOrderLinePresentation> serviceOrderLinePresentations = serviceOrderService.getServiceOrderLinePresentation(serviceOrderPresentation.getServiceOrderLines());
        theModel.addAttribute("serviceOrderLinePresentations",serviceOrderLinePresentations);

        List<Contractor> contractors = contractorRepository.findActiveContractors();
        theModel.addAttribute("contractors", contractors);

        List<Svc> svcs = serviceOrderPresentation.getSvcs();
        theModel.addAttribute("svcs", svcs);

        Set<ServiceOrderLine> serviceOrderLines = serviceOrderPresentation.getServiceOrderLines();
        theModel.addAttribute("serviceOrderLines",serviceOrderLines);

        return ("addAssignment");
    }


    @PostMapping("/assignContractors")
    public String assignContractors(Model theModel, @ModelAttribute("serviceOrderPresentation") ServiceOrderPresentation serviceOrderPresentation,final RedirectAttributes redirectAttributes)
    {


        theModel.addAttribute("serviceOrderPresentation", serviceOrderPresentation);

        // yay!

        System.out.println(serviceOrderPresentation.getCustSiteCity());
        System.out.println(serviceOrderPresentation.getCustSiteEmail());
        System.out.println(serviceOrderPresentation.getCustSitePhone());
        System.out.println(serviceOrderPresentation.getCustSiteZip());
        System.out.println(serviceOrderPresentation.getCountryId());
        System.out.println(serviceOrderPresentation.getStateId());
        System.out.println(serviceOrderPresentation.getContactId());
        System.out.println(serviceOrderPresentation.getContactFname());
        System.out.println(serviceOrderPresentation.getContactLname());
        System.out.println(serviceOrderPresentation.getContactType());
        System.out.println(serviceOrderPresentation.getContractorList());
/*

        for(Contractor contractor : serviceOrderPresentation.getContractorList())
        {
            System.out.println(contractor.getContractorFname());
            System.out.println(contractor.getContractorId());
        }


        for(Svc svc : serviceOrderPresentation.getSvcs())
        {
            System.out.println(svc.getSvcId());
            System.out.println(svc.getSvcName());
        }*/

        // Save
        //serviceOrderService.saveServiceOrderFromCurrentCustomerForm(serviceOrderPresentation);


        serviceOrderService.addAssignments(serviceOrderPresentation);


        // Passes the model attribute to the showFormForAssigningContractors method to be used there
        redirectAttributes.addFlashAttribute("serviceOrderPresentation", serviceOrderPresentation);

        return "redirect:/service_orders/showFormForAssigningContractors";
    }

    @GetMapping("/showFormForAssigningContractors")
    public String showFormForAssigningContractors(Model theModel, @ModelAttribute("serviceOrderPresentation") ServiceOrderPresentation serviceOrderPresentation)
    {
        // These all return valid values
        /*System.out.println(serviceOrderPresentation.getCustSiteCity());
        System.out.println(serviceOrderPresentation.getCustSiteEmail());
        for(Svc svc : serviceOrderPresentation.getSvcs())
        {
            System.out.println(svc.getSvcId());
            System.out.println(svc.getSvcName());
        }*/


        for(ServiceOrderLine serviceOrderLine:serviceOrderPresentation.getServiceOrderLines())
        {
            System.out.println(serviceOrderLine.getSvoLineId());
        }

        List<ServiceOrderLinePresentation> serviceOrderLinePresentations = serviceOrderService.getServiceOrderLinePresentation(serviceOrderPresentation.getServiceOrderLines());
        theModel.addAttribute("serviceOrderLinePresentations",serviceOrderLinePresentations);

        for(ServiceOrderLinePresentation serviceOrderLinePresentation:serviceOrderLinePresentations)
        {
            System.out.println(serviceOrderLinePresentation.getSvoLineId());
            System.out.println(serviceOrderLinePresentation.getSvcId());
            System.out.println(serviceOrderLinePresentation.getSvcName());
        }

        theModel.addAttribute("serviceOrderPresentation",serviceOrderPresentation);

        List<Contractor> contractors = contractorRepository.findActiveContractors();
        theModel.addAttribute("contractors", contractors);

        List<Svc> svcs = serviceOrderPresentation.getSvcs();
        theModel.addAttribute("svcs", svcs);

        Set<ServiceOrderLine> serviceOrderLines = serviceOrderPresentation.getServiceOrderLines();
        theModel.addAttribute("serviceOrderLines",serviceOrderLines);

        return ("addServiceOrderAssignContractors");
    }



    @PostMapping("/addCurrentCustomer")
    public String addCurrentServiceOrder(@ModelAttribute("serviceOrderPresentation") ServiceOrderPresentation serviceOrderPresentation, final RedirectAttributes redirectAttributes)
    {

        // Save
        serviceOrderService.saveServiceOrderFromCurrentCustomerFormOld(serviceOrderPresentation);

        // Passes the model attribute to the showFormForAssigningContractors method to be used there
        redirectAttributes.addFlashAttribute("serviceOrderPresentation", serviceOrderPresentation);

        return "redirect:/service_orders/showFormForAssigningContractors";
    }


    @GetMapping(value="/showFormForAddCurrentCustomer")
    public String showFormForAddCurrentCustomer(Model theModel,@RequestParam("custSiteId") int custSiteId)
    {

        CustomerSite customerSite = customerSiteService.findCustomerSiteByCustSiteId(custSiteId);

        ServiceOrderPresentation serviceOrderPresentation = serviceOrderService.addCustomerSite(customerSite);

        List<Contact> contacts = contactService.findByCustSite(customerSite);
        List<Svc> svcs = svcService.findAll();

        List<ContactType> contactTypes = contactTypeRepository.findAll();

        theModel.addAttribute("contactTypes", contactTypes);
        theModel.addAttribute("svcs", svcs);
        theModel.addAttribute("customer", serviceOrderPresentation);
        theModel.addAttribute("contacts",contacts);

        return ("addServiceOrderCurrentCustomer");
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

