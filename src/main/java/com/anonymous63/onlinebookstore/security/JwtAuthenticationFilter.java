package com.anonymous63.onlinebookstore.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenHelper jwtTokenHelper;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String requestToken = request.getHeader("Authorization");

        String username = null;

        String jwtToken = null;

        if (requestToken != null && requestToken.startsWith("Bearer ")) {
            jwtToken = requestToken.substring(7);
            try {
                username = jwtTokenHelper.getUsernameFromToken(jwtToken);
            } catch (IllegalArgumentException e) {
                logger.warn("Unable to get JWT Token", e);
                System.out.println("Unable to get JWT Token " + e.getMessage());
            } catch (ExpiredJwtException e) {
                logger.warn("JWT Token has expired", e);
                System.out.println("JWT Token has expired " + e.getMessage());
            } catch (MalformedJwtException e) {
                logger.warn("JWT Token is malformed", e);
                System.out.println("JWT Token is malformed " + e.getMessage());
            } catch (Exception e) {
                logger.warn("JWT Token is invalid", e);
                System.out.println("JWT Token is invalid " + e.getMessage());
            }
        } else {
            logger.warn("JWT Token does not begin with Bearer String");
            System.out.println("JWT Token does not begin with Bearer String");
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            if (this.jwtTokenHelper.validateToken(jwtToken, userDetails)) {
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            } else {
                logger.warn("Token is not valid");
                System.out.println("Token is not valid");
            }
        } else {
            logger.warn("Username is null");
            System.out.println("Username is null");
        }

        filterChain.doFilter(request, response);

    }
}
