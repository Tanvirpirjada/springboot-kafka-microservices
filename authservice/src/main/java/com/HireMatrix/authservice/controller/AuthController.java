package com.HireMatrix.authservice.controller;

import com.HireMatrix.authservice.config.ErrorResponse;
import com.HireMatrix.authservice.config.JwtUtils;
import com.HireMatrix.authservice.config.SuccessResponse;
import com.HireMatrix.authservice.entity.USERTYPE;
import com.HireMatrix.authservice.entity.User;
import com.HireMatrix.authservice.model.LoginModel;
import com.HireMatrix.authservice.model.LoginReponseModel;
import com.HireMatrix.authservice.model.SignUpModel;
import com.HireMatrix.authservice.service.LoginService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account/oauth")
public class AuthController {

    @Autowired
    private JwtUtils utils;

    private final LoginService loginService;

    @Autowired
    public AuthController(LoginService loginService){
        this.loginService = loginService;
    }


    @PostMapping
    public ResponseEntity<SuccessResponse> signUp(@RequestBody SignUpModel model){
       return ResponseEntity.ok(loginService.signUp(model));

    }

    @PostMapping("login")
    public ResponseEntity<LoginReponseModel> login(@RequestBody LoginModel model){
        return ResponseEntity.ok(loginService.login(model));
    }


    //This one willbe in used when we will use the google for login
    @PostMapping("google/login")
    public ResponseEntity<LoginReponseModel> googleLogin(@RequestParam String code){
  System.out.println(code);
       return ResponseEntity.ok(loginService.googleLogin(code));
    }


    //this is an testing account to get the accessToken and this ine will contians the userIdm email and user type
    @GetMapping
    public String returnToken() {
        User user = new User(1, "tanvir", "tavir@gmail.com", "password",USERTYPE.USER);
        return utils.generateToken(user);
    }
}
