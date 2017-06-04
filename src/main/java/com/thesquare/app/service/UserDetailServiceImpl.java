package com.thesquare.app.service;

import com.thesquare.app.model.Item;
import com.thesquare.app.model.User;
import com.thesquare.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    private void populateSampleData() {
        createUser("admin", "a", Arrays.asList(
                new Item("item 1"),
                new Item("item 2"),
                new Item("item 3")
        ));

        createUser("user", "u", Arrays.asList(
                new Item("item 21"),
                new Item("item 22"),
                new Item("item 23"),
                new Item("item 24"),
                new Item("item 25"),
                new Item("item 26")
        ));
    }

    private void createUser(String admin2, String a, List<Item> items) {
        User admin = new User(admin2, a);
        admin.setItems(items);
        userRepository.save(admin);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            return user;
        }
    }

}
