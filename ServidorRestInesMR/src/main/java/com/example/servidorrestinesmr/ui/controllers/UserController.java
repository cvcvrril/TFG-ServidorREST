package com.example.servidorrestinesmr.ui.controllers;

import com.example.servidorrestinesmr.data.model.UserResponse;
import com.example.servidorrestinesmr.domain.services.ServiceUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final ServiceUser serviceUser;

    @GetMapping("/byId")
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public UserResponse getUserById(@RequestParam("id") int id ) {
        return serviceUser.getUserById(id).get();
    }
}
