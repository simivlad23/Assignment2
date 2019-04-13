package com.ps.lab.model;

import java.util.List;

public class ShoppingBasket extends EntityObject {
    private User user;
    private List<ShoppingBasketItem> items;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<ShoppingBasketItem> getItems() {
        return items;
    }

    public void setItems(List<ShoppingBasketItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "ShoppingBasket id=" + super.getId() + " of user=" + user.getUsername();
    }
}
