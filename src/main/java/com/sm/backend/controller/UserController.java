package com.sm.backend.controller;

import com.sm.backend.entity.User;
import com.sm.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService us;

    @GetMapping("/getUsers")
    public List<User> getUsers() {
        return us.getAllUsers();
    }

    @GetMapping("getUsersByRoleWithPagination/{offset}/{pageSize}/{role}")
    public Page<User> getUsersByRoleWithPagination(
            @PathVariable int offset,
            @PathVariable int pageSize,
            @PathVariable String role
    ) {
        return us.getAllUsersByRoleWithPagination(role, offset, pageSize);
    }

    @GetMapping("/getUser/{id}")
    public Optional<User> getUserById(@PathVariable int id) {
        return us.getUserById(id);
    }

    @GetMapping("/getUserByUsername/{username}")
    public User getUserByUsername(@PathVariable String username) {
        return us.getUserByUsername(username);
    }

    @GetMapping("/registerUser/isUsernameTaken/{username}")
    public boolean isUsernameTaken(@PathVariable String username) {
        return us.isUsernameTaken(username);
    }

    @GetMapping("/registerUser/isEmailTaken/{email}")
    public boolean isEmailTaken(@PathVariable String email) {
        return us.isEmailTaken(email);
    }

    @DeleteMapping("/deleteUser/{id}")
    public void deleteUserById(@PathVariable("id") int id) {
        us.deleteUserById(id);
    }

    @PutMapping("/updateUser/{id}")
    public void updateUser(@PathVariable("id") int id, @RequestBody User user) {
        us.updateUser(user, id);
    }
}
