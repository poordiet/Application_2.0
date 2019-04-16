package com.example.demo.Controllers;

import com.example.demo.Models.*;
import com.example.demo.Presentation.HwPresentation;
import com.example.demo.Presentation.ServiceOrderPresentation;
import com.example.demo.Repositories.*;
import com.example.demo.Service.HwInventoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @Autowired
    HwInventoryRepository hwInventoryRepository;

    @Autowired
    HwInventoryStatusRepository hwInventoryStatusRepository;

    @Autowired
    HwManufacturerRepository hwManufacturerRepository;

    @Autowired
    HwSeriesRepository hwSeriesRepository;

    @Autowired
    HwModelRepository hwModelRepository;

    @Autowired
    HwTypeRepository hwTypeRepository;

    @Autowired
    HwProviderRepository hwProviderRepository;

    // Search Page Handling
    @GetMapping("/search")
    public String searchServiceOrder(Model theModel)
    {


        List<HwPresentation> hwPresentations = hwInventoryService.getAllHwInventoryForPresentation();

        theModel.addAttribute("hwPresentations",hwPresentations);

        return("hwInventorySearch");
    }

    @GetMapping(value = "/delete")
    public String deleteHwInventory(@RequestParam("hwInvId") int hwInvId)
    {

        HwInventoryStatus hwInventoryStatus = hwInventoryStatusRepository.findById(4);

        HwInventory hwInventory = hwInventoryService.findByHwInvId(hwInvId);
        hwInventory.setHwInventoryStatus(hwInventoryStatus);
        hwInventoryRepository.save(hwInventory);

        return "redirect:/hwInventory/search";
    }

    // Hardware Inventory Profile Handling
    @GetMapping("/hwInventoryProfile")
    public String showHwInventoryProfile(@RequestParam("hwInvId") int hwInvId, Model theModel)
    {
        HwPresentation hwPresentation = hwInventoryService.getHwInventoryPresentationById(hwInvId);

        theModel.addAttribute("hwPresentation",hwPresentation);

        return("hwInventoryProfile");
    }

    // Add Hardware Inventory
    // Create Service Order For New Customer Handling
    @GetMapping("/showAddHwInventory")
    public String showAddHwInventory(Model theModel)
    {

        HwPresentation hwPresentation = new HwPresentation();
        theModel.addAttribute("hwPresentation", hwPresentation);

        List<HwManufacturer> hwManufacturers = hwManufacturerRepository.findAllHwManufacturers();
        theModel.addAttribute("hwManufacturers", hwManufacturers);

        List<HwSeries> hwSeriesList = hwSeriesRepository.findAllHwSeries();
        theModel.addAttribute("hwSeriesList", hwSeriesList);

        List<HwModel> hwModels = hwModelRepository.findAllHwModels();
        theModel.addAttribute("hwModels", hwModels);

        List<HwType> hwTypes = hwTypeRepository.findAllByOrderByHwTypeId();
        theModel.addAttribute("hwTypes", hwTypes);

        List<HwProvider> hwProviders = hwProviderRepository.findAllHwProviders();
        theModel.addAttribute("hwProviders", hwProviders);

        return ("addHwInventory");
    }

    @PostMapping("/addHwInventory")
    public String addHwInventory(@ModelAttribute("hwPresentation") HwPresentation hwPresentation)
    {

        // Save
        hwInventoryService.addHwInventory(hwPresentation);

        // Passes the model attribute to the showFormForAssigningContractors method to be used there
//        redirectAttributes.addFlashAttribute("serviceOrderPresentation", serviceOrderPresentation);

        return "redirect:/hwInventory/search";
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
