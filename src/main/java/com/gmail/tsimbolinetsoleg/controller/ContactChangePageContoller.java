package com.gmail.tsimbolinetsoleg.controller;


import com.gmail.tsimbolinetsoleg.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ContactChangePageContoller {

    @Autowired
    private ContactService contactService;

    @RequestMapping("/contact_change_page")
    public String contactchangepage(Model model) {
        model.addAttribute("contacts", contactService.findAllContacts());
        return "contact_change_page";
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
}
