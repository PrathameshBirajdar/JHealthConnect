package com.jhealthconnect.controller;

import com.jhealthconnect.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model, HttpSession session) {
        Object status = request.getAttribute("jakarta.servlet.error.status_code");
        Object errorMessage = request.getAttribute("jakarta.servlet.error.message");
        String requestUri = (String) request.getAttribute("jakarta.servlet.error.request_uri");

        User user = (User) session.getAttribute("user");
        if (user != null) {
            model.addAttribute("user", user);
        }

        model.addAttribute("uri", requestUri);

        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());

            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                return "error/404";
            } else if (statusCode == HttpStatus.UNAUTHORIZED.value() ||
                    statusCode == HttpStatus.FORBIDDEN.value()) {
                return "error/401";
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                model.addAttribute("message", errorMessage != null ? errorMessage : "Internal Server Error");
                return "error/500";
            }
        }

        model.addAttribute("message", errorMessage != null ? errorMessage : "An unexpected error occurred");
        return "error/general";
    }
}