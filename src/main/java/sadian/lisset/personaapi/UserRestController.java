package sadian.lisset.personaapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.bind.annotation.*;
//TELL ROB YOU HAVE ERRROR CREATING USER AND UPDATING, LOGIN IN WORKS AS OF 12/6/2022 DO NOT FORGET STUPID
@RestController
@RequestMapping("/user")
public class UserRestController {
   // private InMemoryUserDetailsManager inMemoryUserDetailsManager = SecurityConfig.inMemoryUserDetailsManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("")
    public String welcome() {
        return "Welcome to users";
    }

    @PostMapping("/createuser/{username}/{password}/{authority}")
    @PreAuthorize("hasRole('ADMIN')")
    public String createUser(@RequestBody UserModel userModel) {
        Database.createUser(userModel);
        SecurityConfig.inMemoryUserDetailsManager.createUser(User.withUsername(userModel.getUsername()).password(passwordEncoder.encode(userModel.getPassword())).roles(userModel.getAuthority()).build());
        return "User created";
    }

    @PutMapping("/updateuser/{username}/{password}/{authority}")
    @PreAuthorize("hasRole('ADMIN')")
    public String updateUser(@RequestBody UserModel userModel) {
        Database.updateUser(userModel.getUsername(), userModel);
        SecurityConfig.inMemoryUserDetailsManager.updateUser(User.withUsername(userModel.getUsername()).password(passwordEncoder.encode(userModel.getPassword())).roles(userModel.getAuthority()).build());
        return "User updated";
    }

    @DeleteMapping("/deleteuser/{username}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteUser(@PathVariable("username") String username) {
        Database.deleteUser(username);
        SecurityConfig.inMemoryUserDetailsManager.deleteUser(username);
        return "User deleted";
    }
}

