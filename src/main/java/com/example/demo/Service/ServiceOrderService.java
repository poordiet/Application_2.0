package com.example.demo.Service;


import com.example.demo.Models.ServiceOrder;
import com.example.demo.Models.ServiceOrderStatus;
import com.example.demo.Presentation.ServiceOrderPresentation;

import java.util.List;

public interface ServiceOrderService {

    public List<ServiceOrder> findAll();

    public void addServiceOrder(ServiceOrder serviceOrder);

    public List<ServiceOrder> findServiceOrderInProgress();

    public ServiceOrder findServiceOrderBySvoId(int svoId);

    public void saveServiceOrder(ServiceOrder serviceOrder);

    public ServiceOrderStatus findServiceOrderStatusBySvoStatusId(int svoStatusId);

    public void saveServiceOrderFromForm(ServiceOrderPresentation serviceOrderPresentation);

    public List<ServiceOrder> findAllByOrOrderBySvoIdDesc();
}
