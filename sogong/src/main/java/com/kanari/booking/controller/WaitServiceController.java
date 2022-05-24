package com.kanari.booking.controller;

import com.kanari.booking.domain.WaitingEntity;
import com.kanari.booking.dto.WaitingDto;
import com.kanari.booking.repository.WaitingRepository;
import com.kanari.booking.service.WaitingService;
import lombok.RequiredArgsConstructor;
import com.kanari.booking.util.ScriptUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class WaitServiceController {

    private final WaitingService waitingService;
    private final WaitingRepository waitingRepository;


    @PostMapping({"/waitAction"})
    public String Waiting(WaitingDto waitingDto, HttpServletResponse response) throws IOException {
        Long count = waitingService.saveWaiting(waitingDto);
        ScriptUtils.alertAndMovePage(response, "대기 순위는 " + count + "번 입니다", "/");
        return "/";
    }
    @PostMapping({"/cancelWaiting/{waitingId}"})
    public String cancelWaiting(@PathVariable("waitingId") Long id) {
        this.waitingService.cancelWaiting(id);
        return "redirect:/adminPage";
    }



    
}
