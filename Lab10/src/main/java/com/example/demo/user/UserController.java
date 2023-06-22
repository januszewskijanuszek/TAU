package com.example.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@RestController
@RequestMapping(path = "rest/user")
public class UserController {

//    RestTemplate restTemplate = new RestTemplate();
//    WebClient webClient = WebClient.create("http://localhost:8082");

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Tells to service what they want and give it to main func
    @GetMapping
    public List<User> getUsers(){
        return userService.getUsers();
    }
}
