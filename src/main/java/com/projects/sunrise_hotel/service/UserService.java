package com.projects.sunrise_hotel.service;


import com.projects.sunrise_hotel.exception.UserAlreadyExistsException;
import com.projects.sunrise_hotel.model.Role;
import com.projects.sunrise_hotel.model.User;
import com.projects.sunrise_hotel.repository.RoleRepository;
import com.projects.sunrise_hotel.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Override
    public User registerUser(User user){
        if(userRepository.existsByEmail(user.getEmail())){
            throw new UserAlreadyExistsException(user.getEmail()+ "already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        System.out.println(user.getPassword ());
        Role userRole = roleRepository.findByName("ROLE_USER").orElse(new Role("ROLE_USER"));
        user.setRoles(Collections.singletonList(userRole));
        return userRepository.save(user);


    }

    @Override
    public List<User> getUsers(){
        return userRepository.findAll();
    }

    @Transactional
    @Override
    public void deleteUser(String email){
        User theUser = getUser(email);
        if(theUser!=null){
            userRepository.deleteByEmail(email);
        }
    }
    @Override
    public User getUser(String email){
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User is not found"));
    }

}
