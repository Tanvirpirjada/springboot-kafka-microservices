package com.HireMatrix.authservice.service;

import com.HireMatrix.authservice.config.JwtUtils;
import com.HireMatrix.authservice.config.SuccessResponse;
import com.HireMatrix.authservice.entity.GoogleTokenResponse;
import com.HireMatrix.authservice.entity.User;
import com.HireMatrix.authservice.misc.AESEncryptionService;
import com.HireMatrix.authservice.model.LoginModel;
import com.HireMatrix.authservice.model.LoginReponseModel;
import com.HireMatrix.authservice.model.SignUpModel;
import com.HireMatrix.authservice.repo.UserRepo;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;


import java.util.Base64;
import java.util.Optional;

@Service
public class LoginService {

    private final UserRepo userRepo;

    private final AESEncryptionService aesEncryptionService;

    private final JwtUtils jwtUtils;

    private final String googleClientId;

    private final String googleRedirectUri;


    private final String googleClientSecret;

    @Autowired
    public LoginService(UserRepo userRepo, AESEncryptionService aesEncryptionService, JwtUtils jwtUtils, @Value("${googleClientId}") String googleClientId,  @Value("${googleRedirectUri}") String googleRedirectUri,  @Value("${googleClientSecret}") String googleClientSecret){
        this.userRepo = userRepo;
        this.aesEncryptionService = aesEncryptionService;
        this.jwtUtils = jwtUtils;
        this.googleClientId = googleClientId;
        this.googleRedirectUri = googleRedirectUri;
        this.googleClientSecret = googleClientSecret;
    }


    public SuccessResponse signUp(SignUpModel model) {
        try {
            Optional<User> user=userRepo.findByEmail(aesEncryptionService.encrypt(model.email()));
            if(user.isPresent()){
                throw new RuntimeException("Email Alreayd Exists");
            }
            User newUser=new User(null,model.name(),(aesEncryptionService.encrypt(model.email())),model.password(),model.type());
            userRepo.save(newUser);
            return new SuccessResponse("SUCCESS","User  SuccessFully SignUp");
        }catch (Exception e){
            throw new RuntimeException("SignUp failed", e);
        }
    }


    public LoginReponseModel login(LoginModel model) {
        try{
        Optional<User> user=userRepo.findByEmail(aesEncryptionService.encrypt(model.email()));
        if(user.isPresent()){
            return new LoginReponseModel(user.get().getName(), jwtUtils.generateToken(user.get()), jwtUtils.generateRefreshToken(user.get()));
        }else{
            return null;
        }
        }catch (Exception e){
            throw new RuntimeException("Login Error",e);
        }
    }

    public LoginReponseModel googleLogin(String code) {
        GoogleTokenResponse response=exchangeCodeForToken(code);
        String  email=extractEmailFromIdToken(response.id_token());
        try{
            Optional<User> user=userRepo.findByEmail(aesEncryptionService.encrypt(email));
            if(user.isPresent()){
                return new LoginReponseModel(user.get().getName(), jwtUtils.generateToken(user.get()), jwtUtils.generateRefreshToken(user.get()));
            }else{
                return null;
            }
        }catch (Exception e){
            throw new RuntimeException("Login Error",e);
        }
    }


    public GoogleTokenResponse exchangeCodeForToken(String code) {

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("code", code);
        body.add("client_id", googleClientId);
        body.add("client_secret", googleClientSecret);
        body.add("redirect_uri", googleRedirectUri);
        body.add("grant_type", "authorization_code");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> request =
                new HttpEntity<>(body, headers);

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<GoogleTokenResponse> response =
                restTemplate.postForEntity(
                        "https://oauth2.googleapis.com/token",
                        request,
                        GoogleTokenResponse.class
                );

        return response.getBody();
    }


    public String extractEmailFromIdToken(String idToken) {
        String[] parts = idToken.split("\\.");
        String payload = new String(
                Base64.getUrlDecoder().decode(parts[1])
        );

        JSONObject json = new JSONObject(payload);
        return json.getString("email");
    }

}
