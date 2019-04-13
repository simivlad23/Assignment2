package com.ps.lab.repository;

import com.ps.lab.model.Item;

import java.util.List;

public interface ItemRepository {

    List<Item> findAll();

    Item findById(Long id);

    Item create(Item item);

    Item update(Item item);

    boolean deleteById(Long id);

}
