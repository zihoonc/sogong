package com.kanari.booking.controller;

import com.kanari.booking.domain.CustomerEntity;
import com.kanari.booking.dto.BookingDto;
import com.kanari.booking.dto.CustomerDto;
import com.kanari.booking.repository.BookingRepository;
import com.kanari.booking.repository.CustomerRepository;
import com.kanari.booking.service.BookingService;
import com.kanari.booking.service.CustomerService;
import lombok.RequiredArgsConstructor;
import com.kanari.booking.util.ScriptUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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

    @PostMapping("/cancleBooking/{bookingId}")
    public String cancelBooking(@PathVariable("bookingId") Long id) {
        bookingService.cancelBooking(id);
        return "redirect:/";
    }

    @PostMapping("/joinAction")
    public String join(CustomerDto customerDto) {
        customerService.saveCus(customerDto);
        return "redirect:/";
    }


    @PostMapping("/login")
    public String login(@RequestParam(value = "name") String name, @RequestParam(value = "pwd") String pwd,
                        HttpServletRequest request, HttpServletResponse response, HttpSession session) {

        CustomerEntity cus = customerRepository.findByName(name);

        try {
            if (cus == null || !cus.getPwd().equals(pwd)) {
                ScriptUtils.alertAndMovePage(response, "존재하지 않는 아이디이거나 비밀번호가 틀립니다.", "login");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            session.setAttribute("cus", cus);
            ScriptUtils.alertAndMovePage(response, "로그인 성공!", "/");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("session 값 :" + session);
        return "";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("cus");
        System.out.println("logout success");
        return "redirect:/";
    }
}
