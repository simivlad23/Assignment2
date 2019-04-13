package com.ps.lab.service;

import com.ps.lab.model.ShoppingBasket;

import java.util.List;

public interface ShoppingBasketService {
    void addItem(Long shoppingBasketId, Long itemId, int quantity);

    ShoppingBasket findById(Long id);

    ShoppingBasket findByUserId(Long userId);
}
