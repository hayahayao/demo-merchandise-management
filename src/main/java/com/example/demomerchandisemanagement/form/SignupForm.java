package com.example.demomerchandisemanagement.form;

import lombok.Data;

/**
 * ユーザー登録画面 form
 */
@Data
public class SignupForm {

    /**
     * ログインID
     */
    private String loginId;

    /**
     * パスワード
     */
    private String password;

}
