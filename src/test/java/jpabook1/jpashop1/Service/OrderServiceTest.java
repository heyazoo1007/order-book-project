package jpabook1.jpashop1.Service;

import jpabook1.jpashop1.domain.Address;
import jpabook1.jpashop1.domain.Member;
import jpabook1.jpashop1.domain.Order.Order;
import jpabook1.jpashop1.domain.Order.OrderStatus;
import jpabook1.jpashop1.domain.item.Book;
import jpabook1.jpashop1.domain.item.Item;
import jpabook1.jpashop1.exception.NotEnoughStockException;
import jpabook1.jpashop1.repository.OrderRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.swing.text.html.parser.Entity;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderServiceTest {

    @Autowired
    EntityManager em;
    @Autowired OrderService orderService;
    @Autowired
    OrderRepository orderRepository;

    @Test
    public void 상품주문() throws Exception{

        Member member = createMember();
        Book book= createBook("책1",15000,3);


        int orderCount=2;

        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);
        Order getOrder =orderRepository.findOne(orderId);

        assertEquals(getOrder.getStatus(), OrderStatus.ORDER, "상품 주문시 상태는 ORDER");
        assertEquals(getOrder.getOrderItems().size(), 1, "주문한 상품 종류 수가 정확해야한");
        assertEquals(getOrder.getTotalPrice(), 10000*orderCount, "주문 가격은 가겨*수량이");
        assertEquals(book.getStockQuantity(), 2, "주문 수량만큼 재고가 줄어야한다 ");


    }


    @Test
    public void 주문취소() throws Exception{

        Member member = createMember();
        Item item= createBook("시골 JPA",10000,10);

        int orderCount=2;

        Long orderId = orderService.order(member.getId(), item.getId(), orderCount);

        //when
        orderService.cancelOrder(orderId);

        Order getOrder = orderRepository.findOne(orderId);
        assertEquals(OrderStatus.CANCEL,getOrder.getStatus(),"주문 취소시 상태는 cancel이");
        assertEquals(10,item.getStockQuantity(),"주문이 취소되면 취소한만큼 증가");

    }


    @Test(expected = NotEnoughStockException.class)
    public void 상품주문_재고수량초과() throws Exception{

        Member member = createMember();
        Item item= createBook("JPA",10000,10);
        int orderCount=11;

        orderService.order(member.getId(),item.getId(),orderCount);

        fail("재고 수량 부족 예외가 발생해야한다");

    }

    public Member createMember(){
        Member member = new Member();
        member.setName("kim");
        member.setAddress(new Address("soeoul","mangwon","16564"));
        em.persist(member);
        return member;
    }

    public Book createBook(String name, int price,int stockQuantity){
        Book book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);
        em.persist(book);
        return book;

    }
}
