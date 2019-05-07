package com.example.edubookapp.controller.androidapi;

import com.example.edubookapp.model.User;
import com.example.edubookapp.payload.ApiResponse;
import com.example.edubookapp.payload.JwtAuthenticationResponse;
import com.example.edubookapp.payload.LoginRequest;
import com.example.edubookapp.payload.SignUpRequest;
import com.example.edubookapp.security.JwtTokenProvider;
import com.example.edubookapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/auth")
public class AuthApi {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;
    @Autowired
    private JwtTokenProvider tokenProvider;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);

            return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @GetMapping("/logout")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void logoutUser(HttpSession session) {
        session.invalidate();
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest){
        if (userService.existsByUsername(signUpRequest.getUsername())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(false, "Username is already taken!"));
        }
        if (userService.existsByEmail(signUpRequest.getEmail())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(false, "Email Address already in use!"));

        }
        if (!signUpRequest.getConfirmPassword().equalsIgnoreCase(signUpRequest.getPassword())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(false, "Confirm Password is not match"));

        }
        User user = new User();
        user.setUsername(signUpRequest.getUsername());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(signUpRequest.getPassword());
        User result = userService.register(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/user/{id}")
                .buildAndExpand(result.getId()).toUri();
        System.out.println(location);
        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
    }
}
