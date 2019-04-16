package com.example.demo.Repositories;

import com.example.demo.Models.HwInventoryStatus;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HwInventoryStatusRepository extends CrudRepository<HwInventoryStatus,Integer> {

    HwInventoryStatus findById(int id);
}
