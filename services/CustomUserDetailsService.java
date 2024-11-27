package ru.batievsky.personalAccountViewer.services;

import ru.batievsky.personalAccountViewer.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("searching email");
        System.out.println("email: " + email);
        System.out.println(userRepository.findByEmail(email));
        return userRepository.findByEmail(email);
    }
}