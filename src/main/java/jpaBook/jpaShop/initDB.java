package jpaBook.jpaShop;

import jpaBook.jpaShop.domain.address.Address;
import jpaBook.jpaShop.domain.delivery.Delivery;
import jpaBook.jpaShop.domain.item.Book;
import jpaBook.jpaShop.domain.member.Member;
import jpaBook.jpaShop.domain.order.Order;
import jpaBook.jpaShop.domain.orderItem.OrderItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;


@Component
@RequiredArgsConstructor
public class initDB {
    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit1();
        initService.dbInit2();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager entityManager;
        public void dbInit1() {
            Member member = createMember("userA", "서울", "1", "111");
            entityManager.persist(member);

            Book book = createBook("JPA1 BOOK", 10000, 100);
            entityManager.persist(book);
            Book book2 = createBook("JPA2 BOOK", 20000, 100);
            entityManager.persist(book2);

            OrderItem orderItem1 = OrderItem.createOrderItem(book, 10000, 1);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 10000, 2);

            Delivery delivery = createDelivery(member);
            Order order = Order.createOrder(member,delivery, orderItem1, orderItem2);
            entityManager.persist(order);
        }

        private Delivery createDelivery(Member member) {
            Delivery delivery = new Delivery();
            delivery.setAddress(member.getAddress());
            return delivery;
        }

        private Book createBook(String name, int price, int stockQuantity) {
            Book book = new Book();
            book.setName(name);
            book.setPrice(price);
            book.setStockQuantity(stockQuantity);
            return book;
        }

        private Member createMember(String name, String city, String street, String zipCode) {
            Member member = new Member();
            member.setName(name);
            member.setAddress(new Address(city, street, zipCode));
            return member;
        }

        public void dbInit2() {
            Member member = createMember("userB", "부산", "2", "222");
            entityManager.persist(member);

            Book book = createBook("SPRING1 BOOK", 20000, 200);
            entityManager.persist(book);
            Book book2 = createBook("SPRING2 BOOK", 40000, 300);
            entityManager.persist(book2);

            OrderItem orderItem1 = OrderItem.createOrderItem(book, 20000, 3);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 40000, 4);

            Delivery delivery = createDelivery(member);
            Order order = Order.createOrder(member,delivery, orderItem1, orderItem2);
            entityManager.persist(order);
        }
    }
}
