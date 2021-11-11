package jpaBook.jpaShop.service;

import jpaBook.jpaShop.domain.address.Address;
import jpaBook.jpaShop.domain.item.Book;
import jpaBook.jpaShop.domain.item.Item;
import jpaBook.jpaShop.domain.member.Member;
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
    public void 상품주문()throws Exception{
        //given
        Member member = new Member();
        Item item = new Book();
        member.setAddress(new Address("","",""));
        //when
        //then
    }
    public void 주문취소()throws Exception{

    }
}