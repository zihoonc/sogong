package com.kanari.booking.controller;

import com.kanari.booking.domain.CustomerEntity;
import com.kanari.booking.dto.BookingDto;
import com.kanari.booking.dto.CustomerDto;
import com.kanari.booking.repository.BookingRepository;
import com.kanari.booking.repository.CustomerRepository;
import com.kanari.booking.service.BookingService;
import com.kanari.booking.service.CustomerService;
import com.kanari.booking.util.ScriptUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ServiceController {

    private final BookingService bookingService;
    private final BookingRepository bookingRepository;

    private final CustomerService customerService;
    private final CustomerRepository customerRepository;

    @PostMapping("/bookAction")
    public String booking(BookingDto bookingDto) {
        bookingService.saveBooking(bookingDto);
        return "redirect:/";
    }

    @PostMapping("/cancelBooking/{bookingId}")
    public String cancelBooking(@PathVariable("bookingId") Long id) {
        bookingService.cancelBooking(id);
        return "redirect:/";
    }

    @PostMapping("/joinAction")
    public String join(CustomerDto customerDto, HttpServletResponse response) {
        customerService.saveCus(customerDto);
        return "redirect:/";
    }


    @PostMapping("/login")
    public String login(@RequestParam(value = "name") String name, @RequestParam(value = "pwd") String pwd,
                        HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        //System.out.println("받아온 값 :" + pwd);
        //System.out.println(customerRepository.findByName(name).getPwd());
        try {
            if (!customerRepository.findByName(name).getPwd().equals(pwd)) {
                ScriptUtils.alertAndMovePage(response, "로그인 실패!", "login");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            session = request.getSession();
            ScriptUtils.alertAndMovePage(response, "로그인 성공!", "/");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
