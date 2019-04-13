package com.ps.lab.utils;

import com.ps.lab.model.Item;
import com.ps.lab.model.ShoppingBasket;

import java.util.List;

public interface DataConverter {
    Object[][] itemToTableData(List<Item> items);

    Object[][] shoppingBasketToTableData(ShoppingBasket shoppingBasket);

    String[] itemToTableColumnNames();

    String[] shoppingBasketToTableColumnNames();
}
