package sadian.lisset.personaapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserRestController {

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
        return "User created";
    }

    @PutMapping("/updateuser/{username}/{password}/{authority}")
    @PreAuthorize("hasRole('ADMIN')")
    public String updateUser(@RequestBody UserModel userModel) {
        Database.updateUser(userModel.getUsername(), userModel);
        return "User updated";
    }

    @DeleteMapping("/deleteuser/{username}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteUser(@PathVariable("username") String username) {
        Database.deleteUser(username);
        return "User deleted";
    }
}

