package com.cho.springblog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity // 이 클래스는 엔티디 클래스다. 데이터베이스에 매핑해주는 클래스이다. 라는 뜻
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
    private int id;

    @Column(nullable = false, length = 200)
    private String content;

    // 누가? 어느 게시글의 답변인가!
    @ManyToOne // 여러개의 답변이 하나의 게시글에 달릴 수 있음
    @JoinColumn(name = "boadID") // 연관관계를 만들어줘야함
    private Board board;

    @ManyToOne // 하나의 유저는 여러 답글을 달 수 있음
    @JoinColumn(name = "userID")
    private User user;

    @CreationTimestamp
    private Timestamp creatData;
}
