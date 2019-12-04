package com.codrox.myapp.Models;

public class CartItems {
    private String cartId;
    private TopicsInfo topicsInfo;

    public CartItems(String cartId, TopicsInfo topicsInfo) {
        this.cartId = cartId;
        this.topicsInfo = topicsInfo;
    }

    public String getCartId() {
        return cartId;
    }

    public TopicsInfo getTopicsInfo() {
        return topicsInfo;
    }
}
