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

        // 해당 입력한 id와 일치하는 유저가 존재시, 해당 유저의 비밀번호와 대조
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
        // 메인화면으로 넘어갈 시 auth의 정보를 삽입
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
            // 만약 사용자가 입력한 비밀번호가 실제 비밀번호와 일치하면
            if(passwordEncoder.matches(currentPassword, user.getPassword())){

                // 새로운 비밀번호로 유저 정보 업데이트
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

            // 만약 사용자가 입력한 비밀번호가 실제 비밀번호와 일치하면
            if(passwordEncoder.matches(password, user.getPassword())){

                // 전화번호 업데이트
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
