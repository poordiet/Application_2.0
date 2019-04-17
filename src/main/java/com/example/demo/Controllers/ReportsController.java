package com.example.demo.Controllers;

import com.example.demo.Models.*;
import com.example.demo.Presentation.CustomerPresentation;
import com.example.demo.Presentation.HwPresentation;
import com.example.demo.Presentation.IncidentPresentation;
import com.example.demo.Presentation.ServiceOrderPresentation;
import com.example.demo.Repositories.ContractorRepository;
import com.example.demo.Repositories.CustomerSiteRepository;
import com.example.demo.Repositories.ServiceOrderRepository;
import com.example.demo.Repositories.StateProvinceRepository;
import com.example.demo.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import static jdk.nashorn.internal.objects.NativeMath.round;

@SessionAttributes({"serviceOrderPresentation","skills","states"})
@RequestMapping("/reports")
@Controller
public class ReportsController {

    @ModelAttribute("serviceOrderPresentation")
    public ServiceOrderPresentation getServiceOrderPresentation(){
        return new ServiceOrderPresentation();
    }

    @ModelAttribute("skills")
    public Skill getSkills(){
        return new Skill();
    }

    @ModelAttribute("states")
    public StateProvince getStates(){
        return new StateProvince();
    }

    @Autowired
    ReportsService reportsService;

    @Autowired
    ServiceOrderServiceImpl serviceOrderService;

    @Autowired
    ServiceOrderRepository serviceOrderRepository;
    @Autowired
    CustomerSiteService customerSiteService;
    @Autowired
    CustomerSiteRepository customerSiteRepository;
    @Autowired
    CustomerService customerService;
    @Autowired
    ContractorRepository contractorRepository;
    @Autowired
    ContractorService contractorService;
    @Autowired
    StateProvinceRepository stateProvinceRepository;

    @GetMapping("/reportsPage")
    public String reportsPage()
    {
        return("reportsPage");
    }

    // Search Page Handling
    @GetMapping("/monthlyServiceOrders")
    public String monthlyServiceOrders(Model theModel)
    {

        List<ServiceOrderPresentation> serviceOrderPresentations = serviceOrderService.getServiceOrderPresentation(serviceOrderRepository.findMonthlyServiceOrders());

        theModel.addAttribute("serviceOrderPresentations",serviceOrderPresentations);

        double monthTotal = 0.0;
        int countServiceOrder = 0;

        for(ServiceOrderPresentation serviceOrderPresentation: serviceOrderPresentations) {
            monthTotal += serviceOrderPresentation.getTotal().doubleValue();
            countServiceOrder += 1;
        }
//        monthTotal = Math.round(monthTotal*100)/100;
        DecimalFormat df2 = new DecimalFormat("###.##");
        monthTotal = Double.valueOf(df2.format(monthTotal));
        theModel.addAttribute("monthTotal",monthTotal);
        theModel.addAttribute("countServiceOrder",countServiceOrder);

        DateFormat dateFormat = new SimpleDateFormat("MM/dd/YYYY");

        Calendar cal1 = Calendar.getInstance();
        Date result1 = cal1.getTime();
        String currentDate = dateFormat.format(result1);

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        Date result2 = cal.getTime();
        String pastMonth = dateFormat.format(result2);

        theModel.addAttribute("currentDate", currentDate);
        theModel.addAttribute("pastMonth",pastMonth);




        return("reportMonthlyServiceOrder");
    }


    // Search Page Handling
    @GetMapping("/serviceOrderSearch")
    public String serviceOrderSearch(Model theModel)
    {

        List<ServiceOrderPresentation> serviceOrderPresentations = serviceOrderService.getServiceOrderPresentation(serviceOrderService.findAllByOrOrderBySvoIdDesc());

        theModel.addAttribute("serviceOrderPresentations",serviceOrderPresentations);

        return("serviceOrderSearchForReport");
    }

    // Service Order Profile Handling
    @GetMapping("/serviceOrderProfile")
    public String showServiceOrderProfile(@RequestParam("svoId") int svoId, Model theModel)
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

