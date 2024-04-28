package uz.pdp.onekhm.security;

import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import uz.pdp.onekhm.security.jwt.JwtTokenFilter;
import uz.pdp.onekhm.utils.URL;

import java.util.List;
import java.util.Optional;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    @SneakyThrows
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtTokenFilter jwtTokenFilter) {
        http.csrf(AbstractHttpConfigurer::disable);
        http.cors(c->c.configurationSource(corsConfigurationSource()));
        http.authorizeHttpRequests(a->a.requestMatchers(URL.HEAD_URL + URL.AUTH_URL + "/**").permitAll().anyRequest().authenticated());
        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
    @Bean
    public AuditorAware<String> auditorAware(){
        return ()-> Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
    }
    @Bean
    public CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(List.of("*"));
        corsConfiguration.setAllowedMethods(List.of("*"));
        corsConfiguration.setAllowedHeaders(List.of("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",corsConfiguration);
        return source;
    }

}
