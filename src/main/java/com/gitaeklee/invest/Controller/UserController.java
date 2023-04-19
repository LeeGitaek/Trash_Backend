package com.gitaeklee.invest.Controller;

import com.gitaeklee.invest.domain.entity.Address;
import com.gitaeklee.invest.domain.entity.User;
import com.gitaeklee.invest.repository.dto.request.SignupRequest;
import com.gitaeklee.invest.repository.dto.UserDto;
import com.gitaeklee.invest.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/api/v2/user/join")
    public CreateUserResponse saveUserV2(@RequestBody @Validated SignupRequest request) {
        User user = new User();
        user.setUserName(request.getUsername());
        user.setUserEmail(request.getEmail());
        user.setPassword(request.getPassword());

        Long id = userService.join(user);
        return new CreateUserResponse(id);
    }

    @Data
    @AllArgsConstructor
    static class CreateUserResponse {
        private Long id;
    }

    @Data
    @AllArgsConstructor
    static class UserResponse {
        private Long id;
    }

    // sign in
    @PostMapping("/api/v2/login")
    public UserDto login(@RequestBody @Validated UserLoginRequest request) {
        User user = userService.login(request.userEmail, request.userPassword);
        return new UserDto(user);
    }

    @GetMapping("/api/v2/user/{id}")
    public UserDto findUserV2(@PathVariable("id") Long id) {
        User user = userService.findOne(id);
        UserDto findUser = new UserDto(user);
        return findUser;
    }

    // 109ms
    @GetMapping("/api/v2/users")
    public List<UserDto> usersV2() {
        List<User> users = userService.findUsers();
        List<UserDto> findUsers = users.stream()
                .map(o -> new UserDto(o))
                .collect(Collectors.toList());

        return findUsers;
    }

    @Data
    @AllArgsConstructor
    static class UserDataRequest {
        private String userName;
    }

    // 344ms
    @GetMapping("/api/v2/user")
    public UserDto findUserByName(@RequestParam(value = "name") String userName) {
        User user = userService.findUserByName(userName);
        UserDto findUser = new UserDto(user);
        return findUser;
    }

    /* update user name */
    @Data
    static class UpdateUserRequest {
        private Long userId;
        private String name;
    }

    @PostMapping("/api/v2/user/update/name")
    public UserDto updateUserName(@RequestBody @Validated UpdateUserRequest request) {
        userService.updateUserName(request.userId, request.name);
        User user = userService.findOne(request.userId);
        return new UserDto(user);
    }

    /* update user email */
    @Data
    static class UpdateUserEmailRequest {
        private Long userId;
        private String userEmail;
    }

    @PostMapping("/api/v2/user/update/email")
    public UserDto updateUserEmail(@RequestBody @Validated UpdateUserEmailRequest request) {
        userService.updateUserEmail(request.userId, request.userEmail);
        User user = userService.findOne(request.userId);
        return new UserDto(user);
    }

    /* update user phone */
    @Data
    static class UpdateUserPhoneRequest {
        private Long userId;
        private String userPhone;
    }

    @PostMapping("/api/v2/user/update/phone")
    public UserDto updateUserPhone(@RequestBody @Validated UpdateUserPhoneRequest request) {
        userService.updateUserPhone(request.userId, request.userPhone);
        User user = userService.findOne(request.userId);
        return new UserDto(user);
    }

    /* update user password */
    @Data
    static class UpdateUserPwdRequest {
        private Long userId;
        private String userPwd;
        private String userToken;
    }

    @PostMapping("/api/v2/user/update/pwd")
    public UserDto updateUserPwd(@RequestBody @Validated UpdateUserPwdRequest request) {
        userService.updateUserPassword(request.userId, request.userPwd, request.userToken);
        User user = userService.findUserByToken(request.userId, request.userToken);
        return new UserDto(user);
    }

    /* update user address */
    @Data
    static class UpdateUserAddressRequest {
        private Long userId;
        private Address userAddress;
    }

    @PostMapping("/api/v2/user/update/addr")
    public UserDto updateUserAddr(@RequestBody @Validated UpdateUserAddressRequest request) {
        userService.updateUserAddress(request.userId, request.userAddress);
        User user = userService.findOne(request.userId);
        return new UserDto(user);
    }

    /* update user profile image */
    /* TODO: PROFILE IMAGE UPLOAD TO S3 ON AWS */
    @Data
    static class UpdateUserProfileRequest {
        private Long userId;
        private String userProfile;
    }

    @PostMapping("/api/v2/user/update/profile")
    public UserDto updateUserProfile(@RequestBody @Validated UpdateUserProfileRequest request) {
        userService.updateUserProfile(request.userId, request.userProfile);
        User user = userService.findOne(request.userId);
        return new UserDto(user);
    }

    /* update user profile introduce */
    @Data
    static class UpdateUserIntroRequest {
        private Long userId;
        private String userIntro;
    }

    @PostMapping("/api/v2/user/update/intro")
    public UserDto updateUserIntro(@RequestBody @Validated UpdateUserIntroRequest request) {
        userService.updateUserInfo(request.userId, request.userIntro);
        User user = userService.findOne(request.userId);
        return new UserDto(user);
    }

    /* update user percent */
    @Data
    @AllArgsConstructor
    static class UpdateUserPercentRequest {
        private Long userId;
        private Float trustPercent;
    }

    @PostMapping("/api/v2/user/update/trust")
    public UserDto updateUserTrust(@RequestBody @Validated UpdateUserPercentRequest request) {
        userService.updatePercent(request.userId, request.trustPercent);
        User user = userService.findOne(request.userId);
        return new UserDto(user);
    }

    @Data
    @AllArgsConstructor
    static class UserLoginRequest {
        private String userEmail;
        private String userPassword;
    }

    /* update user crew membership...
    * TODO: USER CREW MEMBERSHIP CHECK
    * TODO: USER LOGIN
    * TODO: USER FIND PASSWORD
    * */


}
