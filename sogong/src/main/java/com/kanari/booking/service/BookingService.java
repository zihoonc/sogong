package com.kanari.booking.service;

import com.kanari.booking.domain.BookingEntity;
import com.kanari.booking.dto.BookingDto;
import com.kanari.booking.dto.BookingResponseDto;
import com.kanari.booking.dto.BookingUpdateRequestDto;
import com.kanari.booking.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class BookingService {
    @Autowired
    private final BookingRepository bookingRepository;

    @Transactional
    public Long saveBooking(BookingDto bookingDto) {
        return bookingRepository.save(bookingDto.toEntity()).getBookingId();
    }


    @Transactional
    public void cancelBooking(Long id) {
        bookingRepository.deleteById(id);
    }



    @Transactional
    public Long update(Long bookingId, BookingUpdateRequestDto requestDto){
        BookingEntity bookingEntity = bookingRepository.findById(bookingId).orElseThrow(() -> new IllegalArgumentException("해당 예약이 없습니다. id =" + bookingId));
        bookingEntity.update(requestDto.getArrive());
        return bookingId;
    }
    public BookingResponseDto findByBookingId (Long bookingId) {
        BookingEntity bookingentity = bookingRepository.findById(bookingId).orElseThrow( () -> new IllegalArgumentException("해당 게시글이 없습니다. id =" + bookingId));

        return  new BookingResponseDto(bookingentity);
    }
}
