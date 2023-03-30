package com.example.Library.config;



import com.example.Library.enums.Permission;
import com.example.Library.security.UserDetailsServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class WebSecurity extends WebSecurityConfigurerAdapter {

    UserDetailsServiceImpl userDetailsService;



    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
        super.configure(auth);
    }

    @Bean
    protected PasswordEncoder getPasswordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http    .csrf().disable()
                .authorizeRequests()

                .antMatchers(HttpMethod.GET,"/pnr","/user/*","/airport/*","/aviaCompany/*","/email/*").hasAuthority(Permission.ADMIN_READ.getPermission())
                .antMatchers("/user/passwordUpdate").hasAuthority(Permission.ADMIN_READ.getPermission())
                .antMatchers("/pnr/moder").hasAuthority(Permission.ADMIN_WRITE.getPermission())
                .antMatchers(HttpMethod.POST,"/pnr/*","/user/*","/airport/*","/aviaCompany/*").hasAuthority(Permission.ADMIN_WRITE.getPermission())
                .antMatchers(HttpMethod.DELETE,"/pnr/*","/user/*","/airport/delete/*","/aviaCompany/delete/*").hasAuthority(Permission.ADMIN_UPDATE.getPermission())
                .antMatchers("/authenticate").permitAll()
                .antMatchers(HttpMethod.PUT,"/pnr/*","/user/delete/*","/user/update/*","/airport/update/*","/airport/delete/*","/aviaCompany/delete/*","/aviaCompany/update/*").hasAuthority(Permission.ADMIN_UPDATE.getPermission())
                .anyRequest().authenticated()
//                .and()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

                .and().httpBasic();
//        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }


}