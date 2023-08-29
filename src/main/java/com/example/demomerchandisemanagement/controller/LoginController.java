package com.example.demomerchandisemanagement.controller;

import com.example.demomerchandisemanagement.constant.MessageConst;
import com.example.demomerchandisemanagement.constant.UrlConst;
import com.example.demomerchandisemanagement.form.LoginForm;
import com.example.demomerchandisemanagement.service.LoginService;
import com.example.demomerchandisemanagement.util.AppUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 　ログイン画面 Controller
 */
@Controller
@RequiredArgsConstructor
public class LoginController {

    /**
     * ログイン画面 service
     */
    private final LoginService service;

    /**
     * PasswordEncoder
     */
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /**
     * メッセージソース
     */
    private final MessageSource messageSource;

    /**
     * 初期表示
     *
     * @param model モデル
     * @param form  入力情報
     * @return 表示画面
     */
    @GetMapping(UrlConst.LOGIN)
    public String view(Model model, LoginForm form) {
        return "login";
    }

    /**
     * ログイン
     *
     * @param model モデル
     * @param form  入力情報
     * @return 表示画面
     */
    @PostMapping(UrlConst.LOGIN)
    public String login(Model model, LoginForm form) {
        var userInfo = service.searchUserById(form.getLoginId());
        // パスワードはハッシュ化
        var encodePassword = passwordEncoder.encode(form.getPassword());
        var isCorrentUserAuth = userInfo.isPresent()
                && passwordEncoder.matches(form.getPassword(), userInfo.get().getPassword());
        if (isCorrentUserAuth) {
            return "redirect:/menu";
        } else {
            // エラーメッセージはプロバティファイルで管理する
            var errorMsg = AppUtil.getMessage(messageSource, MessageConst.LOGIN_WRONG_INPUT);
            model.addAttribute("errorMsg", errorMsg);
            return "login";
        }
    }
}
