package com.armorfeed.api.shipments.security;

import com.armorfeed.api.shipments.providers.feignclients.UsersServiceFeignClient;
import com.armorfeed.api.shipments.providers.feignclients.dtos.AuthTokenResponse;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TokenInterceptor implements HandlerInterceptor {

    @Autowired
    private UsersServiceFeignClient usersServiceFeignClient;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if(request.getRequestURI().startsWith("/swagger-ui/")) {
            return true;
        }
        if (!isValidToken(request)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
                
        return true;
    }

    enum AuthTokenMessage {
        SUCCESS("Sucessfull authentication");
        private String message;
        private AuthTokenMessage(String message) {
            this.message = message;
        }
        public String getMessage() {
            return this.message;
        }
    }

    private boolean isValidToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);
            AuthTokenResponse authTokenResponse = usersServiceFeignClient.validateToken(token);
            return authTokenResponse.isValidToken() || authTokenResponse.getMessage().equals(AuthTokenMessage.SUCCESS.getMessage());
        }
        return false; // Token no válido o ausente
    }
    
}
