package jpabook1.jpashop1.domain.Order;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderSearch {

    private String memberName; //회원 이름으로 검색
    private OrderStatus orderStatus; // 주문상태(CANCEL,ORDER)


}
