package org.cris6h16.practicas.Config;


import org.cris6h16.practicas.Models.UserEntity;
import org.cris6h16.practicas.Repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetails implements UserDetailsService {
    UserRepository userRepository;

    public CustomUserDetails(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(username);
        if (userEntity == null) throw new UsernameNotFoundException("User not found");

        UserDetails user = User.withUsername(userEntity.getUsername())
                .password(userEntity.getPassword())
                .authorities(new SimpleGrantedAuthority("ROLE_" + userEntity.getPermission().getName()))
                .build();

        return user;
    }

}
