package com.kanari.booking.service;

import com.kanari.booking.dto.BookingDto;
import com.kanari.booking.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;

    @Transactional
    public Long saveBooking(BookingDto bookingDto) {
        return bookingRepository.save(bookingDto.toEntity()).getBookingId();
    }

    @Transactional
    public void cancelBooking(Long id) {
        bookingRepository.deleteById(id);
    }
}
