package com.example.demomerchandisemanagement.service;

import com.example.demomerchandisemanagement.entity.UserInfo;
import com.example.demomerchandisemanagement.form.SignupForm;
import com.example.demomerchandisemanagement.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * ユーザー登録画面 Service
 */
@Service
@RequiredArgsConstructor
public class SignupService {

    /**
     * ユーザ情報テーブルDAO
     */
    private final UserInfoRepository repository;

    /**
     * PasswordEncoder
     */
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /**
     * ユーザ情報テーブル　主キー検索
     *
     * @param form 入力情報
     * @return 登録情報(ユーザー情報Entity)、すでに同じユーザIDで登録がある場合はEmpty
     */
    public Optional<UserInfo> resistUserInfo(SignupForm form) {
        var userInfoExistedOpt = repository.findById(form.getLoginId());
        if (userInfoExistedOpt.isPresent()) {
            return Optional.empty();
        }

        var userInfo = new UserInfo();
        userInfo.setLoginId(form.getLoginId());
        var encodedPassword = passwordEncoder.encode(form.getPassword());
        userInfo.setPassword(encodedPassword);

        return Optional.of(repository.save(userInfo));
    }
}
