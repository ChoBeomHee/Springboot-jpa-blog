package com.cho.springblog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 100)
    private String title;

    @Lob // 대용량 데이터 사용시
    private String content; // 섬머노트 라이브러리 사용 html 태그가 섞임

    private int count; // 조회수

    @ManyToOne(fetch = FetchType.EAGER) // many = board , user = one 한명의 유저는 여러가지 게시글을 쓴다
    @JoinColumn(name="userId") // 실제 데이터베이스에는 이 이름으로 만들어진다.
    private User user; // DB는 오브젝트를 저장할 수 없다.

    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER) // mappby : 연관관계의 주인이 아니다. (난 FK가 아니다) DB에 컬럼을 만들지마라.
    // 조인이 필요가 없음 why?? mysql 페이지에서 포링키가 필요없기 때문임.
    private List<Reply> reply;

    @CreationTimestamp
    private Timestamp createDate;
}