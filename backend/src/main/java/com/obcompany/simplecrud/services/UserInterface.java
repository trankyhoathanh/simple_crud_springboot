package com.obcompany.simplecrud.services;

import com.obcompany.simplecrud.entity.User;  

public interface UserInterface {
    Iterable<User> findAll();
    User create(User user);
}
