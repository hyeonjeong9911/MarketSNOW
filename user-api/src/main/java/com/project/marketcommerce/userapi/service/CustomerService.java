package com.project.marketcommerce.userapi.service;

import com.project.marketcommerce.userapi.domain.model.Customer;
import com.project.marketcommerce.userapi.domain.repository.CustomerRepository;

import java.util.Optional;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

  private final CustomerRepository customerRepository;

  public Optional<Customer> findByIdAndEmail(Long id) {
    return customerRepository.findById(id);
  }

//  public Optional<Customer> findValidCustomer(String email, String password) {
//    return customerRepository.findByEmail(email).stream()
//        .filter(customer -> customer.getPassword().equals(password) && customer.isVerify())
//        .findFirst();
//  }
}
