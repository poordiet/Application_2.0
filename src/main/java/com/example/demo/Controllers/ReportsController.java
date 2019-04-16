package com.example.demo.Controllers;

import com.example.demo.Models.Incident;
import com.example.demo.Models.Payment;
import com.example.demo.Models.ServiceOrder;
import com.example.demo.Models.Svc;
import com.example.demo.Presentation.HwPresentation;
import com.example.demo.Presentation.ServiceOrderPresentation;
import com.example.demo.Repositories.ServiceOrderRepository;
import com.example.demo.Service.ReportsService;
import com.example.demo.Service.ServiceOrderService;
import com.example.demo.Service.ServiceOrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

@RequestMapping("/reports")
@Controller
public class ReportsController {

    @Autowired
    ReportsService reportsService;

    @Autowired
    ServiceOrderServiceImpl serviceOrderService;

    @Autowired
    ServiceOrderRepository serviceOrderRepository;
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

}
