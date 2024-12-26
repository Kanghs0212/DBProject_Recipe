package com.example.demo.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.Optional;


@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    @GetMapping("/login")
    String login(){
        return "login.html";
    }


    @PostMapping("/login")
    String loginCheck(String uid, String password){
        Optional<User> result = userService.findById(uid);

        if(result.isPresent()){
            User user = result.get();

            if(user.getPassword().equals(password)){
                return "redirect:/index";
            }
            else{
                return "redirect:/login";
            }
        }
        else{
            return "redirect:/login";
        }
    }

    @GetMapping("/index")
    String index(Authentication auth, Model model){
        model.addAttribute(auth);
        return "index.html";
    }

    @GetMapping("/register")
    String register(){ return "register.html"; }

    @PostMapping("register")
    String signUp(String id, String password, String phoneNumber){
        boolean flag = userService.saveUser(id, password, phoneNumber);

        return "redirect:/login";
    }

    @GetMapping("/mypage")
    String myPage(){

        return "mypage.html";
    }

    @PostMapping("/updatePassword")
    String updatePassword(Model model, Authentication auth,  String currentPassword, String newPassword){
        Optional<User> result = userService.findById(auth.getName());

        if(result.isPresent()){
            User user = result.get();
            if(passwordEncoder.matches(currentPassword, user.getPassword())){

                userService.updateUserPassword(newPassword, user);

                model.addAttribute("message", "비밀번호가 변경되었습니다.");
            }
            else{
                model.addAttribute("message", "현재 비밀번호가 틀립니다.");
            }
        }
        else{
            model.addAttribute("message", "현재 비밀번호가 틀립니다.");
        }

        return "alert.html";

    }
    @PostMapping("/updatePhone")
    String updatePhone(Model model, Authentication auth,  String password, String newPhone){
        Optional<User> result = userService.findById(auth.getName());

        if(result.isPresent()){
            User user = result.get();

            if(passwordEncoder.matches(password, user.getPassword())){

                userService.updateUserPhoneNumber(newPhone, user);

                model.addAttribute("message", "전화번호가 변경되었습니다.");
            }
            else{
                model.addAttribute("message", "현재 비밀번호가 틀립니다.");
            }
        }
        else{
            model.addAttribute("message", "현재 비밀번호가 틀립니다.");
        }

        return "alert.html";

    }

    @PostMapping("/deleteAccount")
    String deleteAccount(Model model, Authentication auth){
        Optional<User> result = userService.findById(auth.getName());

        if(result.isPresent()){
            User user = result.get();
            userService.deleteById(user.getId());
            return "redirect:/logout";
        }

        return "redirect:/account";

    }
}
