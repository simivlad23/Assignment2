package com.ps.lab.repository.impl;

import com.ps.lab.model.Item;
import com.ps.lab.model.ShoppingBasketItem;
import com.ps.lab.repository.ShoppingBasketItemRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShoppingBasketItemRepositoryImpl implements ShoppingBasketItemRepository {

    private final JDBConnectionWrapper jdbConnectionWrapper;

    public ShoppingBasketItemRepositoryImpl(JDBConnectionWrapper jdbConnectionWrapper) {
        this.jdbConnectionWrapper = jdbConnectionWrapper;
    }

    @Override
    public List<ShoppingBasketItem> findAllByShoppingBasketId(Long shoppingBasketId) {
        Connection connection = jdbConnectionWrapper.getConnection();
        List<ShoppingBasketItem> shoppingBasketItems = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM shopping_basket_item AS s " +
                            "INNER JOIN item AS a ON s.item_id = a.id " +
                            "WHERE shopping_basket_id=?");
            preparedStatement.setLong(1, shoppingBasketId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                ShoppingBasketItem shoppingBasketItem = new ShoppingBasketItem();
                Item item = new Item();

                item.setId(resultSet.getLong(5));
                item.setName(resultSet.getString(6));
                item.setPrice(resultSet.getDouble(7));

                shoppingBasketItem.setId(resultSet.getLong(1));
                shoppingBasketItem.setShoppingBasketId(resultSet.getLong(2));
                shoppingBasketItem.setItem(item);
                shoppingBasketItem.setQuantity(resultSet.getInt(4));

                shoppingBasketItems.add(shoppingBasketItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return shoppingBasketItems;
    }

    @Override
    public ShoppingBasketItem findByShoppingBasketIdAndByItemId(Long shoppingBasketId, Long itemId) {
        Connection connection = jdbConnectionWrapper.getConnection();
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM shopping_basket_item AS s " +
                            "INNER JOIN item AS a ON s.item_id = a.id " +
                            "WHERE shopping_basket_id=? AND item_id=?");
            preparedStatement.setLong(1, shoppingBasketId);
            preparedStatement.setLong(2, itemId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                ShoppingBasketItem shoppingBasketItem = new ShoppingBasketItem();
                Item item = new Item();

                item.setId(resultSet.getLong(5));
                item.setName(resultSet.getString(6));
                item.setPrice(resultSet.getDouble(7));

                shoppingBasketItem.setId(resultSet.getLong(1));
                shoppingBasketItem.setShoppingBasketId(resultSet.getLong(2));
                shoppingBasketItem.setItem(item);
                shoppingBasketItem.setQuantity(resultSet.getInt(4));

                return shoppingBasketItem;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ShoppingBasketItem create(ShoppingBasketItem shoppingBasketItem) {
        Connection connection = jdbConnectionWrapper.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO shopping_basket_item (shopping_basket_id, item_id, quantity) VALUES(?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, shoppingBasketItem.getShoppingBasketId());
            preparedStatement.setLong(2, shoppingBasketItem.getItem().getId());
            preparedStatement.setInt(3, shoppingBasketItem.getQuantity());

            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                shoppingBasketItem.setId(resultSet.getLong(1));
                return shoppingBasketItem;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ShoppingBasketItem update(ShoppingBasketItem shoppingBasketItem) {
        Connection connection = jdbConnectionWrapper.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE shopping_basket_item SET shopping_basket_id=?, item_id=?, quantity=? WHERE id=?",
                    Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, shoppingBasketItem.getShoppingBasketId());
            preparedStatement.setLong(2, shoppingBasketItem.getItem().getId());
            preparedStatement.setInt(3, shoppingBasketItem.getQuantity());
            preparedStatement.setLong(4, shoppingBasketItem.getId());

            int changedRows = preparedStatement.executeUpdate();

            if (changedRows > 0) {
                return shoppingBasketItem;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        Connection connection = jdbConnectionWrapper.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM shopping_basket_item WHERE id= ?");
            preparedStatement.setLong(1, id);

            int updatedRows = preparedStatement.executeUpdate();

            return updatedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
