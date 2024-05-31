package com.Blogging.Platform.Blog.Auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Blogging.Platform.Blog.Entity.User;
import com.Blogging.Platform.Blog.Model.AuthenticationRequest;
import com.Blogging.Platform.Blog.Model.RegisterRequest;
import com.Blogging.Platform.Blog.Repository.UserRepository;
import com.Blogging.Platform.Blog.Service.JwtService;

import lombok.*;

@Service
@RequiredArgsConstructor
public class AuthService {
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final JwtService jwtService;
    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest registerRequest) {

        var user = User.builder()
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(registerRequest.getRole())
                .build();
        var savedUser = userRepository.save(user);
        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().accessToken(jwtToken).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        // first step
        // we need to validate out request (validate whether password & username is
        // correct)
        // verify whether user present in the database
        // which AuthenticataionProvider -> DaoAuthenticationProvider(Inject)
        // we need to authenticate using authenicationManager injecting this
        // authenticationProvider
        // second step
        // verify whether username and password is correct =>
        // UserNamePasswordAuthenticationToken
        // verify whether user present in db
        // generateToken
        // return the token

        // authenticationManager.authenticate(new
        // UsernamePasswordAuthenticationToken(request.getEmail(),
        // request.getPassword()));
        // var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        // String jwtToken = jwtService.generateToken(user);
        // return AuthenticationResponse.builder().accessToken(jwtToken).build();

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()));

        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().accessToken(jwtToken).build();
    }

}
