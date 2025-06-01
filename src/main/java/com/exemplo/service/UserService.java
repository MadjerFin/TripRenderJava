
package com.exemplo.service;

import com.exemplo.model.User;
import com.exemplo.repository.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class UserService {

    @Inject
    UserRepository userRepository;

    @Transactional
    public void save(User user) {
        userRepository.persist(user);
    }

    public List<User> findAll() {
        return userRepository.listAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id);
    }

    @Transactional
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public User login(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user != null && user.getSenha().equals(password)) {
            return user;
        }
        return null;
    }
}
