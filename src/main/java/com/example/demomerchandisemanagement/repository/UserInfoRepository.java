package com.example.demomerchandisemanagement.repository;

import com.example.demomerchandisemanagement.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, String> {

}
