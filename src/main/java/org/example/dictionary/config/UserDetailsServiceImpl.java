//package org.example.dictionary.config;
//
//import org.example.dictionary.model.User;
//import org.example.dictionary.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class UserDetailsServiceImpl {
//    @Autowired
//    UserRepository userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        System.out.println("DEBUG: Пошук користувача за email: " + email);
//        User user = userRepository.findByEmail(email);
//        if (user == null) {
//            System.out.println("DEBUG: Користувач не знайдений!");
//            throw new UsernameNotFoundException("User not found");
//        }
//        System.out.println("DEBUG: Користувач знайдений: " + user.getEmail());
//        System.out.println("DEBUG: Його хеш пароля: " + user.getPassword());
//        return UserDetailsImpl.build(user);
//    }
//}
