package com.optimal.task.users;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.optimal.task.role.Role;
import com.optimal.task.role.RoleRepo;
import com.optimal.task.role.RoleType;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {
    private final UsersRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;


    @Override //security metodi
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepo.findByUsername(username);
        if (user == null || !user.isState()) {
            throw new UsernameNotFoundException("Tizimdan foydalanishga taqiq o'rnatilgan");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    public Users parseToken(HttpServletRequest httpServletRequest) {

        String authorizationHeader = httpServletRequest.getHeader(AUTHORIZATION);
        String token = authorizationHeader.replace("Bearer ", "");

        Algorithm algorithm = Algorithm.HMAC256("secret_key".getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        String username = decodedJWT.getSubject();
        return userRepo.findByUsername(username);
    }

    public void addRole() {
        if (!roleRepo.existsByName(RoleType.ADMIN.getName())) {
            roleRepo.save(new Role(RoleType.ADMIN.getName()));
        }
        if (!roleRepo.existsByName(RoleType.KINDERGARDEN_PRINCIPAL.getName())) {
            roleRepo.save(new Role(RoleType.KINDERGARDEN_PRINCIPAL.getName()));
        }
    }

}