package com.spring.Memo.repository;

import com.spring.Memo.domain.Memo;
import com.spring.Memo.domain.MemoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemoRepository {

    private final EntityManager em;

    //저장
    public void save(Memo memo) {
        em.persist(memo);
    }

    //삭제
    public void remove(Memo memo) {
        em.remove(memo);
    }

    //정보 업데이트
    public void update(Memo memo, MemoDTO memoDTO) {
        String title = memoDTO.title();
        String content = memoDTO.content();
        memo.setTitle(title);
        memo.setContent(content);
    }

    public Memo findOne(Long id) {
        return em.find(Memo.class, id);
    }

    public List<Memo> findAll() {
        return em.createQuery("SELECT M FROM Memo AS M", Memo.class)
                .getResultList();
    }

    //검색 로직
    public List<Memo> findSearch(String keyWord) {
        return em.createQuery("SELECT M FROM Memo AS M WHERE M.title LIKE :keyWord", Memo.class)
                .setParameter("keyWord", "%" + keyWord + "%")
                .getResultList();
    }
}
