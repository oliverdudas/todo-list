package com.thesquare.app.service;

import com.thesquare.app.model.Item;
import com.thesquare.app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    @Transactional
    public void save(Item item, String username) {
        User user = (User) userDetailsService.loadUserByUsername(username);
        user.addItem(item);
    }

    @Override
    @Transactional
    public void update(Item item, String username) {
        User user = (User) userDetailsService.loadUserByUsername(username);
        List<Item> items = user.getItems();
        if (items != null) {
            items.stream()
                .filter(thisItem -> thisItem.getId().equals(item.getId()))
                .forEach(thisItem -> thisItem.setName(item.getName()));
        }
    }

    @Override
    @Transactional
    public void remove(Long id, String username) {
        User user = (User) userDetailsService.loadUserByUsername(username);
        user.getItems()
                .removeIf(itemToRemove ->
                        itemToRemove.getId().equals(id)
                );
    }
}
