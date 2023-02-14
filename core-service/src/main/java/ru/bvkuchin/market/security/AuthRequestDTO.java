package ru.bvkuchin.market.security;

import lombok.Getter;

@Getter
public class AuthRequestDTO {
    private String username;
    private String password;
}
