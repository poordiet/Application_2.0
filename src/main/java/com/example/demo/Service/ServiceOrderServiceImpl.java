package com.example.demo.Service;

import com.example.demo.Assignment;
import com.example.demo.Presentation.ServiceOrderPresentation;
import com.example.demo.Repositories.ServiceOrderLineRepository;
import com.example.demo.Repositories.ServiceOrderRepository;
import com.example.demo.ServiceOrder;
import com.example.demo.ServiceOrderLine;
import com.example.demo.Svc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class ServiceOrderServiceImpl implements ServiceOrderService {

    @Autowired
    ServiceOrderRepository serviceOrderRepository;

    @Autowired
    ServiceOrderLineRepository serviceOrderLineRepository;

    @Override
    public void addServiceOrder(ServiceOrder serviceOrder) { serviceOrderRepository.save(serviceOrder);}

    @Override
    public List<ServiceOrder> findAll() {
        return serviceOrderRepository.findAllByOrderByDateScheduledDesc();
    }

    public List<ServiceOrderPresentation> setServiceOrderPresentation(List<ServiceOrder> serviceOrders){

        List<ServiceOrderPresentation> serviceOrderPresentations = new ArrayList<>();


        for (ServiceOrder serviceOrder1:serviceOrders) {

            int count = 0;

            ServiceOrderPresentation serviceOrderPresentation = new ServiceOrderPresentation();
            String svcName = new String();
            String contactName;
            String contractors = new String();
            String location;

            serviceOrderPresentation.setDateFinished(serviceOrder1.getDateFinished());
            serviceOrderPresentation.setDateRequested(serviceOrder1.getDateRequested());
            serviceOrderPresentation.setDateScheduled(serviceOrder1.getDateScheduled());
            serviceOrderPresentation.setDateStarted(serviceOrder1.getDateStarted());
            serviceOrderPresentation.setSvoId(serviceOrder1.getSvoId());
            serviceOrderPresentation.setTotal(serviceOrder1.getTotal());
            serviceOrderPresentation.setServiceOrderLines(serviceOrder1.getServiceOrderLines());
            serviceOrderPresentation.setCustSiteId(serviceOrder1.getCustomerSite().getCustSiteId());
            serviceOrderPresentation.setCustSiteNumber(serviceOrder1.getCustomerSite().getCustSiteNumber());
            serviceOrderPresentation.setCustSiteName(serviceOrder1.getCustomerSite().getCustSiteName());
            serviceOrderPresentation.setSvoStatus(serviceOrder1.getServiceOrderStatus().getSvoStatus());

            location = serviceOrder1.getCustomerSite().getCustSiteAddress()+ " " + serviceOrder1.getCustomerSite().getCustSiteCity() + ", "+
                    serviceOrder1.getCustomerSite().getStateProvince().getStateName() + ", " + serviceOrder1.getCustomerSite().getCustSiteZip();

            serviceOrderPresentation.setCustSiteLocation(location);

           contactName = serviceOrder1.getContact().getContactFname() + " " + serviceOrder1.getContact().getContactLname();
           serviceOrderPresentation.setContactName(contactName);

            Set<ServiceOrderLine> serviceOrderLines = serviceOrder1.getServiceOrderLines();


            int count2 = 0;

            for(ServiceOrderLine serviceOrderLine1:serviceOrderLines)
            {

                serviceOrderLine1.getSvc().getSvcName();
                if(count > 0)
                {
                    svcName += ", ";
                }
                svcName+=serviceOrderLine1.getSvc().getSvcName();
                System.out.println(svcName);
                serviceOrderPresentation.setSvcName(svcName);
                count++;


                Set<Assignment> assignments = serviceOrderLine1.getAssignments();

                for(Assignment assignment: assignments) {
                    if (count2 > 0) {
                        contractors += ", ";
                    }
                    contractors += assignment.getContractor().getContractorFname() + " " + assignment.getContractor().getContractorLname();
                    System.out.println(contractors);
                    serviceOrderPresentation.setContractors(contractors);
                    count2++;
                }
            }

            serviceOrderPresentations.add(serviceOrderPresentation);

        }
        return serviceOrderPresentations;
    }

}
