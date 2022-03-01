package jpabook1.jpashop1.repository;

import jpabook1.jpashop1.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Member member){
        em.persist(member);
    }
    public  Member findOne(Long id ){
        Member member = em.find(Member.class, id); //find(타입,pk)
        return member;
    }

    //리스트는 쿼리 작성해서 반환해야한다
    public List<Member> findAll(){
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public List<Member> findByName(String name){
        return em.createQuery("select m from Member m where m.name=:name",Member.class)
                .setParameter("name",name)
                .getResultList();

    }
}
