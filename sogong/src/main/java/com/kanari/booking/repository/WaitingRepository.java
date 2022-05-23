package com.kanari.booking.repository;

import com.kanari.booking.domain.BookingEntity;
import com.kanari.booking.domain.WaitingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WaitingRepository extends JpaRepository<WaitingEntity, Long> {
    WaitingEntity findByName(String name);
}
