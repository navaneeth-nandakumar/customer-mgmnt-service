package com.cms.customermgmntservice.repository;

import com.cms.customermgmntservice.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
