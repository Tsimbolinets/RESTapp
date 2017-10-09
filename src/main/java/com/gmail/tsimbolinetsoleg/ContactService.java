package com.gmail.tsimbolinetsoleg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
public class ContactService {
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private OrderRepository orderRepository;

    @Transactional
    public void addContacts(List<Contact> contact) {
        contactRepository.save(contact);
    }

    @Transactional
    public void addContact(Contact contact) {
        contactRepository.save(contact);
    }

    @Transactional
    public List<Contact> findAll(Pageable pageable) {
        return contactRepository.findAll(pageable).getContent();
    }

    @Transactional
    public List<Contact> findAllContacts() {
        return contactRepository.findAll();
    }

    @Transactional
    public List<Order> findAllOrders(Pageable pageable) {
        return orderRepository.findAll(pageable).getContent();
    }

    @Transactional
    public void addOrder(List<Order> order) {
        orderRepository.save(order);
    }

    @Transactional
    public Integer setById(String surname, String sex, String birthday, String identnumber, long id) { return contactRepository.setById(surname,sex,birthday,identnumber,id);
    }

    @Transactional
    public void deleteContacts(long[] idList) {
        for (long id : idList)
            contactRepository.delete(id);
    }


    @Transactional(readOnly=true)
    public List<Contact> findByPattern(String pattern, Pageable pageable) {
        return contactRepository.findByPattern(pattern, pageable);
    }

    @Transactional(readOnly=true)
    public List<Contact> findById(long id, Pageable pageable) {
        return contactRepository.findById(id, pageable);
    }

    @Transactional(readOnly=true)
    public List<Order> findByPatternOrders(String pattern, Pageable pageable) {
        return orderRepository.findByPatternOrders(pattern, pageable);
    }

    @Transactional(readOnly = true)
    public long count() {
        return contactRepository.count();
    }

    @Transactional(readOnly = true)
    public long countOrders() {
        return orderRepository.count();
    }

}