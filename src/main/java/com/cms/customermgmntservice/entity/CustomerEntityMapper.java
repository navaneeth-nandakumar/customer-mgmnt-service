package com.cms.customermgmntservice.entity;

import com.cms.customermgmntservice.dto.CustomerDTO;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class CustomerEntityMapper implements Function<CustomerDTO, Customer> {

    @Override
    public Customer apply(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setId(customerDTO.getId());
        customer.setAddress(customerDTO.getAddress());
        customer.setName(customerDTO.getName());
        customer.setEmail(customerDTO.getEmail());
        customer.setPhoneNumber(customerDTO.getPhoneNumber());
        return customer;
    }
}
