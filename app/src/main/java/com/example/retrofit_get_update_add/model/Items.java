package com.example.retrofit_get_update_add.model;

import java.util.List;

public class Items {
    private List<User> item = null;

    public Items(List<User> item) {
        this.item = item;
    }

    public List<User> getItem() {
        return item;
    }
    public void setItem(List<User> item) {
        this.item = item;
    }
}
