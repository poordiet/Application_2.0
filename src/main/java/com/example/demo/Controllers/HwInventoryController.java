package com.example.demo.Controllers;

import com.example.demo.Models.Incident;
import com.example.demo.Models.Payment;
import com.example.demo.Models.ServiceOrder;
import com.example.demo.Models.Svc;
import com.example.demo.Presentation.HwPresentation;
import com.example.demo.Presentation.ServiceOrderPresentation;
import com.example.demo.Service.HwInventoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@SessionAttributes("hwPresentation")
@RequestMapping("/hwInventory")
@Controller
public class HwInventoryController {

    // This makes the model attribute of hwPresentation persist throughout this whole controller
    @ModelAttribute("hwPresentation")
    public HwPresentation getHwPresentation(){
        return new HwPresentation();
    }

    @Autowired
    HwInventoryServiceImpl hwInventoryService;

    // Search Page Handling
    @GetMapping("/search")
    public String searchServiceOrder(Model theModel)
    {


        List<HwPresentation> hwPresentations = hwInventoryService.getAllHwInventoryForPresentation();

        theModel.addAttribute("hwPresentations",hwPresentations);

        return("hwInventorySearch");
    }

    // Hardware Inventory Profile Handling
    @GetMapping("/hwInventoryProfile")
    public String showHwInventoryProfile(@RequestParam("hwInvId") int hwInvId, Model theModel)
    {
        HwPresentation hwPresentation = hwInventoryService.getHwInventoryPresentationById(hwInvId);

        theModel.addAttribute("hwPresentation",hwPresentation);

        return("hwInventoryProfile");
    }

    // Edit Hardware Inventory
    @GetMapping("/hwInventoryProfileEdit")
    public String hwInventoryProfileEdit(@ModelAttribute("hwPresentation") HwPresentation hwPresentation, Model theModel)
    {
        theModel.addAttribute("hwPresentation", hwPresentation);



        return("hwInventoryProfileEdit");
    }

    @PostMapping("/hwInventoryProfileEditSubmit")
    public String hwInventoryProfileEditSubmit(@ModelAttribute("hwPresentation") HwPresentation hwPresentation, Model theModel)
    {


        hwInventoryService.updateHwInventory(hwPresentation);

        return "redirect:/hwInventory/search";
    }

}
