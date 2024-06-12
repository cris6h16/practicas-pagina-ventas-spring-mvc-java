package org.cris6h16.practicas.Service;

import org.cris6h16.practicas.DTOs.UseDTO;
import org.cris6h16.practicas.Models.UserEntity;

public interface UserService {
    UserEntity create(UseDTO dto);
    int countByUsername(String username);
    int countByEmail(String email);
}
