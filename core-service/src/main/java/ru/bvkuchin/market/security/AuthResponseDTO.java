package ru.bvkuchin.market.security;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class AuthResponseDTO {

    private String token;

}
