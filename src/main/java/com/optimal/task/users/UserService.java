package com.optimal.task.users;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.optimal.task.faculty.IFacultyService;
import com.optimal.task.group.GroupEntity;
import com.optimal.task.group.GroupRepo;
import com.optimal.task.group.IGroupService;
import com.optimal.task.mark.Mark;
import com.optimal.task.message.StateMessage;
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
import java.util.*;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService, IUserService {
    private final UsersRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;
    private final GroupRepo groupRepo;
    private final IGroupService iGroupService;
    private final IFacultyService iFacultyService;


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

    public StateMessage addStudent(UsersDTO dto) {

        Optional<Role> optionalRole = roleRepo.findByName(RoleType.STUDENT.getName());
        Optional<GroupEntity> optionalGroup = groupRepo.findById(dto.getGroupId());

        if (optionalRole.isEmpty() || optionalGroup.isEmpty()) {
            return new StateMessage().wrongInformation();
        }
        Users users = new Users(
                dto.getName(),
                generateUsernameAndPassword(),
                passwordEncoder.encode(generateUsernameAndPassword()),
                List.of(optionalRole.get()),
                optionalGroup.get()
        );

        userRepo.save(users);
        return new StateMessage().successAdd();
    }

    public StateMessage editStudent(UsersDTO dto, Long id) {

        Optional<Users> optionalUsers = userRepo.findById(id);
        Optional<Role> optionalRole = roleRepo.findByName(RoleType.STUDENT.getName());
        Optional<GroupEntity> optionalGroup = groupRepo.findById(dto.getGroupId());

        if (optionalRole.isEmpty() || optionalGroup.isEmpty() || optionalUsers.isEmpty()) {
            return new StateMessage().wrongInformation();
        }

        Users users = optionalUsers.get();
        users.setName(dto.getName());
        users.setGroupEntity(optionalGroup.get());
        userRepo.save(users);
        return new StateMessage().successEdit();
    }

    public StateMessage deleteStudent(Long id) {
        Optional<Users> optionalUsers = userRepo.findById(id);

        if (optionalUsers.isEmpty()) {
            return new StateMessage().wrongInformation();
        }

        userRepo.deleteById(id);
        return new StateMessage().successDelete();
    }

    public List<UsersResDTO> searchByName(String name) {

        List<Users> all = userRepo.findAllByNameContainingIgnoreCase(name);

        List<UsersResDTO> list = new ArrayList<>();

        for (Users users : all) {
            list.add(parseToDTO(users));
        }

        list.sort(Comparator.comparing(UsersResDTO::getName));

        return list;
    }

    public List<UsersResDTO> getAllByGroupId(Long groupId) {

        List<Users> all = userRepo.findAllByGroupEntity_Id(groupId);

        List<UsersResDTO> list = new ArrayList<>();

        for (Users users : all) {
            list.add(parseToDTO(users));
        }

        list.sort((h1, h2) -> {
            if (h1.getTotalScore() < h2.getTotalScore())
                return 1;
            if (h1.getTotalScore() > h2.getTotalScore())
                return -1;
            return 0;
        });
        return list;
    }

    public UsersResDTO parseToDTO(Users users) {

        return new UsersResDTO(
                users.getId(),
                users.getName(),
                users.getUsername(),
                users.getGroupEntity() != null ? iGroupService.parseToDTO(users.getGroupEntity()) : null,
                users.getUpdateDate(),
                users.getCreateDate(),
                users.getGroupEntity() != null ? iFacultyService.parseToDTO(users.getGroupEntity().getFaculty()) : null,
                users.getMarkList().stream()
                        .mapToInt(Mark::getScore)
                        .sum()
        );
    }

    private String generateUsernameAndPassword() {
        StringBuilder str = new StringBuilder();

        for (int j = 0; j < 3; j++) {
            str.append(Character.toString(new Random().nextInt(65, 90)));
            str.append(Character.toString(new Random().nextInt(97, 122)));
            str.append((new Random().nextInt(0, 9)));
        }
        return str.toString();
    }
}