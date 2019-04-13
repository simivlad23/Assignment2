package com.ps.lab.repository.impl;

import com.ps.lab.model.Item;
import com.ps.lab.repository.ItemRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemRepositoryImpl implements ItemRepository {

    private final JDBConnectionWrapper jdbConnectionWrapper;
    private Connection connection;

    public ItemRepositoryImpl(JDBConnectionWrapper jdbConnectionWrapper) {
        this.jdbConnectionWrapper = jdbConnectionWrapper;
    }

    @Override
    public List<Item> findAll() {


        Connection connection = jdbConnectionWrapper.getConnection();
        List<Item> items = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM item");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Item item = new Item();

                item.setId(resultSet.getLong(1));
                item.setName(resultSet.getString(2));
                item.setPrice(resultSet.getDouble(3));

                items.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    @Override
    public Item findById(Long id) {
        Connection connection = jdbConnectionWrapper.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM item WHERE id=?");
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Item item = new Item();

                item.setId(resultSet.getLong(1));
                item.setName(resultSet.getString(2));
                item.setPrice(resultSet.getDouble(3));

                return item;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }



        return null;
    }

    @Override
    public Item create(Item item) {
        Connection connection = jdbConnectionWrapper.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO item (name, price) VALUES(?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, item.getName());
            preparedStatement.setDouble(2, item.getPrice());

            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                item.setId(resultSet.getLong(1));
                return item;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Item update(Item item) {
        Connection connection = jdbConnectionWrapper.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE item SET name=?, price=? WHERE id=?",
                    Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, item.getName());
            preparedStatement.setDouble(2, item.getPrice());
            preparedStatement.setLong(3, item.getId());

            int changedRows = preparedStatement.executeUpdate();

            if (changedRows > 0) {
                return item;
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
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM item WHERE id= ?");
            preparedStatement.setLong(1, id);

            int updatedRows = preparedStatement.executeUpdate();

            return updatedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
