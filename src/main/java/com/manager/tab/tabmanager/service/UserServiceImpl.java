package com.manager.tab.tabmanager.service;

import com.manager.tab.tabmanager.entity.User;
import com.manager.tab.tabmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (user.getRoles() == null || user.getRoles().isBlank()) {
            user.setRoles("USER");
        }
        return userRepository.save(user);
    }

    @Override
    public List<User> fetchUserList() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public User
    updateUser(User user, Long userId){
        User userDB = userRepository.findById(userId).get();

        if (Objects.nonNull(user.getUsername())
            && !"".equalsIgnoreCase(user.getUsername())){
            userDB.setUsername(user.getUsername());
        }
        if (Objects.nonNull(user.getPassword())
                && !"".equalsIgnoreCase(user.getPassword())){
            userDB.setPassword(user.getPassword());
        }
        if (Objects.nonNull(user.getEmail())
                && !"".equalsIgnoreCase(user.getEmail())){
            userDB.setEmail(user.getEmail());
        }
        return userRepository.save(userDB);
    }

    @Override
    public void deleteUserById(Long userId){
        userRepository.deleteById(userId);
    }
}