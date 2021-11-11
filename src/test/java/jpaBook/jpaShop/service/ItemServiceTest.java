package jpaBook.jpaShop.service;

import jpaBook.jpaShop.domain.item.Book;
import jpaBook.jpaShop.domain.item.Item;
import jpaBook.jpaShop.domain.item.ItemRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest

@Transactional
public class ItemServiceTest {
    @Autowired
    ItemService itemService;
    @Autowired
    ItemRepository itemRepository;

    @Test
    public void 아이템입력() throws Exception {
        //given
        Item item = new Book();
        item.setName("젊은 베르테르의 슬픔");
        //when
        itemService.saveItem(item);
        //then
        assertEquals(item, itemRepository.findOne(item.getId()));
    }
}