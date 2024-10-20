package com.edivan.biblioteca.model;

import lombok.Getter;

@Getter
public class AuthResponse {
	private String token;
	private String role;

    public AuthResponse(String token, String role) {
        this.token = token;
        this.role = role;
    }

}
