package com.cho.springblog.test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Member {
    private int id;
    private String username;
    private String password;
    private String email;



    @Builder    // ex , id는 넣고싶지않을 경우 원래는 오버로딩을 하나 해야함, 빌더 패턴을 사용하면 그러지않아도됨
    public Member(int id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
