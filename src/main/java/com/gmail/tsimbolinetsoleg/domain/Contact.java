package com.gmail.tsimbolinetsoleg.domain;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="Contacts")
public class Contact {
    @Id
    @GeneratedValue
    @Column(name = "CONTACT_ID")
    private long id;

    @Column(name = "CONTACTS_NAME")
    private String name;

    @Column(name = "CONTACTS_SURNAME")
    private String surname;

    @Column(name = "CONTACTS_SEX")
    private String sex;

    @Column(name = "CONTACTS_BIRTHDAY")
    private String birthday;

    @Column(name = "CONTACTS_IDENTNUMBER")
    private String identnumber;

   @OneToMany(mappedBy="contact", cascade=CascadeType.ALL)
    private List<Order> order = new ArrayList<Order>();

    public List<Order> getOrder() {
        return order;
    }

    public void setOrder(List<Order> order) {
        this.order = order;
    }

    public Contact() {}

    public Contact(String name, String surname, String sex, String birthday, String identnumber) {
        this.name = name;
        this.surname = surname;
        this.sex = sex;
        this.birthday = birthday;
        this.identnumber = identnumber;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getIdentnumber() {
        return identnumber;
    }

    public void setIdentnumber(String identnumber) {
        this.identnumber = identnumber;
    }






}

