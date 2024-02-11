package com.samax.simpleCommerce.session.service;

import com.samax.simpleCommerce.common.excption.ScHttpException;
import com.samax.simpleCommerce.session.model.SessionUserView;
import com.samax.simpleCommerce.session.repository.SessionEntryRepository;
import com.samax.simpleCommerce.session.util.HttpSessionUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Component
@RequiredArgsConstructor
public class SessionUserCheckFilter extends OncePerRequestFilter {

    private static final String MSG_UNAUTHORIZED_SESSION_ACCESS = "unauthorized session access";

    private final SessionEntryRepository sessionEntryRepository;

    private final HttpSessionUtil httpSessionUtil = new HttpSessionUtil();


    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        String sessionId = httpSessionUtil.getSessionId(request);

        if (sessionId == null || sessionId.isEmpty()) {
            filterChain.doFilter(request, response);
            return;
        }

        String sessionOwnerId = sessionEntryRepository.findTopBySessionId(sessionId).map(SessionUserView::getUserId)
                .orElse(null);

        if (sessionOwnerId == null) {
            filterChain.doFilter(request, response);
            return;
        }

        String authUserId = httpSessionUtil.getUserId(SecurityContextHolder.getContext().getAuthentication());

        if (!sessionOwnerId.equals(authUserId))
            throw new ScHttpException(HttpStatus.FORBIDDEN, MSG_UNAUTHORIZED_SESSION_ACCESS);

        filterChain.doFilter(request, response);
    }

}
