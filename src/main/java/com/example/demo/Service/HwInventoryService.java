package com.example.demo.Service;

import com.example.demo.Models.HwInventory;

import java.util.List;

public interface HwInventoryService {

    List<HwInventory> findAllHwInventory();

    HwInventory findByHwInvId(int id);

}
