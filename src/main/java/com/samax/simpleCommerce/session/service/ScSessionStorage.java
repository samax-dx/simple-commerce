package com.samax.simpleCommerce.session.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.samax.simpleCommerce.session.model.SessionEntry;
import com.samax.simpleCommerce.session.repository.SessionEntryRepository;
import com.samax.simpleCommerce.session.util.HttpSessionUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import java.util.List;
import java.util.Objects;


@Service
@RequestScope
@RequiredArgsConstructor
public class ScSessionStorage {

    private final HttpServletRequest request;

    private final Authentication authentication;

    private final SessionEntryRepository sessionEntryRepository;

    private final HttpSessionUtil httpSessionUtil = new HttpSessionUtil();

    private final ObjectMapper objectMapper = new ObjectMapper();


    public <T> void putList(String key, List<T> values) {
        List<SessionEntry> sessionEntries = values.stream()
                .map(value -> createSessionEntry(key, value))
                .filter(Objects::nonNull).toList();
        sessionEntryRepository.saveAll(sessionEntries);
    }

    public <T> void put(String key, T value) {
        SessionEntry sessionEntry = createSessionEntry(key, value);
        if (sessionEntry != null) sessionEntryRepository.save(sessionEntry);
    }

    private <T> SessionEntry createSessionEntry(String key, T value) {
        String sessionId = httpSessionUtil.getSessionId(request);
        String userId = httpSessionUtil.getUserId(authentication);

        if (sessionId == null && userId == null) return null;

        try {
            SessionEntry sessionEntry = new SessionEntry();
            sessionEntry.setSessionId(sessionId);
            sessionEntry.setUserId(userId);
            sessionEntry.setItemKey(key);
            sessionEntry.setItemValue(objectMapper.writeValueAsString(value));
            return sessionEntry;
        } catch (JsonProcessingException e) {
            return null;
        }
    }

}
