package com.obcompany.simplecrud.services;

import com.obcompany.simplecrud.entity.User;  
import com.obcompany.simplecrud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.stereotype.Service;

@Service  
public class UserService implements UserInterface {  
  @Autowired
  private UserRepository userRepository;

  @Override
  public Iterable<User> findAll() {
    return userRepository.findAll();
  }

  @Override  
  public User create(User user) {  
    return userRepository.save(user);
  }  
}
