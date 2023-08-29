package com.example.demomerchandisemanagement.authentication;

import com.example.demomerchandisemanagement.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


/**
 * ユーザー情報生成
 */
@Component
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    /**
     * ユーザー情報テーブルRepository
     */
    private final UserInfoRepository repository;

    /**
     * ユーザー情報生成
     *
     * @param username ログインID
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var userInfo = repository.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        return User.withUsername(userInfo.getLoginId())
                .password(userInfo.getPassword())
                .roles("USER")
                .build();
    }
}
