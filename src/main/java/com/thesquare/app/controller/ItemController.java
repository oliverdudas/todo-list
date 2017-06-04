package com.thesquare.app.controller;

import com.thesquare.app.model.ItemRequest;
import com.thesquare.app.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping(
            value = "/items",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public void save(@RequestBody ItemRequest itemRequest) throws AuthenticationException {
        itemService.save(itemRequest.getItem(), itemRequest.getUsername());
    }

    @RequestMapping(
            value = "/items/{id}/{username}",
            method = RequestMethod.DELETE
    )
    public void remove(@PathVariable Long id, @PathVariable String username) throws AuthenticationException {
        itemService.remove(id, username);
    }

}
