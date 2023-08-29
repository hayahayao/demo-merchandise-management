package com.example.demomerchandisemanagement.form;

import jdk.jfr.Label;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * ユーザー登録画面 form
 */
@Data
public class SignupForm {

    /**
     * ログインID
     */
    @Length(min = 8, max = 20)
    private String loginId;

    /**
     * パスワード
     */
    @Length(min = 8, max = 20)
    private String password;

}
