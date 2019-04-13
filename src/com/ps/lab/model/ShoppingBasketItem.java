package com.ps.lab.model;

public class ShoppingBasketItem extends EntityObject {
    private Long shoppingBasketId;
    private Item item;
    private Integer quantity;

    public Long getShoppingBasketId() {
        return shoppingBasketId;
    }

    public void setShoppingBasketId(Long shoppingBasketId) {
        this.shoppingBasketId = shoppingBasketId;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
