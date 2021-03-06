package com.kanari.booking.repository;

import com.kanari.booking.domain.BookingEntity;
import com.kanari.booking.dto.BookingDto;
import com.kanari.booking.dto.BookingUpdateDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<BookingEntity, Long> {
    BookingEntity findByName(String name);

    BookingDto findByBookingId(Long bookingId);
}
