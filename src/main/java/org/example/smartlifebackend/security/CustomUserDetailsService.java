package org.example.smartlifebackend.security;

import lombok.RequiredArgsConstructor;
import org.example.smartlifebackend.model.User;
import org.example.smartlifebackend.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("User not found")
        );

        return new CustomUserDetails(user);
    }

}