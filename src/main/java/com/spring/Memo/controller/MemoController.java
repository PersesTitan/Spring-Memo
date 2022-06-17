package com.spring.Memo.controller;

import com.spring.Memo.domain.Memo;
import com.spring.Memo.repository.MemoRepository;
import com.spring.Memo.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemoController {

    private final MemoRepository memoRepository;
    private final MemoService memoService;

//    public Memo createMemo(@RequestBody Memo memo) {
//        memoRepository.save(memo);
//    }

    public List<Memo> findSearch(String search) {
        return memoService.findSearch(search);
    }

}
