package com.star.eswasthyabackend.service.user;

import com.star.eswasthyabackend.dto.user.UserResetPasswordRequest;
import com.star.eswasthyabackend.dto.user.UserSignUpRequest;
import com.star.eswasthyabackend.exception.AppException;
import com.star.eswasthyabackend.model.Role;
import com.star.eswasthyabackend.model.User;
import com.star.eswasthyabackend.repository.RoleRepository;
import com.star.eswasthyabackend.repository.UserRepository;
import com.star.eswasthyabackend.security.SecurityConfiguration;
import com.star.eswasthyabackend.service.EmailSenderService;
import com.star.eswasthyabackend.service.AccountVerificationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    private final AccountVerificationService accountAccountVerificationService;
    private final EmailSenderService emailSenderService;

    @Override
    public Integer addNewUser(UserSignUpRequest userSignUpRequest) {
        User newUser = new User();
        if (userRepository.loadUserByUsername(userSignUpRequest.getEmail()) != null) {
            throw new AppException("User already exists for given email.", HttpStatus.BAD_REQUEST);
        }
        newUser.setId(userSignUpRequest.getId());
        newUser.setFirstName(userSignUpRequest.getFirstName());
        newUser.setLastName(userSignUpRequest.getLastName());
        newUser.setEmail(userSignUpRequest.getEmail());
        newUser.setPassword(securityConfiguration.getPasswordEncoder().encode(userSignUpRequest.getPassword()));
        newUser.setIsVerified(false);
        newUser.setResetPasswordEnabled(false);
        newUser.setIsFormFilled(false);

        UUID uuid = UUID.randomUUID();
        String verificationToken = uuid.toString();
        newUser.setVerificationToken(verificationToken);

        LocalTime tokenGeneratedTime = LocalTime.now();
        newUser.setVerifyTokenGenTime(tokenGeneratedTime);

        List<Role> roleList = new ArrayList<>();

        for (Integer roleId : userSignUpRequest.getRolesId()) {
            Role role = roleRepository.findById(roleId)
                    .orElseThrow(() -> new AppException("Role doesn't exist.", HttpStatus.BAD_REQUEST));
            roleList.add(role);
        }
        newUser.setRoles(roleList);
        userRepository.saveAndFlush(newUser);
        //send verification email
        accountAccountVerificationService.verifyOrResetAccount(userSignUpRequest.getEmail(), newUser.getId(), verificationToken);

        return newUser.getId();
    }

    @Override
    public Boolean verifyAccount(Integer userId, String verifyToken) {
        User existingUser = userRepository.findById(userId).orElseThrow(
                () -> new AppException("This token is not associated with any user.", HttpStatus.BAD_REQUEST)
        );

        if (Boolean.TRUE.equals(existingUser.getIsVerified())) {
            throw new AppException("Invalid Token. Please try again!", HttpStatus.BAD_REQUEST);
        }
        String databaseToken = existingUser.getVerificationToken();
        LocalTime sentTime = existingUser.getVerifyTokenGenTime();
        LocalTime currentTime = LocalTime.now();

        long timeDifference = Duration.between(sentTime, currentTime).toSeconds();

        if (timeDifference > 1800) {
            throw new AppException("Link has expired. Please try again!", HttpStatus.BAD_REQUEST);
        }

        if (!Objects.equals(verifyToken, databaseToken)) {
            throw new AppException("Invalid Token. Please try again!", HttpStatus.BAD_REQUEST);
        }

        existingUser.setIsVerified(true);
        existingUser.setVerificationToken(null);
        existingUser.setVerifyTokenGenTime(null);
        userRepository.save(existingUser);

        return true;
    }

    @Override
    public void resendVerificationLink(Integer id) {

        User existingUser = userRepository.findById(id)
                .orElseThrow(()-> new AppException("User doesn't exist.", HttpStatus.BAD_REQUEST));

        if (Boolean.TRUE.equals(existingUser.getIsVerified())){
            throw new AppException("User is already verified. Please log in.", HttpStatus.BAD_REQUEST);
        }

        String email = existingUser.getEmail();
        LocalTime tokenSentTime = LocalTime.now();
        UUID uuid = UUID.randomUUID();
        String verificationToken = uuid.toString();

        existingUser.setVerificationToken(verificationToken);
        existingUser.setVerifyTokenGenTime(tokenSentTime);
        userRepository.save(existingUser);

        accountAccountVerificationService.verifyOrResetAccount(email, id, verificationToken);
    }

    @Override
    public void resetPasswordRequest(String email) {
        User user = userRepository.loadUserByUsername(email);
        if(user==null){
            throw new AppException("User not found for given email address.", HttpStatus.BAD_REQUEST);
        }

        Integer id = user.getId();
        UUID uuid = UUID.randomUUID();
        String resetToken = uuid.toString();
        LocalTime resetTokenGenTime = LocalTime.now();

        user.setResetPasswordToken(resetToken);
        user.setResetPasswordTokenGenTime(resetTokenGenTime);
        userRepository.save(user);

        emailSenderService.sendEmail(email,
                "Please click the link below to reset your password. The link expires in 30 minutes. \n" +
                        "localhost:8080/api/user/verify-reset-password-link/"+id+"/"+resetToken,
                "Reset Your Password");
    }

    @Override
    public String verifyResetPasswordLink(Integer id, String token) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(()-> new AppException("This link is not associated with any user.", HttpStatus.BAD_REQUEST));
        String databaseToken = existingUser.getResetPasswordToken();
        LocalTime tokenSentTime = existingUser.getResetPasswordTokenGenTime();

        if(!databaseToken.equals(token)){
            throw new AppException("Invalid Link. Please try again!", HttpStatus.BAD_REQUEST);
        }

        long timeDifference = Duration.between(tokenSentTime, LocalTime.now()).toSeconds();

        if(timeDifference>18000){
            throw new AppException("Link has expired. Please try again.", HttpStatus.BAD_REQUEST);
        }
        existingUser.setResetPasswordEnabled(true);
        existingUser.setResetPasswordToken(null);
        existingUser.setResetPasswordTokenGenTime(null);
        userRepository.save(existingUser);
        return existingUser.getEmail();
    }

    @Override
    public void changePassword(UserResetPasswordRequest passwordRequest) {

        User existingUser = userRepository.findById(passwordRequest.getId())
                .orElseThrow(()-> new AppException("User doesn't exist.",HttpStatus.BAD_REQUEST ));

        if(Boolean.FALSE.equals(existingUser.getResetPasswordEnabled())){
            throw new AppException("Invalid Request.Please try again!", HttpStatus.BAD_REQUEST);
        }

        existingUser.setPassword(securityConfiguration.getPasswordEncoder().encode(passwordRequest.getNewPassword()));
        existingUser.setResetPasswordEnabled(false);
        userRepository.save(existingUser);
    }

}
