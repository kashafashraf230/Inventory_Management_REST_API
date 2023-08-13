package com.example.inventorymanagementsystem.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;


@Provider
public class SecurityFilter implements ContainerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(SecurityFilter.class);
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String AUTHORIZATION_HEADER_PREFIX = "Basic";

    UserCrud user = new UserCrud();
    @Override
    public void filter(ContainerRequestContext containerRequestContext) {

        String authHeader = containerRequestContext.getHeaderString(AUTHORIZATION_HEADER);
        if(authHeader != null && authHeader.startsWith(AUTHORIZATION_HEADER_PREFIX)){
            String credentials = authHeader.substring(AUTHORIZATION_HEADER_PREFIX.length()).trim();
            byte[] decodedCredentials = Base64.getDecoder().decode(credentials);

            String decodedString = new String (decodedCredentials, StandardCharsets.UTF_8);

            String[] parts = decodedString.split(":");
            String username = parts[0];
            String password = parts[1];

            if(user.isUser(username, password)){
                logger.info(getLogMessage(containerRequestContext, "Authentication successful"));
                return;
            }

        }
        logger.warn(getLogMessage(containerRequestContext, "Authentication failed"));
        containerRequestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                .entity("Unauthorized User!")
                .build());
    }


    private String getLogMessage(ContainerRequestContext requestContext, String message) {
        return String.format("%s_%s_%s_%s",
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()),
                requestContext.getMethod(),
                "Authentication",
                message);
    }

}
