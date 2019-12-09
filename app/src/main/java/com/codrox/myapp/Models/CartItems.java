package com.codrox.myapp.Models;

public class CartItems {
    private String cartId;
    private SubTopicsInfo subTopicsInfo;

    public CartItems(String cartId, SubTopicsInfo subTopicsInfo) {
        this.cartId = cartId;
        this.subTopicsInfo = subTopicsInfo;
    }

    public String getCartId() {
        return cartId;
    }

    public SubTopicsInfo getSubTopicsInfo() {
        return subTopicsInfo;
    }
}
