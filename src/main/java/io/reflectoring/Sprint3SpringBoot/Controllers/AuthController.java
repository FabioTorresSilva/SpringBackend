package io.reflectoring.Sprint3SpringBoot.Controllers;

import io.reflectoring.Sprint3SpringBoot.Dto.SigninDto;
import io.reflectoring.Sprint3SpringBoot.Models.User;
import io.reflectoring.Sprint3SpringBoot.JWT.JwtUtil;
import io.reflectoring.Sprint3SpringBoot.Repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    //@Autowired
    AuthenticationManager authenticationManager;

    //@Autowired
    UserRepository userRepository;

    //@Autowired
    PasswordEncoder encoder;

    //@Autowired
    JwtUtil jwtUtils;

    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository, PasswordEncoder encoder, JwtUtil jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/signin")
    public SigninDto authenticateUser(@RequestBody User user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getEmail(),
                        user.getPassword()
                )
        );
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User us = userRepository.findUserByEmail(user.getEmail());
        return new SigninDto(us.getId(), us.getRole(),jwtUtils.generateToken(userDetails.getUsername()));
    }

    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: Email is already taken!");
        }
        // Create new user's account
        User newUser = new User(
                user.getName(),
                user.getEmail(),
                encoder.encode(user.getPassword()),
                user.getRole()
        );
        userRepository.save(newUser);
        return ResponseEntity.status(HttpStatus.OK).body("User registered successfully!");
    }
}