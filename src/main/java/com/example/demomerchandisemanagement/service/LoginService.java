package com.example.demomerchandisemanagement.service;

import com.example.demomerchandisemanagement.entity.UserInfo;
import com.example.demomerchandisemanagement.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final UserInfoRepository repository;

    public Optional<UserInfo> searchUserById(String loginId) {
        return repository.findById(loginId);
    }
}
