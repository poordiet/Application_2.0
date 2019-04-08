package com.example.demo.Service;


import com.example.demo.ServiceOrder;

import java.util.List;

public interface ServiceOrderService {

    public List<ServiceOrder> findAll();

    public void addServiceOrder(ServiceOrder serviceOrder);

}
