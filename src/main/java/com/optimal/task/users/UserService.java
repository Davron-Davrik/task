package com.optimal.task.users;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.optimal.task.message.StateMessage;
import com.optimal.task.role.RoleRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

import static com.optimal.task.statics.StaticWords.JWT_SECRET_KEY;
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
        if (user == null || !user.isState() || user.isDelete()) {
            throw new UsernameNotFoundException("Tizimdan foydalanishga taqiq o'rnatilgan");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }


    public Users parseToken(HttpServletRequest httpServletRequest) {

        String authorizationHeader = httpServletRequest.getHeader(AUTHORIZATION);
        String token = authorizationHeader.replace("Bearer ", "");


        Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET_KEY.getBytes());

        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        String username = decodedJWT.getSubject();
        return userRepo.findByUsername(username);
    }

    public StateMessage changeCredentialsPassword(HttpServletRequest request, UserCredentialDTO userCredentialDTO) {
        Users user = parseToken(request);
        boolean matches = passwordEncoder.matches(userCredentialDTO.getOldPassword(), user.getPassword());
        StateMessage stateMessage;

        if (userCredentialDTO.getNewPassword1().equals(userCredentialDTO.getNewPassword2()) && (userCredentialDTO.getNewPassword1() != null && userCredentialDTO.getNewPassword2() != null)) {
            if (matches) {
                user.setPassword(passwordEncoder.encode(userCredentialDTO.getNewPassword1()));
                stateMessage = new StateMessage("Muvaffaqiyatli o'zgartirildi", true, 200);
            } else {
                stateMessage = new StateMessage("Joriy parol xato kiritildi", false, 401);
            }
        } else {
            stateMessage = new StateMessage("Kiritgan yangi ikki parolingiz bir biriga to'g'ri kelmadi", false, 401);
        }

        userRepo.save(user);
        return stateMessage;
    }

    public StateMessage resetUserCredentials(Integer userId) {
//        Users currentUser = parseToken(request);

//        String roleName = currentUser.getRoles().get(0).getName();

        Optional<Users> optionalUsers = userRepo.findById(userId);

        if (optionalUsers.isPresent()) {
            Users user = optionalUsers.get();

            String passsword = user.getUsername() + user.getUsername();

            user.setPassword(passwordEncoder.encode(passsword));
            userRepo.save(user);
            return new StateMessage("Foydalanuvchi ma'lumotlari muvaffaqiyatli tiklandi\n Yangi parol: " + passsword, true, 200);
        } else {
            return new StateMessage("Tizimda bunday foydalanuvchi mavjud emas", false, 401);
        }

//        return new StateMessage("Sizda foydalanuvchi ma'lumotlarini o'zgartirish huquqi mavjud emas",false,403);
    }
}