package com.kursova.travel.service.web;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.common.collect.Iterables;
import com.kursova.travel.config.SecurityPropertiesConfig;
import com.kursova.travel.entity.dictionary.ApplicationType;
import com.kursova.travel.entity.dictionary.UserRole;
import com.kursova.travel.security.SystemUser;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.env.Environment;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JwtWebService {

    static final String ISSUER = "SECRET";

    Algorithm algorithm;

    SecurityPropertiesConfig securityPropertiesConfig;

    public JwtWebService(SecurityPropertiesConfig securityPropertiesConfig) {
        this.securityPropertiesConfig = securityPropertiesConfig;
    }

    /**
     * Method actually is not deprecated, it is just a marker that I want you not to use this method.
     */
    @Deprecated
    @PostConstruct
    public void init() {
        algorithm = Algorithm.HMAC512(securityPropertiesConfig.getJwt().getPrivateKey());
    }

    public Boolean isTokenValid(@NonNull final String token, @NonNull final String applicationType) {
        boolean isValid = true;

        try {
            DecodedJWT decoded = JwtService.verifyJWT(token, algorithm, getIssuer());
            boolean isUserTypeAcceptableToApplicationType = false;
            String roleFromToken = getRoleFromToken(decoded);

            if (ApplicationType.isAdmin(applicationType)) {
                isUserTypeAcceptableToApplicationType = UserRole.isAdmin(roleFromToken);
            } else if (ApplicationType.isTourist(applicationType)) {
                isUserTypeAcceptableToApplicationType = BooleanUtils.isFalse(
                        UserRole.isAdmin(roleFromToken) || UserRole.isSportsman(roleFromToken)
                );
            }

            isValid = isUserTypeAcceptableToApplicationType;
        } catch (JWTVerificationException e) {
            /* Do nothing */
        }

        return isValid;
    }

    public SystemUser getSystemUser(@NonNull final String token) {
        DecodedJWT decoded = JwtService.decodeJWT(token);
        return getSystemUser(decoded);
    }

    public String generateToken(SystemUser systemUser) {
        GrantedAuthority authority = Iterables.getFirst(systemUser.getAuthorities(), null);
        return JwtService.generateJWT(systemUser.getUsername(), authority.getAuthority(), algorithm, getIssuer());
    }

    public Boolean isTokenAboutToExpire(@NonNull final String token) {
        boolean result;

        if (BooleanUtils.isFalse(isTokenExpired(token))) {
            DecodedJWT decodedJWT = JwtService.decodeJWT(token);
            LocalDateTime expiresAt = LocalDateTime.ofInstant(decodedJWT.getExpiresAt().toInstant(), ZoneId.systemDefault());
            result = LocalDateTime.now().plusMinutes(10L).isAfter(expiresAt);
        } else {
            result = true;
        }

        return result;
    }

    public String getIssuer() {
        return ISSUER;
    }

    private Boolean isTokenExpired(@NonNull final String token) {
        boolean isValidAndExpired = false;

        try {
            JwtService.verifyJWT(token, algorithm, getIssuer());
        } catch (TokenExpiredException ex) {
            isValidAndExpired = true;
        }

        return isValidAndExpired;
    }

    private String getRoleFromToken(final DecodedJWT decodedToken) {
        Claim claim = decodedToken.getClaim(JWTClaim.ROLE.name());
        return claim.asString();
    }

    private String getRoleFromToken(final String token) {
        DecodedJWT decodedToken = JwtService.decodeJWT(token);
        return getRoleFromToken(decodedToken);
    }

    private SystemUser getSystemUser(DecodedJWT decodedJWT) {
        String role = getRoleFromToken(decodedJWT);
        String username = getUsernameFromToken(decodedJWT);
        return new SystemUser(username, StringUtils.EMPTY, role);
    }

    private String getUsernameFromToken(DecodedJWT decodedJWT) {
        return decodedJWT.getSubject();
    }


    @FieldDefaults(level = AccessLevel.PRIVATE)
    private static final class JwtService {

        static DecodedJWT decodeJWT(final String token) {
            return JWT.decode(token);
        }

        static DecodedJWT verifyJWT(@NonNull final String token,
                                    @NonNull final Algorithm algorithm,
                                    @NonNull final String issuer) {
            return JWT
                    .require(algorithm)
                    .withIssuer(issuer)
                    .build()
                    .verify(token);
        }

        static String generateJWT(@NonNull final String username,
                                  @NonNull final String role,
                                  @NonNull final Algorithm algorithm,
                                  @NonNull final String issuer) {
            LocalDateTime now = LocalDateTime.now();

            return JWT
                    .create()
                    .withSubject(username)
                    .withIssuedAt(Date.from(now.atZone(ZoneId.systemDefault()).toInstant()))
                    .withIssuer(issuer)
                    .withExpiresAt(Date.from(now.plusDays(4L).atZone(ZoneId.systemDefault()).toInstant()))
                    .withClaim(JWTClaim.ROLE.name(), role)
                    .sign(algorithm);
        }

    }

    /**
     * Additional properties that can be added to JWT.
     */
    public enum JWTClaim {
        ROLE
    }
}
