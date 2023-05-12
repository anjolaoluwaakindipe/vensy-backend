package com.dalevents.vensy.unit.utils;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.dalevents.vensy.common.utils.JwtUtils;
import com.dalevents.vensy.models.AppUser;
import com.dalevents.vensy.models.enums.Role;

public class JwtUtilsTest {
    private JwtUtils jwtUtils;
    private AppUser appUser;
    private String testAccessToken;
    private String testRefreshToken;

    @BeforeEach
    void setup() {
        jwtUtils = new JwtUtils("asdfasdfa", "asdfasdfa", "asdfsfas");
        appUser = AppUser.builder().firstname("Test").lastname("Test").role(Role.COMPANY).password("TestPassword").email("test@gmail.com")
                .build();
        testRefreshToken = jwtUtils.generateAccessToken(appUser);
        testAccessToken = jwtUtils.generateRefreshToken(appUser);

    }

    @Test
    public void shouldGenerateAccessToken() {
        String token = jwtUtils.generateAccessToken(appUser);
        assertNotNull(token);
    }

    @Test
    public void shouldGenerateRefreshToken() {
        String token = jwtUtils.generateRefreshToken(appUser);
        assertNotNull(token);
    }

    @Test
    public void shouldGiveBackTokenSubjectFromAccessToken() {
        String accessToken = jwtUtils.generateAccessToken(appUser);
        String username = jwtUtils.getUsernameFromAccessToken(accessToken);
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
        assertNotNull(expiresAt);
    }

    @Test 
    public void whenGivenANonExpiredAccessToken_returnTrue(){
        boolean result = jwtUtils.isAccessTokenExpired(testAccessToken);
        
        assertFalse(result);
    }

    @Test 
    public void whenGivenANonExpiredRefresToken_returnTrue(){
        boolean result = jwtUtils.isRefreshTokenExpired(testRefreshToken);
        
        assertFalse(result);
    }

    @Test 
    public void whenGivenAValidAccessToken_thenReturnRoles(){
        String[] result = jwtUtils.getRolesFromAccessToken(testAccessToken);

        assertArrayEquals(new String[]{Role.COMPANY.toString()}, result);
    }
    
    @Test 
    public void whenGivenAValidRefreshToken_thenReturnRoles(){
        String[] result = jwtUtils.getRolesFromRefreshToken(testRefreshToken);

        assertArrayEquals(new String[]{Role.COMPANY.toString()}, result);
    }


    @Test
    public void whenGivenAValidAccessToken_thenReturnTrue(){
        boolean result = jwtUtils.isAccessTokenValid(testAccessToken, appUser);
        assertTrue(result);
    }
    @Test
    public void whenGivenAValidRefreshToken_thenReturnTrue(){
        boolean result = jwtUtils.isRefreshTokenValid(testRefreshToken, appUser);
        assertTrue(result);
    }
}