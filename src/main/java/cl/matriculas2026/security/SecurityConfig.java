package cl.matriculas2026.security;

import cl.matriculas2026.entity.Funcionario;
import cl.matriculas2026.service.FuncionarioService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.*;

import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final FuncionarioService funcionarioService;

    public SecurityConfig(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            Funcionario f = funcionarioService.findByEmail(username)
                    .orElseThrow(() -> new UsernameNotFoundException("Funcionario no encontrado"));
            if (!Boolean.TRUE.equals(f.getEnabled())) {
                throw new UsernameNotFoundException("Funcionario deshabilitado");
            }
            return User.withUsername(f.getEmail())
                    .password(f.getPasswordHash())
                    .roles(f.getRole()) // “FUNCIONARIO”
                    .build();
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance(); // ⚠️ Solo para desarrollo
    }

   
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(reg -> reg
                .requestMatchers("/", "/matriculas/**", "/css/**", "/js/**", "/img/**").permitAll()
                .requestMatchers("/funcionarios/login", "/funcionarios/logout","/funcionarios/matriculas-list").permitAll()
                .requestMatchers("/funcionarios/**").hasRole("FUNCIONARIO")
                .anyRequest().permitAll()
            )
            .formLogin(form -> form
                .loginPage("/funcionarios/login")
                .loginProcessingUrl("/funcionarios/login")
                .defaultSuccessUrl("/funcionarios/matriculas-list", true)
                .failureUrl("/funcionarios/login?error")
            )
            .logout(logout -> logout
                .logoutUrl("/funcionarios/logout")
                .logoutSuccessUrl("/funcionarios/login")
                .permitAll()
            )
            .csrf(Customizer.withDefaults());

        return http.build();
    }
}
