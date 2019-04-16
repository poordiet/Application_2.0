package com.example.demo.Repositories;

import com.example.demo.Models.CustomerSite;
import com.example.demo.Models.CustomerSiteHw;
import com.example.demo.Models.HwInventory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HwInventoryRepository extends CrudRepository<HwInventory,Integer> {

    @Query(value = "select * from HW_INVENTORY \n" +
            "INNER JOIN HW_MODEL\n" +
            "ON HW_MODEL.hw_model_id = HW_INVENTORY.hw_inv_id\n" +
            "INNER JOIN HW_SERIES\n" +
            "ON HW_SERIES.hw_series_id = HW_MODEL.hw_series_id\n" +
            "INNER JOIN HW_MANUFACTURER\n" +
            "ON HW_MANUFACTURER.hw_manu_id = HW_SERIES.hw_manu_id\n" +
            "WHERE HW_INVENTORY.hw_inv_status_id = 1\n" +
            "ORDER BY hw_manu_name, hw_series_name, hw_model\n" +
            ";" , nativeQuery = true)
    List<HwInventory> findAllHwInventoryInStock();

    @Query(value = "select * from HW_INVENTORY  WHERE HW_INVENTORY.hw_inv_status_id = 1" , nativeQuery = true)
    List<HwInventory> findAllHwInventoryOrderByHwModel_HwSeries_HwManufacturer_HwManuName();

    @Query(value="select * from HW_INVENTORY INNER JOIN HW_INVENTORY_STATUS ON HW_INVENTORY_STATUS.hw_inv_status_id=HW_INVENTORY.hw_inv_status_id WHERE hw_inv_status != 'Deleted' ORDER BY HW_INVENTORY_STATUS.hw_inv_status_id, HW_INVENTORY.hw_inv_id", nativeQuery = true)
    List<HwInventory> findAllByOrderByHwInventoryStatusAscHwInvId();


    HwInventory findByHwInvId(int id);




}
