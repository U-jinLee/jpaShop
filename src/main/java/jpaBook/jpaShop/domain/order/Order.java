package jpaBook.jpaShop.domain.order;

import jpaBook.jpaShop.domain.OrderStatus;
import jpaBook.jpaShop.domain.delivery.Delivery;
import jpaBook.jpaShop.domain.member.Member;
import jpaBook.jpaShop.domain.orderItem.OrderItem;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="orders")
@Getter @Setter
public class Order {
    @Id @GeneratedValue
    @Column(name="order_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="member_id")
    private Member member;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    private Delivery delivery;

    private LocalDateTime orderDate;

    private OrderStatus status;

}
