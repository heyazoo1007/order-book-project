package jpabook1.jpashop1;

import jpabook1.jpashop1.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MemberRepository {

    @PersistenceContext
    EntityManager em;
//em을 통한 모든 데이터 변경은 트랜잭션 안에서 일어나야 한다

    public Long save(Member member){
        em.persist(member);
        return member.getId();
    }


    public Member find(Long id){
        return em.find(Member.class,id);

    }

}
