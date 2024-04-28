package uz.pdp.onekhm.security.jwt;


import io.jsonwebtoken.Claims;
import io.micrometer.common.lang.NonNullApi;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import uz.pdp.onekhm.domain.Role;
import uz.pdp.onekhm.domain.User;
import uz.pdp.onekhm.repo.UserRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@NonNullApi
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            final String token = authorizationHeader.split(" ")[1];
            if (jwtProvider.isValid(token)){
                Claims claims = jwtProvider.parseAllClaims(token);
                Optional<User> user = userRepository.findByEmail(claims.getSubject());
                if (user.isPresent()) {
                    List<SimpleGrantedAuthority> authorities = new ArrayList<>();
                    Role role = user.get().getRole();
                    authorities.add(new SimpleGrantedAuthority(role.getCode()));
                    role.getPermissions().forEach(p->authorities.add(new SimpleGrantedAuthority(p.getCode())));
                    SecurityContextHolder.getContext().setAuthentication(
                            new UsernamePasswordAuthenticationToken(claims.getSubject(),null,authorities)
                    );
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
