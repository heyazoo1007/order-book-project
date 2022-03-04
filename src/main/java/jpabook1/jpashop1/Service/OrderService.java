package jpabook1.jpashop1.Service;

import jpabook1.jpashop1.domain.Delivery;
import jpabook1.jpashop1.domain.Member;
import jpabook1.jpashop1.domain.Order.Order;
import jpabook1.jpashop1.domain.Order.OrderItem;
import jpabook1.jpashop1.domain.Order.OrderSearch;
import jpabook1.jpashop1.domain.item.Item;
import jpabook1.jpashop1.repository.ItemRepository;
import jpabook1.jpashop1.repository.MemberRepository;
import jpabook1.jpashop1.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;


    @Transactional
    public Long order(Long memberId, Long itemId, int count){

        //엔티티 조회
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        //배송정보 생성
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

        //주문상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item,item.getPrice(),count);

        //주문 생성
        Order order = Order.createOrder(member, delivery, orderItem);

        orderRepository.save(order); //cascade때문에 order만 save해도 됨

        return order.getId();


    }

    //취소

    @Transactional
    public void cancelOrder(Long orderId){
        //주문 엔티티 조
        Order order = orderRepository.findOne(orderId);
        //주문 취소
        order.cancel();
    }

    //검색
    public List<Order> findOrders(OrderSearch orderSearch){
        return orderRepository.findAllByString(orderSearch);

    }

}
