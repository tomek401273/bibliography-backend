package com.tgrajkowski.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
@CrossOrigin(origins = "*")
public class LoginController {

    @RequestMapping(value = "/check", method = RequestMethod.GET)
    public String login() {
        return "Succesfully login";
    }
}
