package com.wokite.net.backendstore.security;

import com.wokite.net.backendstore.security.jwt.JwtAuthEntryPoint;
import com.wokite.net.backendstore.security.jwt.JwtAuthTokenFilter;
import com.wokite.net.backendstore.security.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
/*
@EnableGlobalMethodSecurity(
        prePostEnabled = true
)*/
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtAuthEntryPoint unauthorizedHandler;

    @Bean
    public JwtAuthTokenFilter authenticationJwtTokenFilter() {
        return new JwtAuthTokenFilter();
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().
                authorizeRequests()

                .antMatchers("/**/auth/signIn").permitAll()
                .antMatchers("/**/auth/signUp").permitAll()
                .antMatchers("/**/photoUser/{id}").permitAll()
                .antMatchers("/**/contrats/downloadContratFile/**").permitAll()
                .antMatchers("/**/uploadPdfFile/{id}").permitAll()
                .antMatchers("/**/versements/downloadVersementFile/**").permitAll()
                .antMatchers("/**/utilisateurs/avatar/{id}").permitAll()
                .antMatchers("/**/utilisateurs/avatar/{id}").permitAll()

              //  .antMatchers("/**/categories/create").permitAll()
                .antMatchers("/**/categories/*").permitAll()
                .antMatchers("/**/categories/findById/{id}").permitAll()
                .antMatchers("/**/categories/update/{catId}").permitAll()
                .antMatchers("/**/categories/delete/{id}").permitAll()
                .antMatchers("/**/categories/**}").permitAll()
                .antMatchers("/**/subcategories/*").permitAll()
                .antMatchers("/**/subcategories/**").permitAll()
                .antMatchers("/**/subcategories/findById/{id}").permitAll()
                .antMatchers("/**/subcategories/update/{catId}").permitAll()
                .antMatchers("/**/subcategories/delete/{id}").permitAll()

                .antMatchers("/**/products/*").permitAll()
                .antMatchers("/**/products/**").permitAll()
                .antMatchers("/**/products/findById/{id}").permitAll()
                .antMatchers("/**/products/update/{catId}").permitAll()
                .antMatchers("/**/products/delete/{id}").permitAll()

                .antMatchers("/**/commandes/*").permitAll()
                .antMatchers("/**/commandes/**").permitAll()
                .antMatchers("/**/commandes/findById/{id}").permitAll()
                .antMatchers("/**/commandes/update/{catId}").permitAll()
                .antMatchers("/**/commandes/delete/{id}").permitAll()

                .antMatchers("/**/typedepense/*").permitAll()
                .antMatchers("/**/typedepense/**").permitAll()
                .antMatchers("/**/fournisseurs/*").permitAll()
                .antMatchers("/**/fournisseurs/**").permitAll()
                .antMatchers("/**/clients/*").permitAll()
                .antMatchers("/**/clients/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        //  .allowedOrigins("**")
                        .allowedOrigins("http://localhost:4200")
                        //  .allowedOrigins("http://localhost:8080/MyStock")
                        // .allowedOrigins("https://alamine.herokuapp.com")
                        // .allowedOrigins("https://librairiealamine.com")
                        //   .allowedMethods("*")
                        .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
                        .maxAge(3600L)
                        .allowedHeaders("*")
                        .exposedHeaders("Authorization")
                        .allowCredentials(true);


            }
        };
    }



}
