//package com.tgrajkowski.service.users;
//
//
//import com.tgrajkowski.user.UserDao;
//import com.tgrajkowski.user.UserMapper;
//import com.tgrajkowski.user.Users;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class UserDetailService implements UserDetailsService {
//    @Autowired
//    UserDao userDao;
//    UserMapper userMapper = new UserMapper();
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Users users = userDao.findByLogin(username);
//        return userMapper.mapToUserDetails(users);
//    }
//}
