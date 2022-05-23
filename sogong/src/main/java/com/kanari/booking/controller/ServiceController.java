package com.kanari.booking.controller;

import com.kanari.booking.domain.BookingEntity;
import com.kanari.booking.domain.CustomerEntity;
import com.kanari.booking.domain.WaitingEntity;
import com.kanari.booking.dto.BookingDto;
import com.kanari.booking.dto.CustomerDto;
import com.kanari.booking.repository.BookingRepository;
import com.kanari.booking.repository.CustomerRepository;
import com.kanari.booking.repository.WaitingRepository;
import com.kanari.booking.service.BookingService;
import com.kanari.booking.service.CustomerService;
import com.kanari.booking.service.WaitingService;
import lombok.RequiredArgsConstructor;
import com.kanari.booking.util.ScriptUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    private final WaitingRepository waitingRepository;
    private Long id;

    @GetMapping("/bookModify")
    public String bookModify(Model model) {
        List<BookingEntity> bookingEntities = bookingRepository.findAll();
        model.addAttribute("book", bookingEntities);
        return "bookModify";
    }

    @PostMapping("/bookModify")
    public String postBookModify(BookingDto bookingDto, HttpServletResponse response) {
        try {
            List<BookingEntity> bookingEntities = bookingRepository.findAll();
            id = bookingEntities.get(0).getBookingId();
            bookingService.cancelBooking(id);
            bookingService.saveBooking(bookingDto);
            ScriptUtils.alertAndMovePage(response, "예약이 수정되었습니다.", "/");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/";
    }

    @PostMapping("/bookDelete")
    public String bookDelete(HttpServletResponse response) {
        try {
            List<BookingEntity> bookingEntities = bookingRepository.findAll();
            id = bookingEntities.get(0).getBookingId();
            bookingService.cancelBooking(id);
            ScriptUtils.alertAndMovePage(response, "예약이 삭제되었습니다.", "/");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/";
    }

    @PostMapping("/bookAction")
    public String booking(BookingDto bookingDto, HttpServletResponse response) {
        try {
            Exception e = new Exception("중복된 예약");
            if (bookingService.checkBookList(bookingDto.getBookDay(), bookingDto.getTableNum(), bookingDto.getTime()) == false) {
                ScriptUtils.alertAndMovePage(response, "중복된 예약입니다.", "/book");
                throw e;
            }
            bookingService.saveBooking(bookingDto);
            ScriptUtils.alertAndMovePage(response, "예약이 완료되었습니다.", "/");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    @GetMapping("/bookView")
    public String bookView(HttpSession session, Model model) {
        CustomerEntity customerEntity = (CustomerEntity) session.getAttribute("cus");
        BookingEntity book = bookingRepository.findByName(customerEntity.getName());
        WaitingEntity wait = waitingRepository.findByName(customerEntity.getName());
        session.setAttribute("waits",wait);
        session.setAttribute("books", book);
        return "bookView";
    }

    @PostMapping("/cancelBooking/{bookingId}")
    public String cancelBooking(@PathVariable("bookingId") Long id) {
        bookingService.cancelBooking(id);
        return "redirect:/adminPage";
    }

    @PostMapping("/joinAction")
    public String join(CustomerDto customerDto, HttpServletResponse response) {
        try {
            //ID 중복 검색
            if (customerRepository.findByName(customerDto.getName()) != null)
                ScriptUtils.alertAndMovePage(response, "중복된 아이디 입니다.", "/join");
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
            if (cus.getRole().equals("cus"))
                session.setAttribute("role", false);
            else
                session.setAttribute("role", true);
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
            session.removeAttribute("role");
            ScriptUtils.alertAndMovePage(response, "로그아웃 성공!", "/");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    @PostMapping("/edit/{bookingId}")
    public String edit(@PathVariable("bookingId") Long id, @RequestParam("arrive") String arrive, Model model) {
        BookingDto bookingDto = bookingService.getBook(id);
        bookingDto.setArrive(arrive);   //arrive 값입력
        bookingService.saveBooking(bookingDto); //bookingDto 저장
        return "redirect:/adminPage";
    }

    @GetMapping("/adminPage")
    public String admin(Model model) {
        List<BookingEntity> bookingDtoList = bookingRepository.findAll();
        List<WaitingEntity> waitingEntities = waitingRepository.findAll();
        System.out.println(bookingDtoList);
        model.addAttribute("bookingList", bookingDtoList);
        model.addAttribute("wait", waitingEntities);
        return "adminPage";
    }
}
