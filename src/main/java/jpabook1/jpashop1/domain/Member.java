package jpabook1.jpashop1.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Member {

    @Id @GeneratedValue
    @Column(name ="member_id")
    private Long id;
    private String name;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member") //order테이블에 있는 member 필드에 의해 매핑된 것을 알림
    private List<Order> orders = new ArrayList<>();

}
