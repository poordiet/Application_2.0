package com.example.demo.Controllers;

import com.example.demo.Models.*;
import com.example.demo.Presentation.ServiceOrderPresentation;
import com.example.demo.Repositories.CountryRepository;
import com.example.demo.Repositories.ServiceOrderRepository;
import com.example.demo.Repositories.SvcRepository;
import com.example.demo.Service.CountryService;
import com.example.demo.Service.ServiceOrderServiceImpl;
import com.example.demo.Service.StateProvinceService;
import com.example.demo.Service.SvcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

        System.out.println(svoId);
        System.out.println("test");

        ServiceOrderStatus serviceOrderStatus = serviceOrderService.findServiceOrderStatusBySvoStatusId(3);

       ServiceOrder serviceOrder = serviceOrderService.findServiceOrderBySvoId(svoId);
       serviceOrder.setServiceOrderStatus(serviceOrderStatus);
       serviceOrderService.saveServiceOrder(serviceOrder);

        return "redirect:/service_orders/search";
    }

    @PostMapping("/add")
    public String addServiceOrder(@ModelAttribute("serviceOrderPresentation") ServiceOrderPresentation serviceOrderPresentation,
                                  @ModelAttribute("stateProvince") StateProvince stateProvince, @ModelAttribute("country") Country country,
                                  @ModelAttribute("svc") Svc svc)
    {

        System.out.println(serviceOrderPresentation.getContactEmail());
        System.out.println(serviceOrderPresentation.getWorkRequest());
        System.out.println(stateProvince.getStateName());
        System.out.println (country.getCountryName());
        System.out.println(svc.getSvcName());
        // Save
        serviceOrderService.saveServiceOrderFromForm(serviceOrderPresentation);

        return("addServiceOrder");

        // use a redirect to prevent duplicate submissions
        // return "redirect:/service_orders/showFormforAdd";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel)
    {

        ServiceOrderPresentation serviceOrderPresentation = new ServiceOrderPresentation();
        theModel.addAttribute("serviceOrderPresentation",serviceOrderPresentation);

        List<Svc> svcs = svcService.findAll();
        theModel.addAttribute("svcs", svcs);

        List<Country> countries = countryService.findAll();
        theModel.addAttribute("countries", countries);

        List<StateProvince> stateProvinces = stateProvinceService.findAll();
        theModel.addAttribute("states", stateProvinces);

        System.out.println(serviceOrderPresentation.getWorkRequest());
        System.out.println(serviceOrderPresentation.getContactEmail());

        return ("addServiceOrder");
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

