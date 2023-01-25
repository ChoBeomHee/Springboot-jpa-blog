package com.cho.springblog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;


import javax.persistence.*;
import java.sql.Timestamp;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity // 유저 클래스가 자동으로 Mysql에 테이블이 생성된다.
//@DynamicInsert // insert 시 null 제외
public class User {
    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
    private int id;

    @Column(nullable = false, length = 30, unique = true)
    private String username;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, length = 120)
    private String email;

    // DB는 롤타입이라는 것이 없다.
    // @ColumnDefault("'user")
    @Enumerated(EnumType.STRING) // 이 열거 데이터는 STRING 이다.
    private RoleType role; // 열거를 쓰는 게 좋다.

    @CreationTimestamp // 시간 자동 입력
    private Timestamp createDate;
}
