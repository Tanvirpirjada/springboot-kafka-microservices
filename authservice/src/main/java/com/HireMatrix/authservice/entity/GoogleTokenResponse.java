package com.HireMatrix.authservice.entity;

public record GoogleTokenResponse(
        String access_token,
        String id_token,
        String refresh_token,
        Integer expires_in,
        String token_type
) {}

