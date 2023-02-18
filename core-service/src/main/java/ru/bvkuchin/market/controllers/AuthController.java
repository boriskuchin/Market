package ru.bvkuchin.market.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.bvkuchin.intergation.dtos.PrincipalDto;
import ru.bvkuchin.intergation.dtos.ResourceNotFoundException;
import ru.bvkuchin.market.entities.User;
import ru.bvkuchin.market.security.AuthRequestDTO;
import ru.bvkuchin.market.security.AuthResponseDTO;
import ru.bvkuchin.market.security.JwtService;
import ru.bvkuchin.market.services.UserService;

import java.security.Principal;

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
