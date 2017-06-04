package com.thesquare.app.controller;

import com.thesquare.app.model.ItemRequest;
import com.thesquare.app.model.StatusResponse;
import com.thesquare.app.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping(
            value = "/saveitem",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> getUser(@RequestBody ItemRequest itemRequest) throws AuthenticationException {
        itemService.save(itemRequest.getItem(), itemRequest.getUsername());
        return ResponseEntity.ok(new StatusResponse("OK"));
    }

}
