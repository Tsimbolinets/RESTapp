package com.gmail.tsimbolinetsoleg.repository;

import com.gmail.tsimbolinetsoleg.domain.Contact;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    @Query("SELECT c FROM Contact c WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :pattern, '%'))")
    List<Contact> findByPattern(@Param("pattern") String pattern, Pageable pageable);

    @Query("SELECT c FROM Contact c WHERE c.id = :id")
    List<Contact> findById(@Param("id") long id, Pageable pageable);

    @Modifying(clearAutomatically = true)
    @Transactional(readOnly = false)
    @Query("UPDATE Contact c SET c.surname = :surname, c.sex = :sex, c.birthday = :birthday, c.identnumber = :identnumber where c.id = :id")
    Integer setById(@Param("surname") String surname, @Param("sex") String sex, @Param("birthday") String email, @Param("identnumber") String identnumber, @Param("id") long id);

}