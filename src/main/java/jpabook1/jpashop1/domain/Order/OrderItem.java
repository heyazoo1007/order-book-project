package jpabook1.jpashop1.domain.Order;

import jpabook1.jpashop1.domain.Order.Order;
import jpabook1.jpashop1.domain.item.Item;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Entity
public class OrderItem {

    @Id @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private int orderPrice;
    private int count;



    //생성 메서드//
    public static OrderItem createOrderItem(Item item, int orderPrice,int count){
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);

        item.reduceStock(count);
        return orderItem;
    }

    //비즈니스 로직
    public void cancel(){
        getItem().addStock(count);

    }

    //주문상품 전체 가격 조회
    public int getTotalPrice() {
        return getOrderPrice()*getCount();
    }
}
