package jpaBook.jpaShop.domain.order;

import jpaBook.jpaShop.domain.OrderStatus;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderSearch {
    String memberName;
    OrderStatus orderStatus;
}
