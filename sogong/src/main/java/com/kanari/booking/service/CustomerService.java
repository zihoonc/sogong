package com.kanari.booking.service;

import com.kanari.booking.dto.CustomerDto;
import com.kanari.booking.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Transactional
    public String saveCus(CustomerDto customerDto) {
        return customerRepository.save(customerDto.toEntity()).getName();
    }


}
