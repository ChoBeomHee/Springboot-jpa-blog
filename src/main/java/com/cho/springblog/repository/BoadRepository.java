package com.cho.springblog.repository;

import com.cho.springblog.model.Board;
import com.cho.springblog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// Data access object
// 자동으로 bean에 등록
// @Repository 생략 가능
@Repository
public interface BoadRepository extends JpaRepository<Board, Integer> {
    // JPA 네이밍 쿼리 전략
    // 전통적 방식 User findByUsernameAndPassword(String username, String password);
    // 자동적으로 이름을 이렇게 하면 SELECT * FROM usesr WHERE username = ? AND password = ?; <- 이 쿼리가 실행
    // 아래도 가능
    // @Query(value = "SELECT * FROM user WHERE username = ?1 AND password = ?2", nativeQuery = true)
    // User login(String username, String password);
}
