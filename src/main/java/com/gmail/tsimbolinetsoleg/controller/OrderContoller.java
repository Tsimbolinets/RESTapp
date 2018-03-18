package com.gmail.tsimbolinetsoleg.controller;


import com.gmail.tsimbolinetsoleg.domain.Order;
import com.gmail.tsimbolinetsoleg.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class OrderContoller {
    static final int ITEMS_PER_PAGE = 6;
    static final int page=0;

    @Autowired
    private ContactService contactService;

    @RequestMapping("/orders")
    public String orders(Model model, @RequestParam(required = false, defaultValue = "0") Integer page) {
        if (page < 0) page = 0;
        List<Order> orders = contactService
                .findAllOrders(new PageRequest(page, ITEMS_PER_PAGE, Sort.Direction.DESC, "id"));
        model.addAttribute("orders", orders);
        model.addAttribute("allPages", getPageCountOrders());
        return "orders";
    }

    @RequestMapping(value = "/searchcontactorders", method = RequestMethod.POST)
    public String searchContactOrders(@RequestParam String pattern, Model model) {
        model.addAttribute("orders", contactService.findByPatternOrders(pattern, null));
        return "orders";
    }

    private long getPageCountOrders() {
        long totalCount = contactService.countOrders();
        return (totalCount / ITEMS_PER_PAGE) + ((totalCount % ITEMS_PER_PAGE > 0) ? 1 : 0);
    }
}
