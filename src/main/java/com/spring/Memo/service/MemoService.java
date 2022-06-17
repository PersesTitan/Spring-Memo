package com.spring.Memo.service;

import com.spring.Memo.domain.Memo;
import com.spring.Memo.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemoService {

    private final MemoRepository memoRepository;

    @Transactional
    public Long save(Memo memo) {
        memoRepository.save(memo);
        return memo.getId();
    }

    public Memo findOne(Long id) {
        return memoRepository.findOne(id);
    }

    public List<Memo> findAll() {
        return memoRepository.findAll();
    }

    //검색 로직
    public List<Memo> findSearch(String keyWord) {
        if (keyWord.isEmpty()) return memoRepository.findAll();
        else return memoRepository.findSearch(keyWord);
    }
}
