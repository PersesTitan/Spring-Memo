package com.spring.Memo.service;

import com.spring.Memo.domain.Memo;
import com.spring.Memo.domain.MemoDTO;
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

    @Transactional
    public void update(Long id, String title, String content) {
        Memo memo = memoRepository.findOne(id);
        memoRepository.update(memo, title, content);
    }

    public Memo findOne(Long id) {
        return memoRepository.findOne(id);
    }

    public List<Memo> findAll() {
        return memoRepository.findAll();
    }

    @Transactional
    public void remove(Long id) {
        Memo memo = memoRepository.findOne(id);
        memoRepository.remove(memo);
    }

    //검색 로직
    public List<Memo> findSearch(String keyWord) {
        if (keyWord == null || keyWord.isEmpty()) return memoRepository.findAll();
        else return memoRepository.findSearch(keyWord);
    }
}
