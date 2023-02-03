package com.cms.customermgmntservice.service.impl;

import com.cms.customermgmntservice.dto.CustomerDTO;
import com.cms.customermgmntservice.dto.CustomerDTOMapper;
import com.cms.customermgmntservice.entity.Customer;
import com.cms.customermgmntservice.entity.CustomerEntityMapper;
import com.cms.customermgmntservice.exception.ResourceNotFoundException;
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

    @Autowired
    private CustomerDTOMapper customerDTOMapper;

    @Autowired
    private CustomerEntityMapper customerEntityMapper;

    @Override
    @Loggable
    public List<CustomerDTO> getCustomers() {
        return customerRepository.findAll().stream().map(customerDTOMapper).collect(Collectors.toList());
    }

    @Override
    @Loggable
    public CustomerDTO getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer Not Found"));
        return customerDTOMapper.apply(customer);
    }

    @Override
    @Loggable
    public CustomerDTO addCustomer(CustomerDTO customer) {
        Customer savedCustomer = customerRepository.save(customerEntityMapper.apply(customer));
        return customerDTOMapper.apply(savedCustomer);
    }

    @Override
    @Loggable
    public CustomerDTO updateCustomer(CustomerDTO customerDto) {
        Customer customer = customerRepository.findById(customerDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer Not Found"));
        return addCustomer(customerDto);
    }

    @Override
    @Loggable
    public void deleteCustomerById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer Not Found"));
        customerRepository.delete(customer);
    }

}
