package org.example.ecommservicecustomer.repositories;

import org.example.ecommservicecustomer.models.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ICartItemRepository extends JpaRepository<CartItem,String> {
    List<CartItem> findAllByCustomerId(String customerId);

    void deleteByCustomerId(String customerId);
}
