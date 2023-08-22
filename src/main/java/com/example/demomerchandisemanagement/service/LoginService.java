package com.example.demomerchandisemanagement.service;

import com.example.demomerchandisemanagement.entity.UserInfo;
import com.example.demomerchandisemanagement.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * ログイン画面 Service
 */
@Service
@RequiredArgsConstructor
public class LoginService {

    /**
     * ユーザ情報テーブルDAO
     */
    private final UserInfoRepository repository;

    /**
     * ユーザ情報テーブル　主キー検索
     *
     * @param loginId ログインID
     * @return ユーザ情報テーブルを主キー検索した結果（1件）
     */
    public Optional<UserInfo> searchUserById(String loginId) {
        return repository.findById(loginId);
    }
}
