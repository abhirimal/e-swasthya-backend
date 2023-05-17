package com.star.eswasthyabackend.service.user;

import com.star.eswasthyabackend.dto.user.UserRequestDto;
import com.star.eswasthyabackend.exception.InvalidTokenException;
import com.star.eswasthyabackend.exception.LinkExpiredException;
import com.star.eswasthyabackend.exception.UserAlreadyExistsException;
import com.star.eswasthyabackend.model.Role;
import com.star.eswasthyabackend.model.User;
import com.star.eswasthyabackend.repository.RoleRepository;
import com.star.eswasthyabackend.repository.UserRepository;
import com.star.eswasthyabackend.security.SecurityConfiguration;
import com.star.eswasthyabackend.service.GenerateTokenService;
import com.star.eswasthyabackend.service.UserAccountVerificationService;
import lombok.AllArgsConstructor;
import net.bytebuddy.asm.Advice;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final SecurityConfiguration securityConfiguration;
    private final RoleRepository roleRepository;
    private final UserAccountVerificationService accountVerificationService;

    private final GenerateTokenService generateTokenService;


    @Override
    public Integer addNewUser(UserRequestDto userRequestDto) {
        User newUser = new User();
        if(userRepository.loadUserByUsername(userRequestDto.getUsername()) != null){
            throw new UserAlreadyExistsException("User already exists for given username.");
        }
        newUser.setId(userRequestDto.getId());
        newUser.setPassword(securityConfiguration.getPasswordEncoder().encode(userRequestDto.getPassword()));
        newUser.setUsername(userRequestDto.getUsername());
        newUser.setEmail(userRequestDto.getEmail());
        newUser.setIsVerified(false);
        String token = generateTokenService.generateToken();
        newUser.setToken(token);

        List<Role> roleList = new ArrayList<>();

        for(Integer roleId : userRequestDto.getRolesId()){
            Role role = roleRepository.findById(roleId).orElse(null);
            roleList.add(role);
        }

        newUser.setRoles(roleList);
        userRepository.saveAndFlush(newUser);

        //send verification email
        accountVerificationService.verifyAccount(userRequestDto.getEmail(), newUser.getId(), token);

        return newUser.getId();
    }

    @Override
    public Boolean verifyAccount(Integer userId, String verifyToken) {
        LocalTime sentTime = LocalTime.parse(verifyToken.substring(30));
        LocalTime currentTime = LocalTime.now();

        long timeDifference = Duration.between(sentTime, currentTime).toMinutes();
//        if(currentTime.isBefore(sentTime.plusMinutes(30))){
//            throw new LinkExpiredException("Link has expired");
//        }

        if(timeDifference > 30){
            throw new LinkExpiredException("Link has expired");
        }

        String token = verifyToken.substring(0,30);
        User user= userRepository.findById(userId).orElseThrow(() -> new RuntimeException("gg"));
        String databaseToken = user.getToken().substring(0,30);

        if(!Objects.equals(token, databaseToken)){
            throw new InvalidTokenException("Invalid token");
        }

        return true;
    }
}
