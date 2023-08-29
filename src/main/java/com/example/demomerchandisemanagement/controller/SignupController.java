package com.example.demomerchandisemanagement.controller;

import com.example.demomerchandisemanagement.constant.MessageConst;
import com.example.demomerchandisemanagement.constant.SignupMessage;
import com.example.demomerchandisemanagement.entity.UserInfo;
import com.example.demomerchandisemanagement.form.SignupForm;
import com.example.demomerchandisemanagement.service.SignupService;
import com.example.demomerchandisemanagement.util.AppUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

/**
 * 　ユーザー登録画面 Controller
 */
@Controller
@RequiredArgsConstructor
public class SignupController {

    /**
     * ログイン画面 service
     */
    private final SignupService service;

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
    @GetMapping("/signup")
    public String view(Model model, SignupForm form) {
        return "signup";
    }

    /**
     * ユーザー登録
     *
     * @param model    モデル
     * @param form     入力情報
     * @param bdResult 入力チェック結果
     * @return 表示画面
     */
    @PostMapping("/signup")
    public void signup(Model model, @Validated SignupForm form, BindingResult bdResult) {
        if (bdResult.hasErrors()) {
            editGuideMessage(model, MessageConst.FORM_ERROR, true);
            return;
        }

        var userInfoOpt = service.resistUserInfo(form);

        var signupMessage = judgeMessageKey(userInfoOpt);
        editGuideMessage(model, signupMessage.getMessageId(), signupMessage.isError());
    }

    /**
     * 画面に表示するガイドメッセージを設定する
     *
     * @param model       モデル
     * @param messssageId メッセージID
     * @param isError     エラー有無
     */
    private void editGuideMessage(Model model, String messssageId, boolean isError) {
        var message = AppUtil.getMessage(messageSource, messssageId);
        model.addAttribute("message", message);
        model.addAttribute("isError", isError);
    }

    /**
     * ユーザ情報登録の結果メッセージキーを判断する
     *
     * @param userInfoOpt ユーザ情報登録結果(登録済みだった場合はEmpty)
     * @return メッセージキー
     */
    private SignupMessage judgeMessageKey(Optional<UserInfo> userInfoOpt) {
        if (userInfoOpt.isEmpty()) {
            // IDが重複の場合
            return SignupMessage.EXISTED_LOGIN_ID;
        } else {
            return SignupMessage.SUCCEED;
        }
    }
}
