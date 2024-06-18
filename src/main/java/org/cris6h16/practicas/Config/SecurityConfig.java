package org.cris6h16.practicas.Config;

//import org.cris6h16.practicas.Repository.UsuarioRepositorio;
import org.cris6h16.practicas.Repository.UsuarioRepositorio;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .formLogin(c -> c
                                .loginPage("/login")
                                .loginProcessingUrl("/login")
                                .defaultSuccessUrl("/", true)
                                .usernameParameter("cedula")
                                .passwordParameter("password")
                                .failureUrl("/login?error")
//                        .permitAll()
                );
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(UsuarioRepositorio u) {
        return new CustomUserDetails(u);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        passwordEncoder.upgradeEncoding("noop");
        return passwordEncoder;
    }

}
