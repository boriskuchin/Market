package ru.bvkuchin.authservice.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.bvkuchin.authservice.entities.User;
import ru.bvkuchin.authservice.services.UserService;
import ru.bvkuchin.authservice.utils.JwtService;
import ru.bvkuchin.intergation.dtos.AuthRequestDTO;
import ru.bvkuchin.intergation.dtos.AuthResponseDTO;
import ru.bvkuchin.intergation.dtos.PrincipalDto;
import ru.bvkuchin.intergation.dtos.ResourceNotFoundException;

@RestController
@RequestMapping("/api/v1/auth")
@Slf4j
@RequiredArgsConstructor
@CrossOrigin("*")
public class AuthController {
    private final UserService userService;

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    @PostMapping("/token")
    public AuthResponseDTO getToken(@RequestBody AuthRequestDTO requestDTO) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestDTO.getUsername(), requestDTO.getPassword()));

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = jwtService.generateToken(userDetails);

        return new AuthResponseDTO(token);
    }

    @GetMapping("/user-info/{username}")
    public PrincipalDto getName(@PathVariable String username) {
        PrincipalDto principalDto = new PrincipalDto();
        principalDto.setName(username);
        User u = userService.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        principalDto.setId(u.getId());
        System.out.println(u.getId() + "   " +  username);
        return principalDto;
    }
}
