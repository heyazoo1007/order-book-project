package jpabook1.jpashop1.repository;

import jpabook1.jpashop1.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public void save(Item item){
        if (item.getId()==null){
            em.persist(item);//해당 아이템이 없을 때 새 아이템 등록
        }else{
            em.merge(item);//원래 있던 거에 머지
        }
    }

    public Item findOne(Long id){
        return em.find(Item.class,id);
    }

    public List<Item> findAll(){
        return em.createQuery("select i from Item i",Item.class)
                .getResultList();
    }

}
