package com.ps.lab.repository;

import com.ps.lab.model.ShoppingBasketItem;

import java.util.List;

public interface ShoppingBasketItemRepository {

    List<ShoppingBasketItem> findAllByShoppingBasketId(Long shoppingBasketId);

    ShoppingBasketItem findByShoppingBasketIdAndByItemId(Long shoppingBasketId, Long itemId);

    ShoppingBasketItem create(ShoppingBasketItem shoppingBasketItem);

    ShoppingBasketItem update(ShoppingBasketItem shoppingBasketItem);

    boolean deleteById(Long id);
}
