package jpaBook.jpaShop.contorller;

import jpaBook.jpaShop.domain.item.Item;
import jpaBook.jpaShop.domain.member.Member;
import jpaBook.jpaShop.domain.order.OrderSearch;
import jpaBook.jpaShop.service.ItemService;
import jpaBook.jpaShop.service.MemberService;
import jpaBook.jpaShop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final MemberService memberService;
    private final ItemService itemService;

    @GetMapping("/order")
    public String createForm(Model model) {
        List<Member> members = memberService.findMembers();
        List<Item> items = itemService.findItems();

        model.addAttribute("members", members);
        model.addAttribute("items", items);
        return "/order/orderForm";
    }
    @PostMapping("/order")
    public String create(@RequestParam("memberId") Long memberId, @RequestParam("itemId") Long itemId, @RequestParam("count") int count) {
        orderService.order(memberId, itemId, count);
        return "redirect:/orders";
    }
    @GetMapping("/orders")
    public String List(@ModelAttribute("orderSearch")OrderSearch orderSearch, Model model) {
        orderService.findOrders(orderSearch);
        return "/order/orderList";
    }
}