        return("serviceOrderProfileForReport");
    }

    // Service Order Profile Handling
    @GetMapping("/servicesProvided")
    public String showServicesProvided( Model theModel)
    {
        List<ServiceOrderPresentation> serviceOrderPresentations = reportsService.getServicesProvidedPresentation();

        theModel.addAttribute("serviceOrderPresentations",serviceOrderPresentations);
        return("reportServicesProvided");
    }

    // Hardware Sales Report
    @GetMapping("/hardwareSales")
    public String showHardwareSales( Model theModel)
    {
        List<HwPresentation> hwPresentations = reportsService.getHardwareSales();

        double totalSalePrice = 0.0;

        for(HwPresentation hwPresentation:hwPresentations)
        {
            totalSalePrice+= hwPresentation.getHwSalePrice().doubleValue();
        }
//        totalSalePrice = Math.round(totalSalePrice*100)/100;
        DecimalFormat df2 = new DecimalFormat("###.##");
        totalSalePrice = Double.valueOf(df2.format(totalSalePrice));

        theModel.addAttribute("totalSalePrice",totalSalePrice);
        theModel.addAttribute("hwPresentations",hwPresentations);

        DateFormat dateFormat = new SimpleDateFormat("MM/dd/YYYY");

        Calendar cal1 = Calendar.getInstance();
        Date result1 = cal1.getTime();
        String currentDate = dateFormat.format(result1);

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        Date result2 = cal.getTime();
        String pastMonth = dateFormat.format(result2);

        theModel.addAttribute("currentDate", currentDate);
        theModel.addAttribute("pastMonth",pastMonth);

        return("reportHwSales");
    }

    // Hardware Sales Report
    @GetMapping("/serviceOrderIncidentReport")
    public String serviceOrderIncidentReport( Model theModel)
    {
        List<IncidentPresentation> incidentPresentations = reportsService.getIncidents();

        theModel.addAttribute("incidentPresentations", incidentPresentations);

        int count = 0;

        for(IncidentPresentation incidentPresentation: incidentPresentations)
        {
        count ++;
        }

        theModel.addAttribute("count",count);

        DateFormat dateFormat = new SimpleDateFormat("MM/dd/YYYY");

        Calendar cal1 = Calendar.getInstance();
        Date result1 = cal1.getTime();
        String currentDate = dateFormat.format(result1);

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        Date result2 = cal.getTime();
        String pastMonth = dateFormat.format(result2);

        theModel.addAttribute("currentDate", currentDate);
        theModel.addAttribute("pastMonth",pastMonth);

        return("reportServiceOrderIncidentReport");
    }

    @GetMapping("/customerSiteSearch")
    public String searchCustomer(Model theModel)
    {

        List<CustomerPresentation> customerPresentation = customerService.getCustomerPresentation(customerSiteRepository.findCustomerSiteByABunch());

        theModel.addAttribute("customerPresentations",customerPresentation);

        return("reportCustomerSearch");
    }



    @GetMapping("/customersGained")
    public String customersGained(Model theModel)
    {

        List<CustomerPresentation> customerPresentations = customerService.getCustomerPresentation(customerSiteRepository.findAllCustomersGainedMonthly());

        theModel.addAttribute("customerPresentations",customerPresentations);

        int count =0;

        for(CustomerPresentation customerPresentation1: customerPresentations)
        {
            count++;
        }

        theModel.addAttribute("count",count);

        DateFormat dateFormat = new SimpleDateFormat("MM/dd/YYYY");

        Calendar cal1 = Calendar.getInstance();
        Date result1 = cal1.getTime();
        String currentDate = dateFormat.format(result1);

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        Date result2 = cal.getTime();
        String pastMonth = dateFormat.format(result2);

        theModel.addAttribute("currentDate", currentDate);
        theModel.addAttribute("pastMonth",pastMonth);

        return("reportCustomersGained");
    }

    @GetMapping("/operationalCustomers")
    public String operatingCustomers(Model theModel)
    {

        List<CustomerPresentation> customerPresentations = customerService.getCustomerPresentation(customerSiteRepository.findActiveCustomerSiteOrderByNameThenNumber());

        theModel.addAttribute("customerPresentations",customerPresentations);

        int count =0;

        for(CustomerPresentation customerPresentation1: customerPresentations)
        {
            count++;
        }

        theModel.addAttribute("count",count);

        DateFormat dateFormat = new SimpleDateFormat("MM/dd/YYYY");

        Calendar cal1 = Calendar.getInstance();
        Date result1 = cal1.getTime();
        String currentDate = dateFormat.format(result1);

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        Date result2 = cal.getTime();
        String pastMonth = dateFormat.format(result2);

        theModel.addAttribute("currentDate", currentDate);
        theModel.addAttribute("pastMonth",pastMonth);

        return("reportOperationalCustomers");
    }



    @GetMapping("/contractorsGained")
    public String contractorsGained(Model theModel)
    {

        List<ServiceOrderPresentation> serviceOrderPresentations = contractorService.getContractors(contractorRepository.findAllContractorsGained());

        theModel.addAttribute("serviceOrderPresentations",serviceOrderPresentations);

        int count =0;

        for(ServiceOrderPresentation serviceOrderPresentation: serviceOrderPresentations)
        {
            count++;
        }

        theModel.addAttribute("count",count);

        DateFormat dateFormat = new SimpleDateFormat("MM/dd/YYYY");

        Calendar cal1 = Calendar.getInstance();
        Date result1 = cal1.getTime();
        String currentDate = dateFormat.format(result1);

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        Date result2 = cal.getTime();
        String pastMonth = dateFormat.format(result2);

        theModel.addAttribute("currentDate", currentDate);
        theModel.addAttribute("pastMonth",pastMonth);

        return("reportContractorsHired");
    }

    @GetMapping("/contractorSearch")
    public String searchContractor(Model theModel)
    {


        List<ServiceOrderPresentation> serviceOrderPresentations = contractorService.getContractors(contractorRepository.findAllNotDeleted());

        theModel.addAttribute("serviceOrderPresentations",serviceOrderPresentations);

        return("searchContractorsForReports");
    }

    @GetMapping("/contractorProfile")
    public String showContractorProfile(@RequestParam("contractorId") int contractorId, Model theModel)
    {


        ServiceOrderPresentation serviceOrderPresentation = contractorService.getServiceOrderPresentationById(contractorId);

        theModel.addAttribute("serviceOrderPresentation",serviceOrderPresentation);

        List<Skill> skills = contractorService.getContractorSkills(contractorId);
        theModel.addAttribute("skills", skills);

        return("contractorProfileForReport");
    }

    @GetMapping("/chooseStateForContractor")
    public String chooseStateForContractor(Model theModel)
    {
        ServiceOrderPresentation serviceOrderPresentation = new ServiceOrderPresentation();
        theModel.addAttribute("serviceOrderPresentation",serviceOrderPresentation);

        List<StateProvince> states = stateProvinceRepository.findAll();


        theModel.addAttribute("states",states);


        return("chooseStateForContractor");
    }


    @PostMapping("/chooseStateForContractor")
    public String chooseStateForContractor(@ModelAttribute("serviceOrderPresentation") ServiceOrderPresentation serviceOrderPresentation, Model theModel)
    {
        System.out.println(serviceOrderPresentation.getStateId());


        return "redirect:/reports/contractorsPerState";
    }


    @GetMapping("/contractorsPerState")
    public String contractorsPerState(@ModelAttribute("serviceOrderPresentations") ServiceOrderPresentation serviceOrderPresentation,Model theModel)
    {
        System.out.println(serviceOrderPresentation.getStateId());
        StateProvince state = stateProvinceRepository.findByStateId(serviceOrderPresentation.getStateId());
        List<Contractor> contractors = contractorRepository.findContractorByState(state);
        List<ServiceOrderPresentation> serviceOrderPresentations = contractorService.getContractors(contractors);

        for(ServiceOrderPresentation serviceOrderPresentation1: serviceOrderPresentations)
        {
            System.out.println(serviceOrderPresentation1.getStateId());
            System.out.println(serviceOrderPresentation1.getContractorFname());
        }

        System.out.println(serviceOrderPresentation.getContractorFname());
        System.out.println(serviceOrderPresentation);
        theModel.addAttribute("serviceOrderPresentations",serviceOrderPresentation);


        return("reportContractorsPerState");
    }



}

