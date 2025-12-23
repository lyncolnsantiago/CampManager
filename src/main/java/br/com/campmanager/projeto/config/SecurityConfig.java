package br.com.campmanager.projeto.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // 1. Configura as regras de acesso (Quem pode ver o quê)
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Desativa proteção CSRF (necessário para APIs REST simples)
            .authorizeHttpRequests(auth -> auth
                // PERMITIR ACESSO AOS ARQUIVOS ESTÁTICOS (HTML, Imagens, CSS)
                .requestMatchers("/", "/index.html", "/login.html", "/dashboard.html").permitAll()
                .requestMatchers("/images/**", "/css/**", "/js/**").permitAll()
                
                // PERMITIR ACESSO AOS ENDPOINTS DE AUTENTICAÇÃO
                .requestMatchers("/auth/**").permitAll() 
                
                // QUALQUER OUTRA COISA PRECISA ESTAR LOGADO
                .anyRequest().authenticated()
            );
            
        return http.build();
    }

    // 2. Bean de Criptografia de Senha (já tínhamos feito)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 3. Bean de Gerenciamento de Autenticação (já tínhamos feito)
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}