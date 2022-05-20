package com.kanari.booking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    @GetMapping("/book")
    public String book() {
        return "book";
    }
    @GetMapping("/")
    public String index() {
        return "index";
    }
    @GetMapping("/about")
    public String about() {
        return "about";
    }
    @GetMapping("/bookview")
    public String bookView() {
        return "bookView";
    }
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @GetMapping("/menu")
    public String menu() {
        return "menu";
    }
    @GetMapping("/join")
    public String join() {
        return "join";
    }

}
