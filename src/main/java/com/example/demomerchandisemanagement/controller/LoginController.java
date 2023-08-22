package com.example.demomerchandisemanagement.controller;

import com.example.demomerchandisemanagement.form.LoginForm;
import com.example.demomerchandisemanagement.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private final LoginService service;

    @GetMapping("/login")
    public String view(Model model, LoginForm form) {
        return "login";
    }

    @PostMapping("/login")
    public String login(Model model, LoginForm form) {
        var userInfo = service.searchUserById(form.getLoginId());
        var isCorrentUserAuth = userInfo.isPresent()
                && form.getPassword().equals(userInfo.get().getPassword());
        if (isCorrentUserAuth) {
            return "redirect:/menu";
        } else {
            model.addAttribute("errorMsg", "ログインIDとパスワードの組み合わせが間違っています。");
            return "login";
        }
    }
}
