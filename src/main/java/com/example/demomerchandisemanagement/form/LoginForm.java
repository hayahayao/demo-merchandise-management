package com.example.demomerchandisemanagement.form;

import lombok.Data;

/**
 * ユーザ画面 form
 */
@Data
public class LoginForm {
    
    /**
     * ログインID
     */
    private String loginId;

    /**
     * パスワード
     */
    private String password;
}
