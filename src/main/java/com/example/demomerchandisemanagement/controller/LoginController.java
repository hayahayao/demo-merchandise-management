package com.example.demomerchandisemanagement.controller;

import com.example.demomerchandisemanagement.form.LoginForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
    // 仮の定数
    private static final String LOGIN_ID = "user";
    private static final String PASSWORD = "pwd";

    @GetMapping("/login")
    public String view(Model model, LoginForm form) {
        return "login";
    }

    @PostMapping("/login")
    public String login(Model model, LoginForm form) {
        var isCorrentUserAuth = form.getLoginId().equals(LOGIN_ID) && form.getPassword().equals(PASSWORD);
        if (isCorrentUserAuth) {
            return "redirect:/menu";
        } else {
            model.addAttribute("errorMsg", "ログインIDとパスワードの組み合わせが間違っています。");
            return "login";
        }
    }
}
