package com.armorfeed.api.shipments.security;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.Setter;

@Component
@Scope("singleton")
public class FeignRequestInterceptor implements RequestInterceptor {
    @Setter
    private String bearerToken;

    @Override
    public void apply(RequestTemplate template) {
        String feignClientName = template.feignTarget().name();
        if("users-service".equals(feignClientName)) {
            return;
        }
        template.header("Authorization", bearerToken);
    }
    
}
