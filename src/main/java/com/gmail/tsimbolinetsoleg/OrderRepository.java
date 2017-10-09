package com.gmail.tsimbolinetsoleg;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT o FROM Order o WHERE LOWER(o.contact.name) LIKE LOWER(CONCAT('%', :pattern, '%'))")
    List<Order> findByPatternOrders(@Param("pattern") String pattern, Pageable pageable);
}
