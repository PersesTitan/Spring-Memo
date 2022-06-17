package com.spring.Memo.service;

import com.spring.Memo.domain.Memo;
import com.spring.Memo.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemoService {

    private final MemoRepository memoRepository;

    @Transactional
    public Long save(Memo memo) {
        overlapCheck(memo);
        memoRepository.save(memo);
        return memo.getId();
    }

    //중복체크
    private void overlapCheck(Memo memo) {

    }
}
