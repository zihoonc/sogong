package com.kanari.booking.controller;

import com.kanari.booking.domain.BookingEntity;
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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class    ServiceController {

    private final BookingService bookingService;
    private final BookingRepository bookingRepository;

    private final CustomerService customerService;
    private final CustomerRepository customerRepository;




    @PostMapping("/bookAction")
    public String booking(BookingDto bookingDto, HttpServletResponse response) {
        try {
            bookingService.saveBooking(bookingDto);
            ScriptUtils.alertAndMovePage(response,"예약이 완료되었습니다.", "/list");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/list";
    }
    @GetMapping("/bookview")
    public String bookView(HttpSession session, Model model){

        CustomerEntity customerEntity  = (CustomerEntity) session.getAttribute("cus");
        BookingEntity book = bookingRepository.findByName(customerEntity.getName());
        session.setAttribute("books", book);
        return "bookView";
    }
    @PostMapping("/cancleBooking/{bookingId}")
    public String cancelBooking(@PathVariable("bookingId") Long id) {
        bookingService.cancelBooking(id);
        return "redirect:/";
    }

    @PostMapping("/joinAction")
    public String join(CustomerDto customerDto, HttpServletResponse response) {
        try {
            customerService.saveCus(customerDto);
            ScriptUtils.alertAndMovePage(response, "회원가입 성공!", "/");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    @PostMapping("/login")
    public String login(@RequestParam(value = "name") String name, @RequestParam(value = "pwd") String pwd,
                        HttpServletRequest request, HttpServletResponse response, HttpSession session) {

        CustomerEntity cus = customerRepository.findByName(name);
        BookingEntity book = bookingRepository.findByName(name);

        try {
            if (cus == null || !cus.getPwd().equals(pwd)) {
                ScriptUtils.alertAndMovePage(response, "존재하지 않는 아이디이거나 비밀번호가 틀립니다.", "login");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            session.setAttribute("cus", cus);
            session.setAttribute("book", book);
            ScriptUtils.alertAndMovePage(response, "로그인 성공!", "/");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session, HttpServletResponse response) {
        try {
            session.removeAttribute("cus");
            session.removeAttribute("book");
            ScriptUtils.alertAndMovePage(response, "로그아웃 성공!", "/");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
    @GetMapping("/list")
    public String list(Model model) {
        List<BookingEntity> bookingEntities = bookingRepository.findAll();
        model.addAttribute("books", bookingEntities);
        return "list";
    }
    @PutMapping("/edit/{bookingId}")
    public String update(BookingDto bookingDto,HttpServletResponse response,Model model){
        try {
            bookingService.saveBooking(bookingDto);
            ScriptUtils.alertAndMovePage(response,"예약이 완료되었습니다.", "/list");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/";
    }

    @GetMapping("/edit/{bookingId}")
    public String edit(@PathVariable("bookingId") Long bookingId, Model model) {
        BookingDto dto = bookingService.getBook(bookingId);
        model.addAttribute("book", dto);
        return "list-update";
    }


}
