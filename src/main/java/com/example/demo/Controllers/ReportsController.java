package com.example.demo.Controllers;

import com.example.demo.Models.*;
import com.example.demo.Presentation.CustomerPresentation;
import com.example.demo.Presentation.HwPresentation;
import com.example.demo.Presentation.IncidentPresentation;
import com.example.demo.Presentation.ServiceOrderPresentation;
import com.example.demo.Repositories.CustomerSiteRepository;
import com.example.demo.Repositories.ServiceOrderRepository;
import com.example.demo.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import static jdk.nashorn.internal.objects.NativeMath.round;

@RequestMapping("/reports")
@Controller
public class ReportsController {

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
        monthTotal = Math.round(monthTotal*100)/100;
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
        totalSalePrice = Math.round(totalSalePrice*100)/100;
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

    @GetMapping("/operationalCustomers")
    public String operationalCustomers(Model theModel)
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

    @GetMapping("/operatingCustomers")
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

}

