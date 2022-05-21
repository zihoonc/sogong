package com.kanari.booking.service;

import com.kanari.booking.domain.BookingEntity;
import com.kanari.booking.dto.BookingDto;
import com.kanari.booking.dto.BookingUpdateDto;
import com.kanari.booking.dto.PostResponseDto;
import com.kanari.booking.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

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
    @Transactional
    public Long update(Long id, BookingUpdateDto requestDto) {
        BookingEntity entity = bookingRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id)
        );

       entity.update(requestDto.getArrive());

        return id;
    }
    @Transactional
    public List<BookingDto> getBookList() {
        List<BookingEntity> bookingEntities = bookingRepository.findAll();
        List<BookingDto> bookingDtoList = new ArrayList<>();

        for(BookingEntity bookingEntity : bookingEntities) {
            BookingDto bookingDto = BookingDto.builder()
                    .bookingId(bookingEntity.getBookingId())
                    .name(bookingEntity.getName())
                    .email(bookingEntity.getEmail())
                    .phone(bookingEntity.getPhone())
                    .people(bookingEntity.getPeople())
                    .bookDay(bookingEntity.getBookDay())
                    .time(bookingEntity.getTime())
                    .tableNum(bookingEntity.getTableNum())
                    .build();
            bookingDtoList.add(bookingDto);
        }


        return bookingDtoList;
    }
    public PostResponseDto findById(Long id) {
        BookingEntity entity = bookingRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id)
        );

        return new PostResponseDto(entity);
    }

    public BookingDto findByBookingId (Long bookingId) {
        BookingEntity bookingentity = bookingRepository.findById(bookingId).orElseThrow( () -> new IllegalArgumentException("해당 게시글이 없습니다. id =" + bookingId));

        return  new BookingDto(bookingentity);
    }
}