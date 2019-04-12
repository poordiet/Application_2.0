package com.example.demo.Controllers;

import com.example.demo.Presentation.ServiceOrderPresentation;
import com.example.demo.Repositories.ServiceOrderRepository;
import com.example.demo.Repositories.SvcRepository;
import com.example.demo.Service.ServiceOrderService;
import com.example.demo.Service.ServiceOrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/home")
@Controller
public class HomeController {

    @Autowired
    ServiceOrderRepository serviceOrderRepository;
    @Autowired
    SvcRepository svcRepository;
    @Autowired
    ServiceOrderServiceImpl serviceOrderServiceImpl;

    @GetMapping("/home")
    public String searchServiceOrder(Model theModel)
    {
        List<ServiceOrderPresentation> serviceOrderPresentations = serviceOrderServiceImpl.getServiceOrderPresentation(serviceOrderServiceImpl.findServiceOrderInProgress());

        theModel.addAttribute("serviceOrderPresentations",serviceOrderPresentations);

        return("index");
    }
}
