package com.cms.customermgmntservice.service;

import com.cms.customermgmntservice.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {

    List<CustomerDTO> getCustomers();

    CustomerDTO getCustomerById(Long id);

    CustomerDTO addCustomer(CustomerDTO customer);

    CustomerDTO updateCustomer(CustomerDTO customer);

    void deleteCustomerById(Long id);
}
