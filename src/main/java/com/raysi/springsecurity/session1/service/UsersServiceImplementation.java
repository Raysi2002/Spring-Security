package com.raysi.springsecurity.session1.service;

import com.raysi.springsecurity.session1.entity.UserPrincipal;
import com.raysi.springsecurity.session1.entity.Users;
import com.raysi.springsecurity.session1.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImplementation implements UserService{

    private final UserRepository userRepository;

    public UsersServiceImplementation(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepository.findByUserName(username);

        if(user == null){
            System.out.println("No such user found");
            throw new UsernameNotFoundException("No such user found");
        }
        return new UserPrincipal(user);
    }
}
