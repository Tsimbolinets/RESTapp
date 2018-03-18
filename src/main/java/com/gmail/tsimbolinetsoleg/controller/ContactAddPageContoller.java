package com.gmail.tsimbolinetsoleg.controller;

import com.gmail.tsimbolinetsoleg.domain.Contact;
import com.gmail.tsimbolinetsoleg.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ContactAddPageContoller {

    @Autowired
    private ContactService contactService;

    @RequestMapping("/contact_add_page")
    public String contactAddPage() {
        return "contact_add_page";
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
}
