package com.SmartGarbageCollection.GarbageCollection.Config;

import com.SmartGarbageCollection.GarbageCollection.Filter.JwtAuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SpringSecurity {

    private final JwtAuthFilter jwtAuthFilter;
    private final UserDetailsService userDetailsService;

    // 🔐 Password Encoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 🔑 Authentication Provider
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    // 🔐 Authentication Manager
    @Bean
    public AuthenticationManager authenticationManager() {
        return new ProviderManager(authenticationProvider());
    }

    // 🛡️ Security Filter Chain
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                // ❌ Disable CSRF (not needed for JWT)
                .csrf(csrf -> csrf.disable())

                // ✅ Stateless session (VERY IMPORTANT)
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                // 🔐 Authorization Rules
                .authorizeHttpRequests(auth -> auth

                        // 🌐 PUBLIC ENDPOINTS
                        .requestMatchers(
                                "/auth/**",
                                "/public/**",
                                "/health/**",
                                "/swagger-ui/**",
                                "/v3/api-docs/**"
                        ).permitAll()

                        // 🔒 ROLE-BASED ACCESS
                        .requestMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
                        .requestMatchers("/collector/**").hasAuthority("ROLE_COLLECTOR")
                        .requestMatchers("/pickup/**").hasAuthority("ROLE_RESIDENT")

                        // 🔐 ALL OTHER REQUESTS
                        .anyRequest().authenticated()
                )

                // 🔑 Attach authentication provider
                .authenticationProvider(authenticationProvider())

                // ⚙️ Add JWT filter BEFORE username/password filter
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}











































































































































































































































































































////package com.SmartGarbageCollection.GarbageCollection.Config;
////
////import com.SmartGarbageCollection.GarbageCollection.Filter.JwtAuthFilter;
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.context.annotation.Bean;
////import org.springframework.context.annotation.Configuration;
////import org.springframework.security.authentication.AuthenticationManager;
////import org.springframework.security.authentication.ProviderManager;
////import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
////import org.springframework.security.config.annotation.web.builders.HttpSecurity;
////import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
////import org.springframework.security.core.userdetails.UserDetailsService;
////import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
////import org.springframework.security.crypto.password.PasswordEncoder;
////import org.springframework.security.web.SecurityFilterChain;
////import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
////
////@Configuration
////@EnableWebSecurity
////public class SpringSecurity
////{
////    @Autowired
////    JwtAuthFilter jwtAuthFilter;
////    @Bean
////    public PasswordEncoder passwordEncoder()
////    {
////        return new BCryptPasswordEncoder();
////    }
////
////    @Bean
////    public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception
////    {
////        http
////                .authorizeHttpRequests(authz -> authz
////                        .requestMatchers("/public/**","/auth/**").permitAll() // Allow public paths
////                        .anyRequest().authenticated() // All others require auth
////                )
////                .csrf(csrf -> csrf.disable());
////        http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
////
////        return http.build();
////    }
////    @Bean
////    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) throws Exception {
////        DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
////        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
////        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
////        return new ProviderManager(daoAuthenticationProvider);
////    }
////
////
////
////}
//
//package com.SmartGarbageCollection.GarbageCollection.Config;
//
//import com.SmartGarbageCollection.GarbageCollection.Filter.JwtAuthFilter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.ProviderManager;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@Configuration
//@EnableWebSecurity
//public class SpringSecurity {
//
//    @Autowired
//    private JwtAuthFilter jwtAuthFilter;
//
//    @Autowired
//    private UserDetailsService userDetailsService;
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public DaoAuthenticationProvider authenticationProvider(PasswordEncoder passwordEncoder) {
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        provider.setUserDetailsService(userDetailsService);
//        provider.setPasswordEncoder(passwordEncoder);
//        return provider;
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
////        http
////                .csrf(csrf -> csrf.disable())
////                .authorizeHttpRequests(auth -> auth
////                        .requestMatchers("/public/**", "/auth/**","/admin/**","/collector/**","/health/**").permitAll()
////                        .anyRequest().authenticated()
////                )
////                .authenticationProvider(authenticationProvider(passwordEncoder()))
////                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
////        http
////                .csrf(csrf -> csrf.disable())
////                .authorizeHttpRequests(auth -> auth
////                        // Public endpoints
////                        .requestMatchers( "/auth/**", "/health/**","/public/**").permitAll()
////
////                        // Role-based access
////                        .requestMatchers("/pickup/**").hasRole("RESIDENT")
////                        .requestMatchers("/collector/**").hasRole("COLLECTOR")
////                        .requestMatchers("/admin/**").hasRole("ADMIN")
////
////                        // Everything else
////                        .anyRequest().authenticated()
////                )
////                .authenticationProvider(authenticationProvider(passwordEncoder()))
////                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
//
//        return http.build();
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(PasswordEncoder passwordEncoder) {
//        return new ProviderManager(authenticationProvider(passwordEncoder));
//    }
//}
////@Configuration
////@EnableWebSecurity
////public class SpringSecurity {
////
////    @Autowired
////    private JwtAuthFilter jwtAuthFilter;
////
////    @Autowired
////    private UserDetailsService userDetailsService;
////
////    @Bean
////    public PasswordEncoder passwordEncoder() {
////        return new BCryptPasswordEncoder();
////    }
////
////    @Bean
////    public DaoAuthenticationProvider authenticationProvider() {
////        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
////        provider.setUserDetailsService(userDetailsService);
////        provider.setPasswordEncoder(passwordEncoder());
////        return provider;
////    }
////
////    @Bean
////    public AuthenticationManager authenticationManager() {
////        return new ProviderManager(authenticationProvider());
////    }
////
////    @Bean
////    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
////        http
////                .csrf(csrf -> csrf.disable())
////                .authorizeHttpRequests(auth -> auth
////                        .requestMatchers("/public/**", "/auth/**").permitAll()
////                        .anyRequest().authenticated()
////                )
////                .authenticationProvider(authenticationProvider()) // SINGLE instance
////                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
////
////        return http.build();
////    }
////}