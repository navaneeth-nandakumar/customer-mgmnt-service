package com.cms.customermgmntservice.service.impl;

import com.cms.customermgmntservice.dto.CustomerDto;
import com.cms.customermgmntservice.entity.Customer;
import com.cms.customermgmntservice.logging.Loggable;
import com.cms.customermgmntservice.repository.CustomerRepository;
import com.cms.customermgmntservice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    @Loggable
    public List<CustomerDto> getCustomers() {
        return customerRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    @Loggable
    public CustomerDto getCustomerById(Long id) {
        return customerRepository.findById(id).map(this::convertToDto).orElse(null);
    }

    @Override
    @Loggable
    public CustomerDto addCustomer(CustomerDto customer) {
        return convertToDto(customerRepository.save(convertToEntity(customer)));
    }

    @Override
    @Loggable
    public CustomerDto updateCustomer(CustomerDto customerDto) {
        return addCustomer(customerDto);
    }

    @Override
    @Loggable
    public void deleteCustomerById(Long id) {
        customerRepository.deleteById(id);
    }

    private CustomerDto convertToDto(Customer customer) {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(customer.getId());
        customerDto.setAddress(customer.getAddress());
        customerDto.setName(customer.getName());
        customerDto.setEmail(customer.getEmail());
        customerDto.setPhoneNumber(customer.getPhoneNumber());
        return customerDto;
    }

    private Customer convertToEntity(CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setId(customerDto.getId());
        customer.setAddress(customerDto.getAddress());
        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        customer.setPhoneNumber(customerDto.getPhoneNumber());
        return customer;
    }
}
