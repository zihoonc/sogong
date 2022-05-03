package com.kanari.booking.repository;

import com.kanari.booking.domain.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
    CustomerEntity findByName(String name);
}
