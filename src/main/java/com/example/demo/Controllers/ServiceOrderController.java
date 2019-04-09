package com.example.demo.Controllers;

import com.example.demo.Presentation.ServiceOrderPresentation;
import com.example.demo.Repositories.ServiceOrderRepository;
import com.example.demo.Repositories.SvcRepository;
import com.example.demo.Service.ServiceOrderServiceImpl;
import com.example.demo.Models.ServiceOrder;
import com.example.demo.Models.ServiceOrderStatus;
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


    @GetMapping("/search")
    public String searchServiceOrder(Model theModel)
    {

        List<ServiceOrderPresentation> serviceOrderPresentations = serviceOrderService.getServiceOrderPresentation(serviceOrderService.findAll());

        theModel.addAttribute("serviceOrderPresentations",serviceOrderPresentations);

        return("service_order_search");
    }

    @GetMapping(value = "/delete")
    public String handleDeleteServiceOrder(@RequestParam("svoId") int svoId)
    {

        System.out.println(svoId);
        System.out.println("test");

        ServiceOrderStatus serviceOrderStatus = serviceOrderService.findServiceOrderStatusBySvoStatusId(3);

       ServiceOrder serviceOrder = serviceOrderService.findServiceOrderBySvoId(svoId);
       serviceOrder.setServiceOrderStatus(serviceOrderStatus);
       serviceOrderService.saveServiceOrder(serviceOrder);

        return "redirect:/service_orders/search";
    }

    @GetMapping("/add")
    public String handleAddServiceOrder()
    {

        return("/addServiceOrder");
    }

}

