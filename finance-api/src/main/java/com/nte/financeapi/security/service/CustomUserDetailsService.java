package com.nte.financeapi.security.service;

import com.nte.financeapi.security.dto.CustomUserDetails;
import com.nte.financecore.domain.User;
import com.nte.financecore.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> optional = userRepository.findByUsername(username);

        if(optional.isPresent()){
            User user = optional.get();
            return CustomUserDetails.builder()
                    .user(user)
                    .build();
        }

        return null;
    }
}
