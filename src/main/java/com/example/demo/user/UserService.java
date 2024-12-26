package com.example.demo.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public Optional<User> findById(String uid){
        Optional<User> result = userRepository.findById(uid);
        return result;
    }

    public boolean saveUser(String id, String password, String phoneNumber){
        if(id.length()>=4 && password.length()>=8 && id.length()<=16){
            User user = new User();
            var pw = passwordEncoder.encode(password);
            user.setId(id);
            user.setPassword(pw);
            user.setPhoneNumber(phoneNumber);
            userRepository.save(user);

            return true;
        }else
            return false;
    }

    public void updateUserPassword(String password, User user){
        var newPassword = passwordEncoder.encode(password);
        user.setPassword(newPassword);
        userRepository.save(user);
    }

    public void updateUserPhoneNumber(String phoneNumber, User user){
        user.setPhoneNumber(phoneNumber);
        userRepository.save(user);
    }

    public void deleteById(String id){
        userRepository.deleteById(id);
    }

}
