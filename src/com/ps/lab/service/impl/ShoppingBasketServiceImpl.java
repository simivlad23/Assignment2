package com.ps.lab.service.impl;

import com.ps.lab.model.Item;
import com.ps.lab.model.ShoppingBasket;
import com.ps.lab.model.ShoppingBasketItem;
import com.ps.lab.repository.ItemRepository;
import com.ps.lab.repository.ShoppingBasketItemRepository;
import com.ps.lab.repository.ShoppingBasketRepository;
import com.ps.lab.service.ShoppingBasketService;

import java.util.List;
import java.util.stream.Collectors;

public class ShoppingBasketServiceImpl implements ShoppingBasketService {

    private final ItemRepository itemRepository;
    private final ShoppingBasketItemRepository shoppingBasketItemRepository;
    private final ShoppingBasketRepository shoppingBasketRepository;

    public ShoppingBasketServiceImpl(ItemRepository itemRepository,
                                     ShoppingBasketItemRepository shoppingBasketItemRepository,
                                     ShoppingBasketRepository shoppingBasketRepository) {
        this.itemRepository = itemRepository;
        this.shoppingBasketItemRepository = shoppingBasketItemRepository;
        this.shoppingBasketRepository = shoppingBasketRepository;
    }

    @Override
    public void addItem(Long shoppingBasketId, Long itemId, int quantity) {
        Item item = itemRepository.findById(itemId);

        ShoppingBasketItem shoppingBasketItem = shoppingBasketItemRepository.findByShoppingBasketIdAndByItemId(shoppingBasketId, itemId);

        if (shoppingBasketItem != null) {
            shoppingBasketItem.setQuantity(shoppingBasketItem.getQuantity() + quantity);

            shoppingBasketItemRepository.update(shoppingBasketItem);
        } else {
            shoppingBasketItem = new ShoppingBasketItem();
            shoppingBasketItem.setShoppingBasketId(shoppingBasketId);
            shoppingBasketItem.setItem(item);
            shoppingBasketItem.setQuantity(quantity);

            shoppingBasketItemRepository.create(shoppingBasketItem);
        }
    }

    @Override
    public ShoppingBasket findById(Long id) {
        return shoppingBasketRepository.findById(id);
    }

    @Override
    public ShoppingBasket findByUserId(Long userId) {
        return shoppingBasketRepository.findAll()
                .stream()
                .filter(shoppingBasket -> shoppingBasket.getUser().getId().equals(userId))
                .findFirst().get();
    }
}
