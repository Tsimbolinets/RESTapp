package com.gmail.tsimbolinetsoleg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
public class MyController {
    static final int ITEMS_PER_PAGE = 6;
    static final int page=0;

    @Autowired
    private ContactService contactService;

    @RequestMapping("/")
    public String index(Model model, @RequestParam(required = false, defaultValue = "0") Integer page) {
        if (page < 0) page = 0;
        List<Contact> contacts = contactService
                .findAll(new PageRequest(page, ITEMS_PER_PAGE, Sort.Direction.DESC, "id"));
        model.addAttribute("contacts", contacts);
        model.addAttribute("allPages", getPageCount());
        return "index";
    }


    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String search(@RequestParam String pattern, Model model) {
        model.addAttribute("contacts", contactService.findByPattern(pattern, null));
       return "index";
    }

    @RequestMapping(value = "/searchcontactorders", method = RequestMethod.POST)
    public String searchContactOrders(@RequestParam String pattern, Model model) {
        model.addAttribute("orders", contactService.findByPatternOrders(pattern, null));
        return "orders";
    }

    @RequestMapping(value = "/contact/delete", method = RequestMethod.POST)
    public ResponseEntity<Void> delete(@RequestParam(value = "toDelete[]", required = false) long[] toDelete) {
        if (toDelete != null && toDelete.length > 0)
            contactService.deleteContacts(toDelete);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @RequestMapping(value="/contact/add", method = RequestMethod.POST)
    public String contactAdd(@RequestParam String name,
                             @RequestParam String surname,
                             @RequestParam(value = "sex") String sex,
                             @RequestParam String birthday,
                             @RequestParam String identnumber)
    {

        Contact contact = new Contact(name, surname, sex, birthday,identnumber);
        contactService.addContact(contact);

        return "redirect:/";
    }

    @RequestMapping(value="/contact/change", method = RequestMethod.POST)
    public String changeContact(@RequestParam(value = "id") long id,
                             @RequestParam String surname,
                             @RequestParam(value = "sex") String sex,
                             @RequestParam String birthday,
                             @RequestParam String identnumber)
    {
        contactService.setById(surname,sex, birthday,identnumber,id);
        return "redirect:/";
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

    @RequestMapping("/contact_add_page")
    public String contactAddPage() {
        return "contact_add_page";
    }

    @RequestMapping("/contact_change_page")
    public String contactchangepage(Model model) {
        model.addAttribute("contacts", contactService.findAllContacts());
        return "contact_change_page";
    }

    @RequestMapping("/order_add_page")
    public String orderAddPage(Model model) {
        List<Contact> contacts = contactService
                .findAllContacts();
        model.addAttribute("contacts", contacts);
        return "order_add_page";
    }

    @RequestMapping("/orders")
    public String orders(Model model, @RequestParam(required = false, defaultValue = "0") Integer page) {
        if (page < 0) page = 0;
        List<Order> orders = contactService
                .findAllOrders(new PageRequest(page, ITEMS_PER_PAGE, Sort.Direction.DESC, "id"));
        model.addAttribute("orders", orders);
        model.addAttribute("allPages", getPageCountOrders());
        return "orders";
    }

    private long getPageCount() {
        long totalCount = contactService.count();
        return (totalCount / ITEMS_PER_PAGE) + ((totalCount % ITEMS_PER_PAGE > 0) ? 1 : 0);
    }

    private long getPageCountOrders() {
        long totalCount = contactService.countOrders();
        return (totalCount / ITEMS_PER_PAGE) + ((totalCount % ITEMS_PER_PAGE > 0) ? 1 : 0);
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