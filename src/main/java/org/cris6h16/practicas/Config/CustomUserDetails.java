package org.cris6h16.practicas.Config;


import org.cris6h16.practicas.Models.Usuario;
import org.cris6h16.practicas.Repository.UsuarioRepositorio;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetails implements UserDetailsService {
    UsuarioRepositorio usuarioRepositorio;

    public CustomUserDetails(UsuarioRepositorio userRepository) {
        this.usuarioRepositorio = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String cedula) throws UsernameNotFoundException {
        Usuario userEntity = usuarioRepositorio.findByCedula(cedula)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));


        return User.builder()
                .username(userEntity.getCedula())
                .password(userEntity.getContrasena())
                .authorities(new SimpleGrantedAuthority("ROLE_".concat(userEntity.getRol().getNombre().toString())))
                .build();
    }

}
