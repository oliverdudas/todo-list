package com.thesquare.app.service;

import com.thesquare.app.model.Item;
import com.thesquare.app.model.User;
import com.thesquare.app.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private ItemRepository itemRepository;

    @Override
    @Transactional
    public void save(Item item, String username) {
        User user = (User) userDetailsService.loadUserByUsername(username);
        user.addItem(item);
    }

    @Override
    @Transactional
    public void remove(Item item, String username) {
//        itemRepository.delete(item.getId());
        User user = (User) userDetailsService.loadUserByUsername(username);
        user.getItems()
                .removeIf(itemToRemove ->
                        itemToRemove.getId().equals(item.getId())
                );
    }
}
