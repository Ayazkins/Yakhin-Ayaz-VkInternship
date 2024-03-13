package org.example.vkinternship.authentification.service;

import lombok.RequiredArgsConstructor;
import org.example.vkinternship.authentification.AuthRequest;
import org.example.vkinternship.authentification.AuthResponse;
import org.example.vkinternship.authentification.RegisterRequest;
import org.example.vkinternship.authentification.RegisterResponse;
import org.example.vkinternship.models.MyUserDetails;
import org.example.vkinternship.repository.UserRepository;
import org.example.vkinternship.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public RegisterResponse register(RegisterRequest request) {
        MyUserDetails user = MyUserDetails
                .builder()
                .name(request.name())
                .password(passwordEncoder.encode(request.password()))
                .role(request.role())
                .build();
        userRepository.save(user);
        String token = jwtService.generateToken(user);
        return new RegisterResponse(token);
    }

    public AuthResponse auth(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.name(),
                        request.password()
                )
        );
        MyUserDetails user = userRepository.findByName(request.name()).orElseThrow();
        String token = jwtService.generateToken(user);
        return new AuthResponse(token);

    }


}
