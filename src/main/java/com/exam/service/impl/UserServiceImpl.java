package com.exam.service.impl;

import java.util.Set;

import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.repo.RoleRepository;
import com.exam.repo.UserRepository;
import com.exam.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public User createUser(User user, Set<UserRole> userRoles) throws Exception {

        User local = this.userRepository.findByUsername(user.getUsername());
        if (local != null) {
            System.out.println("User is already there");
            throw new Exception("User already");

        } else {
            for (UserRole ur : userRoles) {
                roleRepository.save(ur.getRole());
            }
            user.getUserRoles().addAll(userRoles);
            local = this.userRepository.save(user);
        }
        return local;
    }

    @Override
    public User getUser(String username) {
        // TODO Auto-generated method stub
        return this.userRepository.findByUsername(username);
    }

    @Override
    public void deleteUser(Long userId) {
        // TODO Auto-generated method stub
        this.userRepository.deleteById(userId);

    }

    @Override
    public void updateUser(String username, String phone) {
        System.out.println(username);

        User local = this.userRepository.findByUsername(username);
        if (local != null) {
            System.out.println("User is already there");
            local.setPhone(phone);
            local = this.userRepository.save(local);

        } else {
            System.out.println("User is not there");
        }

    }

}
