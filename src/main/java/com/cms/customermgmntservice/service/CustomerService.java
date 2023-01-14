package com.cms.customermgmntservice.service;

import com.cms.customermgmntservice.dto.CustomerDto;

import java.util.List;

public interface CustomerService {

    List<CustomerDto> getCustomers();

    CustomerDto getCustomerById(Long id);

    CustomerDto addCustomer(CustomerDto customer);

    CustomerDto updateCustomer(CustomerDto customer);

    void deleteCustomerById(Long id);
}
