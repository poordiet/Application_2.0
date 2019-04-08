package com.example.demo.Controllers;

import com.example.demo.Presentation.ServiceOrderPresentation;
import com.example.demo.Repositories.ServiceOrderRepository;
import com.example.demo.Repositories.SvcRepository;
import com.example.demo.Service.ServiceOrderService;
import com.example.demo.Service.ServiceOrderServiceImpl;
import com.example.demo.ServiceOrder;
import com.example.demo.ServiceOrderLine;
import com.example.demo.Svc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Set;

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

        List<ServiceOrderPresentation> serviceOrderPresentations = serviceOrderService.setServiceOrderPresentation(serviceOrderService.findAll());

        theModel.addAttribute("serviceOrderPresentations",serviceOrderPresentations);
        Iterable<Svc> services = svcRepository.findAll();
        theModel.addAttribute("services",services);
        ServiceOrder serviceOrder = new ServiceOrder();
        ServiceOrderLine serviceOrderLine = new ServiceOrderLine();
        Set<ServiceOrderLine> serviceOrderLines = serviceOrder.getServiceOrderLines();
       // System.out.println(serviceOrderLines);
        return("/front-end-master_2.0/front-end/service_order_search");
    }

}

