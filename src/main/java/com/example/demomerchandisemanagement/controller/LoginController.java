package com.example.demomerchandisemanagement.controller;

import com.example.demomerchandisemanagement.form.LoginForm;
import com.example.demomerchandisemanagement.service.LoginService;
import lombok.RequiredArgsConstructor;
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
     * 初期表示
     *
     * @param model モデル
     * @param form  入力情報
     * @return 表示画面
     */
    @GetMapping("/login")
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
    @PostMapping("/login")
    public String login(Model model, LoginForm form) {
        var userInfo = service.searchUserById(form.getLoginId());
        // TODO パスワードはハッシュ化したものを使用する
        var isCorrentUserAuth = userInfo.isPresent()
                && form.getPassword().equals(userInfo.get().getPassword());
        if (isCorrentUserAuth) {
            return "redirect:/menu";
        } else {
            // TODO エラーメッセージはプロバティファイルで管理する
            model.addAttribute("errorMsg", "ログインIDとパスワードの組み合わせが間違っています。");
            return "login";
        }
    }
}
