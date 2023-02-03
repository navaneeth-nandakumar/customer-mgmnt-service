package com.cms.customermgmntservice.controller;

import com.cms.customermgmntservice.dto.CustomerDTO;
import com.cms.customermgmntservice.logging.Loggable;
import com.cms.customermgmntservice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    @Loggable
    public List<CustomerDTO> getCustomers() {
        return customerService.getCustomers();
    }

    @GetMapping("/{id}")
    @Loggable
    public CustomerDTO getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }

    @PostMapping
    @Loggable
    public ResponseEntity<CustomerDTO> saveCustomer(@RequestBody CustomerDTO customerDto, UriComponentsBuilder ucb) {
        CustomerDTO customer = customerService.addCustomer(customerDto);
        URI location = ucb.path("/customers/")
                .path(String.valueOf(customer.getId()))
                .build().toUri();
        return ResponseEntity.created(location).body(customer);
    }

    @PutMapping("/{id}")
    @Loggable
    public CustomerDTO updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDto) {
        customerDto.setId(id);
        return customerService.updateCustomer(customerDto);
    }

    @DeleteMapping("/{id}")
    @Loggable
    public void deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomerById(id);
    }

}
