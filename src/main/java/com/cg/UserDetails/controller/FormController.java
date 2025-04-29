package com.cg.UserDetails.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class FormController {
    private String getCookieValue(HttpServletRequest request, String name) {
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals(name)) {
                    return cookie.getValue();
                }
            }
        }
        return "";
    }


    @GetMapping("/")
    public String showPage1() {
        return "page1";
    }

    @PostMapping("/page1")
    public String processPage1(@RequestParam String firstName,
                               @RequestParam String lastName,
                               HttpServletResponse response) {
        response.addCookie(new Cookie("firstName", firstName));
        response.addCookie(new Cookie("lastName", lastName));
        return "redirect:/page2";
    }


    @GetMapping("/page2")
    public String showPage2(HttpServletRequest request, Model model) {
        model.addAttribute("firstName", getCookieValue(request, "firstName"));
        model.addAttribute("lastName", getCookieValue(request, "lastName"));
        return "page2";
    }

    @PostMapping("/page2")
    public String processPage2(@RequestParam String email,
                               @RequestParam String phone,
                               HttpServletResponse response) {
        response.addCookie(new Cookie("email", email));
        response.addCookie(new Cookie("phone", phone));
        return "redirect:/page3";
    }


    @GetMapping("/page3")
    public String showPage3(HttpServletRequest request, Model model) {
        model.addAttribute("firstName", getCookieValue(request, "firstName"));
        model.addAttribute("lastName", getCookieValue(request, "lastName"));
        model.addAttribute("email", getCookieValue(request, "email"));
        model.addAttribute("phone", getCookieValue(request, "phone"));
        return "page3";
    }

    @PostMapping("/page3")
    public String processPage3(@RequestParam String city,
                               @RequestParam String country,
                               HttpServletResponse response) {
        response.addCookie(new Cookie("city", city));
        response.addCookie(new Cookie("country", country));
        return "redirect:/page4";
    }


    @GetMapping("/page4")
    public String showSummary(HttpServletRequest request, Model model) {
        String[] fields = {"firstName", "lastName", "email", "phone", "city", "country"};
        for (String field : fields) {
            model.addAttribute(field, getCookieValue(request, field));
        }
        return "page4";
    }
}
