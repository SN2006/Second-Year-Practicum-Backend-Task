package com.example.app.libraryapi.shell;

import com.example.app.libraryapi.entity.enums.Role;
import com.example.app.libraryapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.Arrays;

@ShellComponent
public class CommandLineHandler {

    private final UserService userService;

    @Autowired
    public CommandLineHandler(UserService userService) {
        this.userService = userService;
    }

    @ShellMethod(value = "Set role for user", key = "set_role")
    public String setRole(String email, String role) {
        if (
                Arrays.stream(Role.values())
                        .noneMatch(r -> r.name().equalsIgnoreCase(role))
        ){
            return "Unknown role. Available roles are: " + Arrays.toString(Role.values());
        }
        try {
            userService.setRoleByEmail(email, Role.valueOf(role.toUpperCase()));
            return "Role set successfully";
        }catch (Exception e){
            return e.getMessage();
        }
    }

}
