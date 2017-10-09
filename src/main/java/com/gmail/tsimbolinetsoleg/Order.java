package com.gmail.tsimbolinetsoleg;


import javax.persistence.*;

@Entity
@Table(name="Orders")
public class Order {
    @Id
    @GeneratedValue
    @Column(name="ORDERS_ID")
    private long id;

    @Column(name="ORDERS_DATE")
    private String date;

    @Column(name="ORDERS_STATUS")
    private String status;

    @Column(name="ORDERS_SUMM")
    private String summ;

    @Column(name="ORDERS_CURRENCY")
    private String currency;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_id")
    private Contact contact;

    public Contact getContact() {
       return contact;
    }
    public void setContact(Contact contact) {
        this.contact = contact;
    }


    public Order() {
    }

    public Order( String date, String status, String summ, String currency , Contact contact) {
        this.date = date;
        this.status = status;
        this.summ = summ;
        this.currency = currency;
       this.contact = contact;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSumm() {
        return summ;
    }

    public void setSumm(String summ) {
        this.summ = summ;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }


}