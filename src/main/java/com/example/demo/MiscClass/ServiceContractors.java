package com.example.demo.MiscClass;

import com.example.demo.Models.Contractor;
import com.example.demo.Models.Svc;

import java.util.List;

public class ServiceContractors {

    private Svc svc = new Svc();
    private Contractor contractor = new Contractor();
    private List<ServiceContractors> serviceContractors;

    public    Svc getSvc() {
        return svc;
    }

    public void setSvc(Svc svc) {
        this.svc = svc;
    }

    public Contractor getContractor() {
        return contractor;
    }

    public void setContractor(Contractor contractor) {
        this.contractor = contractor;
    }

    public List<ServiceContractors> getServiceContractors() {
        return serviceContractors;
    }

    public void setServiceContractors(List<ServiceContractors> serviceContractors) {
        this.serviceContractors = serviceContractors;
    }
}
