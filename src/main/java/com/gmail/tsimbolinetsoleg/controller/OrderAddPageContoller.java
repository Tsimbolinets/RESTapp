package com.gmail.tsimbolinetsoleg.controller;

import com.gmail.tsimbolinetsoleg.domain.Contact;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class OrderAddPageContoller {

    static final int ITEMS_PER_PAGE = 6;
    static final int page=0;

    @Autowired
    private ContactService contactService;

    @RequestMapping("/order_add_page")
    public String orderAddPage(Model model) {
        List<Contact> contacts = contactService
                .findAllContacts();
        model.addAttribute("contacts", contacts);
        return "order_add_page";
    }

    @RequestMapping(value="/orders/add", method = RequestMethod.POST)
    public String orderAdd(@RequestParam(value = "id") long id,
                           @RequestParam String summ,
                           @RequestParam(value = "currency") String currency)
    {
        List<Order> order = new ArrayList<>();
        Order order1 = new Order(getTime(), "performed", summ, currency, findById(id));
        order.add(order1);
        contactService.addOrder(order);
        return "redirect:/orders";
    }

    private String getTime() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yy");
        String time = sdf.format(date);
        return time;
    }

    private Contact findById(long id){
        List<Contact> contact = contactService.findById(id ,new PageRequest(page, ITEMS_PER_PAGE, Sort.Direction.DESC, "id"));
        Contact contact1 = contact.get(0);
        return contact1;
    }
}
