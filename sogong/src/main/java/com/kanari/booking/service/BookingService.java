package com.kanari.booking.service;

import com.kanari.booking.domain.BookingEntity;
import com.kanari.booking.dto.BookingDto;
import com.kanari.booking.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;

    @Transactional
    public Long saveBooking(BookingDto bookingDto) {
        return bookingRepository.save(bookingDto.toEntity()).getBookingId();
    }


    @Transactional
    public BookingDto getBook(Long id) {
        Optional<BookingEntity> bookWrapper = bookingRepository.findById(id);
        BookingEntity bookingEntity = bookWrapper.get();

        BookingDto bookingDto = BookingDto.builder()
                .bookingId(bookingEntity.getBookingId())
                .name(bookingEntity.getName())
                .email(bookingEntity.getEmail())
                .phone(bookingEntity.getPhone())
                .people(bookingEntity.getPeople())
                .bookDay(bookingEntity.getBookDay())
                .time(bookingEntity.getTime())
                .tableNum(bookingEntity.getTableNum())
                .arrive(bookingEntity.getArrive())
                .build();

        return bookingDto;
    }
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
    @Transactional
    public void cancelBooking(Long id) {
        bookingRepository.deleteById(id);
    }

    //????????? ????????? ????????? ??????????????? ????????? ?????? ????????????
    public boolean checkBookList(String bookDay, int tableNum, String time) {
        List<BookingEntity> alreadyBooking = bookingRepository.findAll();
        List<BookingDto> bookingDtoList = new ArrayList<>();

        for(BookingEntity bookingEntity : alreadyBooking) {
            if(bookDay.equals(bookingEntity.getBookDay()) && tableNum == bookingEntity.getTableNum() &&
            time.equals(bookingEntity.getTime()))
                return false;
        }
        return true;
    }

    public BookingDto findByBookingId (Long bookingId) {
        BookingEntity bookingentity = bookingRepository.findById(bookingId).orElseThrow( () -> new IllegalArgumentException("?????? ???????????? ????????????. id =" + bookingId));

        return  new BookingDto(bookingentity);
    }
}