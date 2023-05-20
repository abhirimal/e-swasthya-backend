package com.star.eswasthyabackend.service.user;

import com.star.eswasthyabackend.dto.user.UserRequestDto;
import com.star.eswasthyabackend.exception.AppException;
import com.star.eswasthyabackend.model.Role;
import com.star.eswasthyabackend.model.User;
import com.star.eswasthyabackend.repository.RoleRepository;
import com.star.eswasthyabackend.repository.UserRepository;
import com.star.eswasthyabackend.security.SecurityConfiguration;
import com.star.eswasthyabackend.service.VerificationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final SecurityConfiguration securityConfiguration;
    private final RoleRepository roleRepository;
    private final VerificationService accountVerificationService;

    @Override
    public Integer addNewUser(UserRequestDto userRequestDto) {
        User newUser = new User();
        if (userRepository.loadUserByUsername(userRequestDto.getEmail()) != null) {
            throw new AppException("User already exists for given email.", HttpStatus.BAD_REQUEST);
        }
        newUser.setId(userRequestDto.getId());
        newUser.setFirstName(userRequestDto.getFirstName());
        newUser.setLastName(userRequestDto.getLastName());
        newUser.setEmail(userRequestDto.getEmail());
        newUser.setPassword(securityConfiguration.getPasswordEncoder().encode(userRequestDto.getPassword()));
        newUser.setIsVerified(false);

        UUID uuid = UUID.randomUUID();
        String verificationToken = uuid.toString();
        newUser.setVerificationToken(verificationToken);

        LocalTime tokenGeneratedTime = LocalTime.now();
        newUser.setVerifyTokenGenTime(tokenGeneratedTime);

        List<Role> roleList = new ArrayList<>();

        for (Integer roleId : userRequestDto.getRolesId()) {
            Role role = roleRepository.findById(roleId).orElse(null);
            roleList.add(role);
        }
        newUser.setRoles(roleList);
        userRepository.saveAndFlush(newUser);
        //send verification email
        accountVerificationService.verifyAccount(userRequestDto.getEmail(), newUser.getId(), verificationToken);

        return newUser.getId();
    }

    @Override
    public Boolean verifyAccount(Integer userId, String verifyToken) {
        User existingUser = userRepository.findById(userId).orElseThrow(
                ()-> new AppException("This token is not associated with any user.", HttpStatus.BAD_REQUEST)
        );
        String databaseToken = existingUser.getVerificationToken();
        LocalTime sentTime = existingUser.getVerifyTokenGenTime();
        LocalTime currentTime = LocalTime.now();

        long timeDifference = Duration.between(sentTime, currentTime).toNanos();

        if (timeDifference > 1800) {
            throw new AppException("Link has expired.", HttpStatus.BAD_REQUEST);
        }

        if (!Objects.equals(verifyToken, databaseToken)) {
            throw new AppException("Invalid token", HttpStatus.BAD_REQUEST);
        }

        return true;
    }
}
