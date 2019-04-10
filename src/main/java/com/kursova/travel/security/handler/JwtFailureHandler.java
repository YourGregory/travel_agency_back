package com.kursova.travel.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kursova.travel.config.HostConfig;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@AllArgsConstructor
public class JwtFailureHandler implements AuthenticationFailureHandler {

    ObjectMapper objectMapper;
    HostConfig hostConfig;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException e) throws IOException {
        String origin = new ServletServerHttpRequest(request).getHeaders().getOrigin();
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);

        boolean isValid = Arrays.asList(hostConfig.getAllowedCorsOrigins()).contains(origin);

        if (isValid) {
            response.addHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, origin);
        }

        final ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(objectMapper.writeValueAsBytes(e.getMessage()));
        outputStream.flush();
        outputStream.close();
    }
}
