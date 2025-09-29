package br.com.fiap.sprint3.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    @Autowired
    private SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain SecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf
                    .ignoringRequestMatchers("/api/**")
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
                .authorizeHttpRequests(authorize -> authorize
                    .requestMatchers(HttpMethod.GET, "/swagger-ui/**").permitAll()
                    .requestMatchers(HttpMethod.GET, "/v3/**").permitAll()
                    .requestMatchers(HttpMethod.GET, "/web/usuarios/login").permitAll()
                    .requestMatchers(HttpMethod.GET, "/web/usuarios/register").permitAll()
                    .requestMatchers(HttpMethod.POST, "/*/usuarios/login").permitAll()
                    .requestMatchers(HttpMethod.POST, "/*/usuarios/register").permitAll()
                    .requestMatchers(HttpMethod.POST, "/web/filiais/new").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.POST, "/web/motos/new").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.POST, "/web/usuarios/new").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.POST, "/api/filiais").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.POST, "/api/motos").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.POST, "/api/usuarios").hasRole("ADMIN")
                    .anyRequest().authenticated()
                )
                .formLogin(login -> login
                    .loginPage("/web/usuarios/login")
                    .loginProcessingUrl("/web/usuarios/login")
                    .defaultSuccessUrl("/web/filiais", true)
                    .permitAll()
                )
                .logout(logout -> logout
                    .logoutUrl("/web/usuarios/logout")
                    .logoutSuccessUrl("/web/usuarios/login")
                    .invalidateHttpSession(true)
                    .clearAuthentication(true)
                    .deleteCookies("JSESSIONID")
                )
                .exceptionHandling(ex -> ex
                    .authenticationEntryPoint((req, res, e) -> res.sendRedirect("/web/usuarios/login"))
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
