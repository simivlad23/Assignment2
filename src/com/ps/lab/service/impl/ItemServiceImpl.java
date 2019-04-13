package com.ps.lab.service.impl;

import com.ps.lab.model.Item;
import com.ps.lab.repository.ItemRepository;
import com.ps.lab.service.ItemService;

import java.util.List;

public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public List<Item> findAll() {
        return itemRepository.findAll();
    }
}
