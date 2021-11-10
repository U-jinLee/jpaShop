package jpaBook.jpaShop.service;

import jpaBook.jpaShop.domain.item.Item;
import jpaBook.jpaShop.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item){
        itemRepository.save(item);
    }

    public List<Item> findItems(){
        return itemRepository.findAll();
    }

    public Item findOne (Long itemId) {
        return itemRepository.findOne(itemId);
    }
}
