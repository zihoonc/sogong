package com.kanari.waiting.controller;

import com.kanari.waiting.dto.WaitingDto;
import com.kanari.waiting.service.repository.WaitingRepository;
import com.kanari.waiting.service.WaitingService;
import lombok.RequiredArgsConstructor;
import com.kanari.waiting.util.ScriptUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class ServiceController {

    private final WaitingService waitingService;
    private final WaitingRepository waitingRepository;
    private int count=1;


    @PostMapping("/waitAction")
    public String Waiting(WaitingDto waitingDto, HttpServletResponse response) throws IOException {
        waitingService.saveWaiting(waitingDto);
        ScriptUtils.alertAndMovePage(response,"대기 순위는 "+count+"번 입니다","/wait");
        count++;
        return "redirect:/";
    }

    @PostMapping("/cancelWaitiing/{waitingId}")
    public String cancelWaitiing(@PathVariable("bookingId") Long id) {
        waitingService.cancelWaiting(id);
        count--;
        return "redirect:/";
    }


}
