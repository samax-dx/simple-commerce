package com.samax.simpleCommerce.session.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.samax.simpleCommerce.session.repository.SessionEntryRepository;
import com.samax.simpleCommerce.session.util.HttpSessionUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ScSessionStorageTest {

    @Mock
    HttpServletRequest request;

    @Mock
    Authentication authentication;

    @Mock
    HttpSessionUtil httpSessionUtil;

    @Spy
    ObjectMapper objectMapper = new ObjectMapper();

    @Mock
    SessionEntryRepository sessionEntryRepository;

    @InjectMocks
    ScSessionStorage sessionStorage;

    @Test
    void put() {
        when(httpSessionUtil.getSessionId(request));
        String userId = httpSessionUtil.getUserId(authentication);
        assertDoesNotThrow(() -> sessionStorage.put("cart", Map.of(1, "one", 2, "two", 3, "three")));
    }
}