package com.kanari.booking.service;

import com.kanari.booking.dto.WaitingDto;
import com.kanari.booking.repository.WaitingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class WaitingService {

    private final WaitingRepository waitingRepository;

    @Transactional
    public Long saveWaiting(WaitingDto waitingDto) {
        return waitingRepository.save(waitingDto.toEntity()).getWaitingId();
    }

    @Transactional
    public void cancelWaiting(Long id) {
        waitingRepository.deleteById(id);
    }
}
