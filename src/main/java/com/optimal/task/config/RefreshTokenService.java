package com.optimal.task.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.optimal.task.role.Role;
import com.optimal.task.users.Users;
import com.optimal.task.users.UsersRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.stream.Collectors;

import static com.optimal.task.statics.StaticWords.JWT_SECRET_KEY;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final UsersRepo usersRepo;

    public void checkToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {

            try {
                String refresh_token = authorizationHeader.substring("Bearer ".length());

                Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET_KEY.getBytes());

                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refresh_token);
                String username = decodedJWT.getSubject();
                Users user = usersRepo.findByUsername(username);

                String access_token = JWT.create()
                        .withSubject(user.getUsername())
//                        .withExpiresAt(new Date(System.currentTimeMillis() + (120 * 1000)))
                        .withExpiresAt(new Date(System.currentTimeMillis() + (10 * 1000)))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles", user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                        .sign(algorithm);

                HashMap<String, String> body = new HashMap<>();

                body.put("access_token", access_token);
                body.put("refresh_token", refresh_token);

                response.setContentType(APPLICATION_JSON_VALUE);

//                response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
//                response.setHeader("Access-Control-Allow-Credentials", "true");
//                response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
//                response.setHeader("Access-Control-Max-Age", "3600");
//                response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, remember-me");

                new ObjectMapper().writeValue(response.getOutputStream(), body);

            } catch (Exception e) {
                response.setHeader("error has occured---", e.getMessage());
                response.setStatus(401);
//                    response.sendError(FORBIDDEN.value());
                HashMap<String, String> error = new HashMap<>();
                error.put("Muddati tugagan token", e.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        } else {
            throw new RuntimeException("Refresh token is missing");
        }
    }
}
