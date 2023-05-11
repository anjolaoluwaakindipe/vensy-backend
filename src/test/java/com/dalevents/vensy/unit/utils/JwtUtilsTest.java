package com.dalevents.vensy.unit.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.dalevents.vensy.common.utils.JwtUtils;
import com.dalevents.vensy.models.AppUser;
import com.dalevents.vensy.models.enums.Role;

public class JwtUtilsTest {
    private JwtUtils jwtUtils;
    private AppUser appUser;

    @BeforeEach
    void setup() {
        jwtUtils = new JwtUtils("asdfasdfa", "asdfasdfa", "asdfsfas");
        appUser = AppUser.builder().firstname("Test").lastname("Test").role(Role.COMPANY).password("TestPassword").email("test@gmail.com")
                .build();
    }

    @Test
    public void shouldGenerateAccessToken() {
        String token = jwtUtils.generateAccessToken(appUser);
        System.out.println(token);
        assertNotNull(token);
    }

    @Test
    public void shouldGenerateRefreshToken() {
        String token = jwtUtils.generateRefreshToken(appUser);
        System.out.println(token);
        assertNotNull(token);
    }

    @Test
    public void shouldGiveBackTokenSubjectFromAccessToken() {
        String accessToken = jwtUtils.generateAccessToken(appUser);
        String username = jwtUtils.getUsernameFromAccessToken(accessToken);
        System.out.println(username);
        assertEquals(appUser.getEmail(), username);
    }

    @Test
    public void shouldGiveBackTokenSubjectFromRefreshToken() {
        String refreshToken = jwtUtils.generateAccessToken(appUser);
        String username = jwtUtils.getUsernameFromRefreshToken(refreshToken);
        System.out.println(username);
        assertEquals(appUser.getEmail(), username);
    }

    @Test
    public void shouldGiveExpirationDateFromAccessToken() {
        String accessToken = jwtUtils.generateAccessToken(appUser);
        Date expiresAt = jwtUtils.getExpirationDateFromAccessToken(accessToken);
        System.out.println(expiresAt);
        System.out.println(new Date(System.currentTimeMillis() + 60 * 10 * 1000));
    }

}