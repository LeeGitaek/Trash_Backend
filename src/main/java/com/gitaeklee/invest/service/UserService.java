package com.gitaeklee.invest.service;

import com.gitaeklee.invest.domain.entity.Address;
import com.gitaeklee.invest.domain.entity.User;
import com.gitaeklee.invest.repository.dto.request.SignupRequest;
import com.gitaeklee.invest.repository.repo.UserRepository;
import com.querydsl.core.Tuple;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    /* signup */
    @Transactional
    public Long join(User user) {
        validateDuplicateUser(user);
        userRepository.saveUser(user);
        return user.getId();
    }

    private void validateDuplicateUser(User user) {
        List<User> findMembers = userRepository.findByName(user.getUserName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    public User registerNewUser(SignupRequest signupRequest)  {
        if (userRepository.searchUserByName(signupRequest.getUsername()).isEmpty()) {
            throw new IllegalStateException("exist");
        }

        if (userRepository.findUserByUserEmail(signupRequest.getEmail()) == null) {
            throw new IllegalStateException("exist");
        }

        User user = new User();
        user.setUserName(signupRequest.getUsername());
        user.setUserEmail(signupRequest.getEmail());
        user.setPassword(signupRequest.getPassword());

        userRepository.saveUser(user);

        return user;
    }
    /* get users */
    public List<User> findUsers() {
        List<User> result = userRepository.findAll();
        return result;
    }

    /* get a user */
    public User findOne(Long userId) {
        return userRepository.findUser(userId);
    }

    public User findUserByName(String userName) {
        return userRepository.findUserByName(userName);
    }

    public User findUserByEmail(String userEmail) { return userRepository.findUserByUserEmail(userEmail); }
    public User findUserByToken(Long userId, String userToken) {
        return userRepository.findUserByToken(userId, userToken);
    }

    /* update user */
    @Transactional
    public void updateUserName(Long userId,String userName) {
        User user = userRepository.findUser(userId);
        user.setUserName(userName);
    }

    @Transactional
    public void updateUserEmail(Long userId, String userEmail) {
        User user = userRepository.findUser(userId);
        user.setUserEmail(userEmail);
    }

    @Transactional
    public void updateUserPassword(Long userId, String userPassword, String userToken) {
        User user = userRepository.findUserByToken(userId, userToken);
        user.setPassword(userPassword);
    }

    @Transactional
    public void updateUserPhone(Long userId, String userPhone) {
        User user = userRepository.findUser(userId);
        user.setPhone(userPhone);
    }

    @Transactional
    public void updateUserAddress(Long userId, Address userAddress) {
        User user = userRepository.findUser(userId);
        user.setAddress(userAddress);
    }

    @Transactional
    public void updateUserProfile(Long userId, String userProfile) {
        User user = userRepository.findUser(userId);
        user.setProfileImage(userProfile);
    }

    @Transactional
    public void updateUserInfo(Long userId, String userInfo) {
        User user = userRepository.findUser(userId);
        user.setProfileIntro(userInfo);
    }

    @Transactional
    public void updatePercent(Long userId, float percent) {
        User user = userRepository.findUser(userId);
        user.setTrustPercent(percent);
    }

    @Transactional
    public void updateCrewMember(Long userId) {
        // update crew member status if user has one payment history at least.
        User user = userRepository.findUser(userId);
        List<Tuple> paymentList = userRepository.payConfig(user.getId());
        user.setCrewMember(paymentList.size());
    }

    public User login(String userEmail, String userPassword) {
        User user = userRepository.findUserByUserEmail(userEmail);
        Boolean isPasswordMathes = (user.getPassword().equals(userPassword));
        if (!isPasswordMathes) {
            throw new IllegalStateException("password is wrong");
        }
        return user;
    }

}
