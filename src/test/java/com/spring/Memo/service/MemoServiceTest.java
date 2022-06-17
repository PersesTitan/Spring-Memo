package com.spring.Memo.service;

import com.spring.Memo.domain.Memo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemoServiceTest {

    @Autowired private MemoService memoService;

    @Test
    public void 메모생성() {
        //given
        Memo memo = Memo.createMemo("테스트 타이틀", "내용");

        //when
        Long id = memoService.save(memo);

        //then
        assertEquals(memo, memoService.findOne(id));
    }
}