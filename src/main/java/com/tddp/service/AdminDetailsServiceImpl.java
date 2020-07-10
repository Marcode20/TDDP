//package com.tddp.service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import com.tddp.model.Role;
//import com.tddp.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import com.tddp.model.User;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//
//@Service
//public class AdminDetailsServiceImpl implements UserDetailsService{
//    @Autowired
//    private UserRepository usersRepository;
//
//    @Autowired
//    BCryptPasswordEncoder bCryptPasswordEncoder;
//
//    @Override
//    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
//        List<User> usersList = usersRepository.findAdmin(userName);
//
//        if (usersList != null && usersList.size() == 1) {
//            User users = usersList.get(0);
//
//            List<String> roleList = new ArrayList<String>();
//            for (Role role : users.getRoles()) {
//                roleList.add(role.getName());
//            }
//
//            return org.springframework.security.core.userdetails.User.builder()
//                    .username(users.getUsername())
//                    //change here to store encoded password in db
//                    .password( bCryptPasswordEncoder.encode(users.getPassword()) )
////                    .disabled(users.isDisabled())
////                    .accountExpired(users.isAccountExpired())
////                    .accountLocked(users.isAccountLocked())
////                    .credentialsExpired(users.isCredentialsExpired())
//                    .roles(roleList.toArray(new String[0]))
//                    .build();
//        } else {
//            throw new UsernameNotFoundException("User Name is not Found");
//        }
//    }
//}
