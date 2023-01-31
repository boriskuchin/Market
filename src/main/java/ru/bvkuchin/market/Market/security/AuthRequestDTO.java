package ru.bvkuchin.market.Market.security;

import lombok.Getter;

@Getter
public class AuthRequestDTO {
    private String username;
    private String password;
}
