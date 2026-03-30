package com.SmartGarbageCollection.GarbageCollection.Filter;

import com.SmartGarbageCollection.GarbageCollection.Service.UserServiceDetailImpl;
import com.SmartGarbageCollection.GarbageCollection.Utilis.JwtUtility;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter
{
    String userName=null;
    @Autowired
    JwtUtility jwtUtility;
    @Autowired
    UserServiceDetailImpl userServiceDetailImpl;
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException
//    {
//        String token=null;
//        String authHeader=request.getHeader("Authorization");
//        if(authHeader!=null && authHeader.startsWith("Bearer "))
//        {
//            token= authHeader.substring(7);
//            userName=jwtUtility.extractUserName(token);
//        }
//        if(userName!=null && SecurityContextHolder.getContext().getAuthentication()==null)
//        {
//           UserDetails userDetails= userServiceDetailImpl.loadUserByUsername(userName);
//            if (jwtUtility.validateToken(token, userDetails)) {
//                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
//                        userDetails, null, userDetails.getAuthorities());
//                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                SecurityContextHolder.getContext().setAuthentication(authToken);
//            }
//        }
//
@Override
protected void doFilterInternal(HttpServletRequest request,
                                HttpServletResponse response,
                                FilterChain filterChain)
        throws ServletException, IOException {

    String authHeader = request.getHeader("Authorization");
    String token = null;
    String userName = null;

    if (authHeader != null && authHeader.startsWith("Bearer ")) {
        token = authHeader.substring(7);
        userName = jwtUtility.extractUserName(token);
    }

    if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {

        UserDetails userDetails = userServiceDetailImpl.loadUserByUsername(userName);

        if (jwtUtility.validateToken(token, userDetails)) {

            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );

            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(authToken);
        }
    }

    filterChain.doFilter(request, response);
}


}
//        filterChain.doFilter(request,response);


