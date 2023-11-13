package com.beautyServices.BeautyServices.security;

import com.beautyServices.BeautyServices.entity.Customer;
import com.beautyServices.BeautyServices.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
public class AuthService implements UserDetailsService {
    private final CustomerRepository customerRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer user = customerRepository.getByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("No username " + username + " found");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(), user.getActive(), true, true, true,
                Set.of(new SimpleGrantedAuthority(user.getRole())));
    }
}