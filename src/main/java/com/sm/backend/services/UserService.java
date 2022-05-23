package com.sm.backend.services;

import com.sm.backend.entity.User;
import com.sm.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Component
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository ur;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = ur.findByUsername(username);
        if (user == null) {
            System.out.println("User not found in the database!");
            throw new UsernameNotFoundException("USERNAME NOT FOUND EXCEPTION!");
        } else {
            System.out.println("User found in the database: " + username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole()));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    public List<User> getAllUsers() {
        return ur.findAll();
    }

    public Page<User> getAllUsersByRoleWithPagination(String role, int offset, int pageSize) {
        return ur.findAllByRole(PageRequest.of(offset, pageSize), role);
    }

    public Optional<User> getUserById(int id) {
        return ur.findById(id);
    }

    public User getUserByUsername(String username) {
        return ur.findByUsername(username);
    }

    public boolean isUsernameTaken(String username) {
        return ur.existsByUsername(username);
    }

    public boolean isEmailTaken(String email) {
        return ur.existsByEmail(email);
    }

    public void registerUser(User user) {
        ur.save(user);
    }

    public void deleteUserById(int id) {
        ur.deleteById(id);
    }

    public void updateUser(User user, int id) {
        user.setId(id);
        ur.save(user);
    }
}
