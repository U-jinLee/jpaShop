package jpaBook.jpaShop.api;

import jpaBook.jpaShop.domain.order.Order;
import jpaBook.jpaShop.domain.order.OrderRepository;
import jpaBook.jpaShop.domain.order.OrderSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {

    private final OrderRepository orderRepository;

    @GetMapping("/api/v1/simple-orders")
    public List<Order> ordersV1() {
        List<Order> all = orderRepository.findAllByString(new OrderSearch());
        return all;
    }
}
