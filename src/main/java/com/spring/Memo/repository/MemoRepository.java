package com.spring.Memo.repository;

import com.spring.Memo.domain.Memo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemoRepository {

    private final EntityManager em;

    public void save(Memo memo) {
        em.persist(memo);
    }

    public Long findOne(Memo memo) {
        return memo.getId();
    }

    public List<Memo> findAll() {
        return em.createQuery("SELECT M FROM Memo AS M", Memo.class)
                .getResultList();
    }

    public List<Memo> findSearch(String keyWord) {
        return em.createQuery("SELECT M FROM Memo AS M WHERE M.content LIKE :keyWord", Memo.class)
                .setParameter("keyWord", "%" + keyWord + "%")
                .getResultList();
    }
}
