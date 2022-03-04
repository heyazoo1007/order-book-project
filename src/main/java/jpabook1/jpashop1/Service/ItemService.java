package jpabook1.jpashop1.Service;


import jpabook1.jpashop1.domain.item.Book;
import jpabook1.jpashop1.domain.item.Item;
import jpabook1.jpashop1.repository.ItemRepository;
import jpabook1.jpashop1.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item){
        itemRepository.save(item);
    }
    public List<Item> findItem(Item item){
        return itemRepository.findAll();
    }


    @Transactional
    public void update(Long itemId, String name, int price, int stockQuantity){
        Item item = itemRepository.findOne(itemId);
        item.setStockQuantity(stockQuantity);
        item.setPrice(price);
        item.setName(name);
    }

    public Item findOne(Long itemId){
        return itemRepository.findOne(itemId);
    }

    public List<Item> findAll(){
        return itemRepository.findAll();
    }
}
