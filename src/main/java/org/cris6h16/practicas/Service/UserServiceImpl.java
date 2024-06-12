package org.cris6h16.practicas.Service;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.cris6h16.practicas.DTOs.UseDTO;
import org.cris6h16.practicas.Models.ERoles;
import org.cris6h16.practicas.Models.Rol;
import org.cris6h16.practicas.Models.UserEntity;
import org.cris6h16.practicas.Repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserEntity create(@NotNull @Valid UseDTO dto) {
        return userRepository.save(UserEntity.builder()
                .username(dto.getUsername())
                .password("{noop}" + dto.getPassword())
                .permission(Rol.builder().name(ERoles.WRITE).build())
                .email(dto.getEmail())
                .build());
    }

    @Override
    public int countByUsername(@NotNull String username) {
        return userRepository.countByUsername(username);
    }

    @Override
    public int countByEmail(@NotNull String email) {
        return userRepository.countByEmail(email);
    }
}
