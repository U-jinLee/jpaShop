package jpaBook.jpaShop.service;

import jpaBook.jpaShop.domain.delivery.Delivery;
import jpaBook.jpaShop.domain.item.Item;
import jpaBook.jpaShop.domain.item.ItemRepository;
import jpaBook.jpaShop.domain.member.Member;
import jpaBook.jpaShop.domain.member.MemberRepository;
import jpaBook.jpaShop.domain.order.Order;
import jpaBook.jpaShop.domain.order.OrderRepository;
import jpaBook.jpaShop.domain.orderItem.OrderItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;
    //주문
    @Transactional
    public Long order(Long memberId, Long itemId, int count) {
        //엔티티 조회
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        //배송정보 생성
       Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(),count);

        Order order = Order.createOrder(member, delivery, orderItem);

        orderRepository.save(order);

        return order.getId();
    }
    //취소
    //검색
}
