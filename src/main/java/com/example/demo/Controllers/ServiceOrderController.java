package com.example.demo.Controllers;

import com.example.demo.Models.*;
import com.example.demo.Presentation.HwPresentation;
import com.example.demo.Presentation.ServiceOrderLinePresentation;
import com.example.demo.Presentation.ServiceOrderPresentation;
import com.example.demo.Repositories.*;
import com.example.demo.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

@SessionAttributes({"serviceOrderPresentation","serviceOrderLinePresentation"})
@RequestMapping("/service_orders")
@Controller
public class ServiceOrderController {

    // This makes the model attribute of serviceOrderPresentation persist throughout this whole controller
    @ModelAttribute("serviceOrderPresentation")
    public ServiceOrderPresentation getServiceOrderPresentation(){
        return new ServiceOrderPresentation();
    }

    // This makes the model attribute of serviceOrderPresentation persist throughout this whole controller
    @ModelAttribute("serviceOrderLinePresentation")
    public ServiceOrderLinePresentation getServiceOrderLinePresentation(){
        return new ServiceOrderLinePresentation();
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
    @Autowired
    ServiceOrderStatusRepository serviceOrderStatusRepository;


    // Search Page Handling
    @GetMapping("/search")
    public String searchServiceOrder(Model theModel)
    {

        List<ServiceOrderPresentation> serviceOrderPresentations = serviceOrderService.getServiceOrderPresentation(serviceOrderService.findAllByOrOrderBySvoIdDesc());

        theModel.addAttribute("serviceOrderPresentations",serviceOrderPresentations);

        return("serviceOrderSearch");
    }

    @GetMapping(value = "/delete")
    public String deleteServiceOrder(@RequestParam("svoId") int svoId)
    {

        ServiceOrderStatus serviceOrderStatus = serviceOrderService.findServiceOrderStatusBySvoStatusId(5);

       ServiceOrder serviceOrder = serviceOrderService.findServiceOrderBySvoId(svoId);
       serviceOrder.setServiceOrderStatus(serviceOrderStatus);
       serviceOrderService.saveServiceOrder(serviceOrder);

        return "redirect:/service_orders/search";
    }

    // Service Order Profile Handling
    @GetMapping("/serviceOrderProfile")
     public String showServiceOrderProfile(@RequestParam("svoId") int svoId,Model theModel)
    {
        ServiceOrderPresentation serviceOrderPresentation = serviceOrderService.getServiceOrderPresentationForProfile(serviceOrderService.findServiceOrderBySvoId(svoId));

        theModel.addAttribute("serviceOrderPresentation", serviceOrderPresentation);

        List<Svc> svcs = serviceOrderPresentation.getSvcs();
        theModel.addAttribute("svcs", svcs);

        Set<Payment> payments = serviceOrderPresentation.getPayments();
        theModel.addAttribute("payments", payments);

        Set<Incident> incidents = serviceOrderPresentation.getIncidents();
        theModel.addAttribute("incidents", incidents);

        ServiceOrder serviceOrder = serviceOrderService.findServiceOrderBySvoId(svoId);
        theModel.addAttribute("serviceOrder", serviceOrder);

        List<HwPresentation> hwPresentations = serviceOrderService.getHwWorkedOn(svoId);
        theModel.addAttribute("hwPresentations", hwPresentations);

        return("serviceOrderProfile");
    }

    @GetMapping("/showServiceSummary")
    public String showServiceSummary(@ModelAttribute("serviceOrderPresentation") ServiceOrderPresentation serviceOrderPresentation,Model theModel)
    {
//        List<HwPresentation> hwPresentations = serviceOrderService.getCustSiteHwList(svoId);
//        theModel.addAttribute("hwPresentations", hwPresentations);

        int svoId = serviceOrderPresentation.getSvoId();
        System.out.println(svoId);
        List<ServiceOrderLinePresentation> serviceOrderLinePresentations =
                serviceOrderService.getServiceOrderLinePresentation(serviceOrderService.findServiceOrderBySvoId(svoId).getServiceOrderLines());

        theModel.addAttribute("serviceOrderLinePresentations",serviceOrderLinePresentations);

        ServiceOrder serviceOrder = serviceOrderService.findServiceOrderBySvoId(svoId);
        theModel.addAttribute("serviceOrder", serviceOrder);

        return("serviceOrderSummary");
    }

    @GetMapping("/showAddHwWorkedOn")
    public String showAddHwWorkedOn(@ModelAttribute("serviceOrderLinePresentation") ServiceOrderLinePresentation serviceOrderLinePresentation, Model theModel)
    {

        List<HwPresentation> hwPresentations = serviceOrderService.getCustSiteHwList(serviceOrderLinePresentation.getSvoLineId());
        theModel.addAttribute("hwPresentations", hwPresentations);

        serviceOrderLinePresentation.setHwPresentations(hwPresentations);

        theModel.addAttribute("serviceOrderLinePresentation", serviceOrderLinePresentation);

        return("serviceSummaryAddHwWorkedOn");
    }

    @PostMapping("/addHwWorkedOn")
    public String addHwWorkedOn(@ModelAttribute("serviceOrderLinePresentation") ServiceOrderLinePresentation serviceOrderLinePresentation, Model theModel)
    {
          List<HwPresentation> hwPresentations = serviceOrderLinePresentation.getHwPresentations();
          serviceOrderService.saveHwWorkedOn(hwPresentations, serviceOrderLinePresentation);

        return "redirect:/service_orders/showServiceSummary";
    }

    @GetMapping("/showAddServiceSummary")
    public String showAddServiceSummary(@ModelAttribute("serviceOrderPresentation") ServiceOrderPresentation serviceOrderPresentation, Model theModel)
    {
        theModel.addAttribute(serviceOrderPresentation);
        System.out.println(serviceOrderPresentation.getSvoId());
        System.out.println(serviceOrderPresentation.getWorkSummary());
        return("serviceSummaryAddSummary");
    }
    @PostMapping("/addServiceSummary")
    public String addServiceSummary(@ModelAttribute("serviceOrderPresentation") ServiceOrderPresentation serviceOrderPresentation)
    {
        ServiceOrder serviceOrder = serviceOrderService.findServiceOrderBySvoId(serviceOrderPresentation.getSvoId());
        serviceOrder.setWorkSummary(serviceOrderPresentation.getWorkSummary());
        serviceOrder.setTotal(serviceOrderPresentation.getTotal());
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        serviceOrder.setDateFinished(timestamp);
        serviceOrder.setServiceOrderStatus(serviceOrderStatusRepository.findServiceOrderStatusBySvoStatusId(1));
        // saves the work summary details
       serviceOrderService.saveServiceOrder(serviceOrder);
       // save hw_svo_line

        return "redirect:/service_orders/search";
    }

//    @GetMapping("/showSellHardware")
//    public String showSellHardware(@RequestParam("svoId") int svoId,Model theModel)
//    {
//        ServiceOrderPresentation serviceOrderPresentation = serviceOrderService.getServiceOrderPresentationForProfile(serviceOrderService.findServiceOrderBySvoId(svoId));
//
//        theModel.addAttribute("serviceOrderPresentation", serviceOrderPresentation);
//
//        Set<HwInventory> hwInventories =
//
//                Set<Incident> incidents = serviceOrderPresentation.getIncidents();
//        theModel.addAttribute("incidents", incidents);
//
//        ServiceOrder serviceOrder = serviceOrderService.findServiceOrderBySvoId(svoId);
//        theModel.addAttribute("serviceOrder", serviceOrder);
//
//        return("serviceOrderProfile");
//    }

//    @PostMapping("/sellHardware")
//    public String showServiceOrderProfile(@RequestParam("svoId") int svoId,Model theModel)
//    {
//        ServiceOrderPresentation serviceOrderPresentation = serviceOrderService.getServiceOrderPresentationForProfile(serviceOrderService.findServiceOrderBySvoId(svoId));
//
//        theModel.addAttribute("serviceOrderPresentation", serviceOrderPresentation);
//
//        List<Svc> svcs = serviceOrderPresentation.getSvcs();
//        theModel.addAttribute("svcs", svcs);
//
//        Set<Payment> payments = serviceOrderPresentation.getPayments();
//        theModel.addAttribute("payments", payments);
//
//        Set<Incident> incidents = serviceOrderPresentation.getIncidents();
//        theModel.addAttribute("incidents", incidents);
//
//        ServiceOrder serviceOrder = serviceOrderService.findServiceOrderBySvoId(svoId);
//        theModel.addAttribute("serviceOrder", serviceOrder);
//
//        return("serviceOrderProfile");
//    }

    // select current or new customer to create service order for
    @GetMapping("/serviceOrderSelection")
    public String serviceOrderSelection(Model theModel)
    {

        //NEED TO CHANGE THIS TO RETRIEVE CUSTOMER LIST, NOT LIST OF CUSTOMERS FROM SREVICE ORDERS
        List<ServiceOrderPresentation> customers = serviceOrderService.getServiceOrderPresentation(serviceOrderService.findAll());

        theModel.addAttribute("customers",customers);

        return ("addServiceOrderSelection");
    }

    // Create Service Order For New Customer Handling
    @GetMapping("/showFormForAddNewCustomer")
    public String showFormForAddNewCustomer(Model theModel)
    {

        ServiceOrderPresentation serviceOrderPresentation = new ServiceOrderPresentation();
        theModel.addAttribute("serviceOrderPresentation",serviceOrderPresentation);

        List<Svc> svcs = svcService.findActiveSvcs();
        theModel.addAttribute("svcs", svcs);

        List<Country> countries = countryService.findAll();
        theModel.addAttribute("countries", countries);

        List<StateProvince> stateProvinces = stateProvinceService.findAll();
        theModel.addAttribute("states", stateProvinces);

        return ("addServiceOrderNewCustomer");
    }

    @PostMapping("/addNewCustomer")
    public String addNewServiceOrder(@ModelAttribute("serviceOrderPresentation") ServiceOrderPresentation serviceOrderPresentation, final RedirectAttributes redirectAttributes)
    {

        // Save
        serviceOrderService.saveServiceOrderFromForm(serviceOrderPresentation);

        // Passes the model attribute to the showFormForAssigningContractors method to be used there
        redirectAttributes.addFlashAttribute("serviceOrderPresentation", serviceOrderPresentation);

        return "redirect:/service_orders/showFormForAssigningContractors";
    }


     // Create Service Order for existing customer handling

    @GetMapping(value="/showFormForAddCurrentCustomer")
    public String showFormForAddCurrentCustomer(Model theModel,@RequestParam("custSiteId") int custSiteId)
    {

        CustomerSite customerSite = customerSiteService.findCustomerSiteByCustSiteId(custSiteId);

        ServiceOrderPresentation serviceOrderPresentation = serviceOrderService.addCustomerSite(customerSite);

        List<Contact> contacts = contactService.findByCustSite(customerSite);
        List<Svc> svcs = svcService.findActiveSvcs();

        List<ContactType> contactTypes = contactTypeRepository.findAll();

        theModel.addAttribute("contactTypes", contactTypes);
        theModel.addAttribute("svcs", svcs);
        theModel.addAttribute("customer", serviceOrderPresentation);
        theModel.addAttribute("contacts",contacts);

        return ("addServiceOrderCurrentCustomer");
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




    // Add Assignments to the Service Order Lines
    @GetMapping("/showFormForAddAssignment")
    public String showFormForAddAssignment(Model theModel, @ModelAttribute("serviceOrderPresentation") ServiceOrderPresentation serviceOrderPresentation)
    {

        theModel.addAttribute("serviceOrderPresentation",serviceOrderPresentation);

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


    @GetMapping("/showFormForAssigningContractors")
    public String showFormForAssigningContractors(Model theModel, @ModelAttribute("serviceOrderPresentation") ServiceOrderPresentation serviceOrderPresentation)
    {

        List<ServiceOrderLinePresentation> serviceOrderLinePresentations = serviceOrderService.getServiceOrderLinePresentation(serviceOrderPresentation.getServiceOrderLines());
        theModel.addAttribute("serviceOrderLinePresentations",serviceOrderLinePresentations);

        theModel.addAttribute("serviceOrderPresentation",serviceOrderPresentation);

        List<Contractor> contractors = contractorRepository.findActiveContractors();
        theModel.addAttribute("contractors", contractors);

        List<Svc> svcs = serviceOrderPresentation.getSvcs();
        theModel.addAttribute("svcs", svcs);

        Set<ServiceOrderLine> serviceOrderLines = serviceOrderPresentation.getServiceOrderLines();
        theModel.addAttribute("serviceOrderLines",serviceOrderLines);

        return ("addServiceOrderAssignContractors");
    }

    @PostMapping("/assignContractors")
    public String assignContractors(Model theModel, @ModelAttribute("serviceOrderPresentation") ServiceOrderPresentation serviceOrderPresentation,final RedirectAttributes redirectAttributes)
    {

        theModel.addAttribute("serviceOrderPresentation", serviceOrderPresentation);

        // Save
        //serviceOrderService.saveServiceOrderFromCurrentCustomerForm(serviceOrderPresentation);

        serviceOrderService.addAssignments(serviceOrderPresentation);

        // Passes the model attribute to the showFormForAssigningContractors method to be used there
        redirectAttributes.addFlashAttribute("serviceOrderPresentation", serviceOrderPresentation);

        return "redirect:/service_orders/showFormForAssigningContractors";
    }






    // OLD CONTROLLER METHODS - Saving in case we change our minds ------------------------------------------------------

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


    /*
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
    }*/

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

}

