package jpaBook.jpaShop.service;

import jpaBook.jpaShop.domain.OrderStatus;
import jpaBook.jpaShop.domain.address.Address;
import jpaBook.jpaShop.domain.item.Book;
import jpaBook.jpaShop.domain.item.Item;
import jpaBook.jpaShop.domain.member.Member;
import jpaBook.jpaShop.domain.order.Order;
import jpaBook.jpaShop.domain.order.OrderRepository;
import jpaBook.jpaShop.exception.NotEnoughStockException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderServiceTest {
    @Autowired
    EntityManager entityManager;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;

    @Test
    public void 상품주문()throws Exception{
        //given
        Member member = getMember();

        Book book = getBook("시골 JPA", 10000, 10);

        int orderCount = 2;
        //when
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);
        //then
        Order getOrder = orderRepository.findOne(orderId);

        assertEquals("상품 주문 시 상태는 ORDER", OrderStatus.ORDER, getOrder.getStatus());
        assertEquals("주문한 상품 수",1, getOrder.getOrderItems().size());
        assertEquals("주문 가격은 가격 * 수량", 10000 * orderCount, getOrder.getTotalPrice());
        assertEquals("주문 수량의 재고가 주는가?",8,book.getStockQuantity());
    }

    @Test(expected = NotEnoughStockException.class)
    public void 상품주문_재고수량초과() throws Exception {
        //given
        Member member = getMember();
        Item book = getBook("시골 JPA", 10000, 10);
        int orderCount = 11;
        //when
        orderService.order(member.getId(), book.getId(), orderCount);
        //then
        fail("재고 수량 부족 예외가 발생해야 함");
    }

    @Test
    public void 주문취소() throws Exception{
        //given
        Member member = getMember();
        Book item = getBook("시골 JPA", 10000, 10);

        int orderCount = 2;

        Long orderId = orderService.order(member.getId(), item.getId(), orderCount);
        //when
        orderService.cancelOrder(orderId);
        //then
        Order getOrder = orderRepository.findOne(orderId);
        assertEquals("주문 취소 시 상태가 Cancel인가?",OrderStatus.CANCEL, getOrder.getStatus());
        assertEquals("주문 취소 시 재고는 원복되어야 함",10, item.getStockQuantity());
    }


    private Book getBook(String name, int price, int stockQuantity) {
        Book book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);
        entityManager.persist(book);
        return book;
    }

    private Member getMember() {
        Member member = new Member();
        Item item = new Book();
        member.setAddress(new Address("서울","강남","127-1"));
        entityManager.persist(member);
        return member;
    }
}