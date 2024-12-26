package com.example.demo.user;

import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, String> {
    // 계정 삭제시, 아이디에 해당하는 유저 삭제
    void deleteById(String id);
}
