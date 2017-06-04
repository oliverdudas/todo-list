package com.thesquare.app.service;

import com.thesquare.app.model.Item;

public interface ItemService {

    void save(Item item, String username);

    void remove(Item item, String username);
}
