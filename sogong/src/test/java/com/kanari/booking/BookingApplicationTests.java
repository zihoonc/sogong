package com.kanari.booking;

import com.kanari.booking.domain.BookingEntity;
import com.kanari.booking.repository.BookingRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.HiddenHttpMethodFilter;

import java.util.Optional;

@SpringBootTest
class BookingApplicationTests {
    @Bean
    public HiddenHttpMethodFilter httpMethodFilter() {
        HiddenHttpMethodFilter hiddenHttpMethodFilter = new HiddenHttpMethodFilter();
        return hiddenHttpMethodFilter;
    }
    @Test
    void contextLoads() {
    }

}
