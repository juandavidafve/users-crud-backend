package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> list() {
        return userRepository.findAll();
    }

    public User listOne(Integer id) {
        Optional<User> userDb = userRepository.findById(id);

        if (userDb.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User does not exist");
        }

        return userDb.get();
    }

    public User update(User user, Integer id) {
        listOne(id);

        user.setId(id);

        return userRepository.save(user);
    }

    public User create(User user) {
        user.setId(null);

        return userRepository.save(user);
    }

    public User delete(Integer id) {
        User user = listOne(id);

        userRepository.delete(user);

        return user;
    }
}
