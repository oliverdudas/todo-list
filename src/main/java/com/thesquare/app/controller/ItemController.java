package com.thesquare.app.controller;

import com.thesquare.app.model.Item;
import com.thesquare.app.service.ItemService;
import com.thesquare.app.service.TokenService;
import com.thesquare.app.util.RequestMappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private TokenService tokenService;

    @Value("${jwt.header}")
    private String header;

    @RequestMapping(
            value = RequestMappings.API_ITEMS,
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public void save(@RequestBody Item item, HttpServletRequest request) throws AuthenticationException {
        String token = request.getHeader(header);
        itemService.save(item, tokenService.getUsername(token));
    }

    @RequestMapping(
            value = RequestMappings.API_ITEMS,
            method = RequestMethod.PUT
    )
    public void update(@RequestBody Item item, HttpServletRequest request) throws AuthenticationException {
        String token = request.getHeader(header);
        itemService.update(item, tokenService.getUsername(token));
    }

    @RequestMapping(
            value = RequestMappings.API_ITEMS + "/{id}",
            method = RequestMethod.DELETE
    )
    public void remove(@PathVariable Long id, HttpServletRequest request) throws AuthenticationException {
        String token = request.getHeader(header);
        itemService.remove(id, tokenService.getUsername(token));
    }

}
