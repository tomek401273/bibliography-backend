//package com.tgrajkowski.controllers;
//
//import com.tgrajkowski.model.newsletter.ConfirmDto;
//import com.tgrajkowski.service.users.AuthenticationService;
//import com.tgrajkowski.user.ChangePassword;
//import com.tgrajkowski.user.UserDto;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//
//
//@RestController()
//@RequestMapping("/auth")
//public class AuthenticationController {
//
//    @Autowired
//    private AuthenticationService authenticationService;
//
//    @RequestMapping(value = "/signup", method = RequestMethod.POST)
//    public UserDto singUp(@RequestBody @Valid UserDto userDto) {
//        System.out.println(userDto.toString());
//        return authenticationService.singUp(userDto);
//    }
//
//    @RequestMapping(value = "/checkLoginAvailable", method = RequestMethod.GET)
//    public boolean checkLoginAvailable(@RequestParam String login) {
//        return authenticationService.checkLoginAvailable(login);
//    }
//
//    @RequestMapping(value ="/account/confirm", method = RequestMethod.POST)
//    public ConfirmDto accountConfirm(@RequestBody ConfirmDto confirmDto) {
//        return authenticationService.accountConfirm(confirmDto);
//    }
//
//    @RequestMapping(value = "/update/account", method = RequestMethod.PUT)
//    public void updateAccount(@RequestBody UserDto userDto) {
//        authenticationService.accountUpdate(userDto);
//    }
//
//    @RequestMapping(value = "/update/password", method = RequestMethod.PUT)
//    public void updatePassword(@RequestBody ChangePassword changePassword){
//        authenticationService.passwordUpdate(changePassword);
//    }
//}
