package com.ps.lab.service.impl;

import com.ps.lab.model.ShoppingBasket;
import com.ps.lab.service.ShoppingBasketService;

import java.util.List;

public class ShoppingBasketDecoratorImpl implements ShoppingBasketService {

    private final ShoppingBasketService shoppingBasketService;

    public ShoppingBasketDecoratorImpl(ShoppingBasketService shoppingBasketService) {
        this.shoppingBasketService = shoppingBasketService;
    }

    @Override
    public void addItem(Long shoppingBasketId, Long itemId, int quantity) {
        shoppingBasketService.addItem(shoppingBasketId, itemId, quantity);

        System.out.println("s-a adaugat in cos id: " + shoppingBasketId +
                " " + quantity +" bucati din articolul cu id: " + itemId);
    }

    @Override
    public ShoppingBasket findById(Long id) {
        return shoppingBasketService.findById(id);
    }

    @Override
    public ShoppingBasket findByUserId(Long userId) {
        return shoppingBasketService.findByUserId(userId);
    }
}
