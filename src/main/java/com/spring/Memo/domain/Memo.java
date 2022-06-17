package com.spring.Memo.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Memo {

    @Id @GeneratedValue
    @Column(name = "memo_id")
    private Long id;

    @NotNull private String title;
    @NotNull private String content;

    @NotNull
    private final LocalDateTime createDate = LocalDateTime.now();

    private Memo(String title, String content) {
        this.title = title;
        this.content = content;
    }

    //생성 로직
    public static Memo createMemo(String title, String content) {
        return new Memo(title, content);
    }
}
