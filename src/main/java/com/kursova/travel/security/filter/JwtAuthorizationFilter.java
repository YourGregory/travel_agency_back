package com.kursova.travel.security.filter;

import com.kursova.travel.security.SystemUser;
import com.kursova.travel.security.exception.InvalidTokenException;
import com.kursova.travel.service.PermissionService;
import com.kursova.travel.service.web.JwtWebService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.kursova.travel.security.dictionary.SecurityConstants.APPLICATION_TYPE;
import static com.kursova.travel.security.dictionary.SecurityConstants.AUTHORIZATION_TOKEN;

@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JwtAuthorizationFilter extends AbstractAuthenticationProcessingFilter {

    JwtWebService jwtWebService;
    PermissionService permissionService;

    public JwtAuthorizationFilter(JwtWebService jwtWebService, PermissionService permissionService,
                                  AuthenticationSuccessHandler successHandler, AuthenticationFailureHandler failureHandler) {
        super("/whatever"); // Just a stub
        this.jwtWebService = jwtWebService;
        this.permissionService = permissionService;
        setAuthenticationSuccessHandler(successHandler);
        setAuthenticationFailureHandler(failureHandler);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        Authentication authentication;
        String authorizationToken = request.getHeader(AUTHORIZATION_TOKEN);
        String applicationType = request.getHeader(APPLICATION_TYPE);

        if (StringUtils.isNotBlank(authorizationToken) && StringUtils.isNotBlank(applicationType)) {
            if (jwtWebService.isTokenValid(authorizationToken, applicationType)) {
                SystemUser systemUser = jwtWebService.getSystemUser(authorizationToken);
                permissionService.canBeAuthorized(systemUser);


                authentication = new UsernamePasswordAuthenticationToken(
                        systemUser, StringUtils.EMPTY, systemUser.getAuthorities()
                );
            } else {
                throw new InvalidTokenException("Not valid token provided");
            }
        } else {
            throw new InvalidTokenException("Received blank token");
        }

        return authentication;
    }

    @Override
    protected boolean requiresAuthentication(HttpServletRequest request, HttpServletResponse response) {
        return request.getRequestURI().matches("^.*api.*$");
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) throws IOException, ServletException {
        SecurityContextHolder.getContext().setAuthentication(authResult);
        if (log.isDebugEnabled()) {
            log.debug("User {} has been authorized.", authResult.getName());
        }
        chain.doFilter(request, response);
    }
}

