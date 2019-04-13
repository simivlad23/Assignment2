package com.ps.lab.utils.impl;

import com.ps.lab.model.Item;
import com.ps.lab.model.ShoppingBasket;
import com.ps.lab.utils.DataConverter;

import java.util.List;

public class DataConverterImpl implements DataConverter {
    public Object[][] itemToTableData(List<Item> items) {
        Object[][] data = new Object[items.size()][3];
        for (int row = 0; row < data.length; row++) {
            data[row][0] = items.get(row).getId();
            data[row][1] = items.get(row).getName();
            data[row][2] = items.get(row).getPrice();
        }
        return data;
    }

    public Object[][] shoppingBasketToTableData(ShoppingBasket shoppingBasket) {
        Object[][] data = new Object[shoppingBasket.getItems().size()][3];
        for (int row = 0; row < data.length; row++) {
            data[row][0] = shoppingBasket.getItems().get(row).getId();
            data[row][1] = shoppingBasket.getItems().get(row).getItem().getName();
            data[row][2] = shoppingBasket.getItems().get(row).getQuantity();
        }
        return data;
    }

    public String[] itemToTableColumnNames() {
        return new String[]{"Id", "Name", "Price"};
    }

    public String[] shoppingBasketToTableColumnNames() {
        return new String[]{"Id", "Name", "Quantity"};
    }
}
