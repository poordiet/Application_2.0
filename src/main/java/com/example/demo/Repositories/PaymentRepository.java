package com.example.demo.Repositories;

import com.example.demo.Models.Payment;
import org.springframework.data.repository.CrudRepository;

public interface PaymentRepository extends CrudRepository<Payment,Integer> {

}
