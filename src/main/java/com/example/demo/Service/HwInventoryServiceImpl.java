package com.example.demo.Service;

import com.example.demo.Models.HwInventory;
import com.example.demo.Presentation.HwPresentation;
import com.example.demo.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;



@Service
public class HwInventoryServiceImpl implements HwInventoryService {

    @Autowired
    HwInventoryRepository hwInventoryRepository;

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

    @Autowired
    HwInventoryStatusRepository hwInventoryStatusRepository;

    @Override
    public HwInventory findByHwInvId(int id)
    {
        return hwInventoryRepository.findByHwInvId(id);
    }

    public List<HwInventory> findAllHwInventory()
    {
        return hwInventoryRepository.findAllByOrderByHwInventoryStatusAscHwInvId();
    }


    public List<HwPresentation> getAllHwInventoryForPresentation()
    {
        List<HwPresentation> hwPresentations = new ArrayList<>();
        List<HwInventory> hwInventories =  hwInventoryRepository.findAllByOrderByHwInventoryStatusAscHwInvId();

        for(HwInventory hwInventory: hwInventories)
        {
            HwPresentation hwPresentation = new HwPresentation();
            hwPresentation.setHwInvId(hwInventory.getHwInvId());
            hwPresentation.setHwManuName(hwInventory.getHwModel().getHwSeries().getHwManufacturer().getHwManuName());
            hwPresentation.setHwSeriesName(hwInventory.getHwModel().getHwSeries().getHwSeriesName());
            hwPresentation.setHwModel(hwInventory.getHwModel().getHwModel());
            hwPresentation.setHwSerialNumber(hwInventory.getHwSerialNumber());
            hwPresentation.setHwMacAddress(hwInventory.getHwMacAddress());
            hwPresentation.setHwPurchaseDate(hwInventory.getHwPurchaseDate());
            hwPresentation.setHwCost(hwInventory.getHwCost());
            hwPresentation.setHwInvStatus(hwInventory.getHwInventoryStatus().getHwInvStatus());

            hwPresentations.add(hwPresentation);
        }



        return hwPresentations;
    }

    public HwPresentation getHwInventoryPresentationById(int hwInvId)
    {

        HwInventory hwInventory =  hwInventoryRepository.findByHwInvId(hwInvId);


            HwPresentation hwPresentation = new HwPresentation();
            hwPresentation.setHwInvId(hwInventory.getHwInvId());
            hwPresentation.setHwManuName(hwInventory.getHwModel().getHwSeries().getHwManufacturer().getHwManuName());
            hwPresentation.setHwSeriesName(hwInventory.getHwModel().getHwSeries().getHwSeriesName());
            hwPresentation.setHwModel(hwInventory.getHwModel().getHwModel());
            hwPresentation.setHwSerialNumber(hwInventory.getHwSerialNumber());
            hwPresentation.setHwMacAddress(hwInventory.getHwMacAddress());
            hwPresentation.setHwPurchaseDate(hwInventory.getHwPurchaseDate());
            hwPresentation.setHwCost(hwInventory.getHwCost());
            hwPresentation.setHwInvStatus(hwInventory.getHwInventoryStatus().getHwInvStatus());
            hwPresentation.setHwSalePrice(hwInventory.getHwSalePrice());
            hwPresentation.setHwType(hwInventory.getHwModel().getHwSeries().getHwType().getHwType());
            hwPresentation.setHwManuId(hwInventory.getHwModel().getHwSeries().getHwManufacturer().getHwManuId());

            return hwPresentation;
        }


        public void addHwInventory(HwPresentation hwPresentation)
        {
            HwInventory hwInventory =  new HwInventory();

            hwInventory.setHwPurchaseDate( hwPresentation.getHwPurchaseDate());
            hwInventory.setHwProvider(hwProviderRepository.findById(hwPresentation.getHwProviderId()));
            hwInventory.setHwModel(hwModelRepository.findById(hwPresentation.getHwModelId()));
//            hwPresentation.getHwTypeId();
//            hwPresentation.getHwManuId();
//            hwPresentation.getHwSeriesId();
            hwInventory.setHwSerialNumber(hwPresentation.getHwSerialNumber());
            hwInventory.setHwMacAddress(hwPresentation.getHwMacAddress());
            hwInventory.setHwPurchaseDate(hwPresentation.getHwPurchaseDate());
            hwInventory.setHwCost(hwPresentation.getHwCost());
            hwInventory.setHwSalePrice(hwPresentation.getHwSalePrice());
            hwInventory.setHwInventoryStatus(hwInventoryStatusRepository.findById(1));
            hwInventoryRepository.save(hwInventory);
        }


    public void updateHwInventory(HwPresentation hwPresentation)
    {

        HwInventory hwInventory =  hwInventoryRepository.findByHwInvId(hwPresentation.getHwInvId());

        hwInventory.setHwCost(hwPresentation.getHwCost());
        hwInventory.setHwMacAddress(hwPresentation.getHwMacAddress());
        hwInventory.setHwSalePrice(hwPresentation.getHwSalePrice());
        hwInventory.setHwSerialNumber(hwPresentation.getHwSerialNumber());

        hwInventoryRepository.save(hwInventory);

    }



}


