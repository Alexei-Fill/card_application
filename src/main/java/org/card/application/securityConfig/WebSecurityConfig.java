package org.card.application.securityConfig;

import org.card.application.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@Order(1)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserServiceImpl userServiceImpl;
    @Autowired
    RestAuthEntryPoint restAuthEntryPoint;
    @Autowired
    SavedRequestAwareSuccessHandler mySuccessHandler;

    SimpleUrlAuthenticationFailureHandler myFailureHandler = new SimpleUrlAuthenticationFailureHandler();

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        authenticationProvider().setPasswordEncoder(encoder());
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        TokenAuthenticationFilter tokenAuthenticationFilter = new TokenAuthenticationFilter(userServiceImpl);
        http
                .addFilterBefore(customUsernamePasswordAuthenticationFilter(), CustomUsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(tokenAuthenticationFilter, BasicAuthenticationFilter.class)
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(restAuthEntryPoint)
                .and()
                .exceptionHandling().authenticationEntryPoint(restAuthEntryPoint)
                .and()
                .authorizeRequests()
                .antMatchers("/login").anonymous()
                .antMatchers("/logout").permitAll()
                .antMatchers("/ca/user").permitAll()
                .antMatchers("/ca/**").hasAnyRole("ADMIN", "USER")
                .and()
                .formLogin().loginProcessingUrl("/login").passwordParameter("password").usernameParameter("username")
                .and()
                .logout().logoutUrl("/logout")
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CustomUsernamePasswordAuthenticationFilter customUsernamePasswordAuthenticationFilter()
            throws Exception {
        CustomUsernamePasswordAuthenticationFilter customUsernamePasswordAuthFilter = new CustomUsernamePasswordAuthenticationFilter();
        customUsernamePasswordAuthFilter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/login", "POST"));
        customUsernamePasswordAuthFilter.setAuthenticationSuccessHandler(mySuccessHandler);
        customUsernamePasswordAuthFilter.setAuthenticationFailureHandler(myFailureHandler);
        customUsernamePasswordAuthFilter.setAuthenticationManager(authenticationManagerBean());
        return customUsernamePasswordAuthFilter;
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userServiceImpl);
        return daoAuthenticationProvider;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
