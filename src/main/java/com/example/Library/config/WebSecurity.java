package com.example.Library.config;


import com.example.Library.enums.Permission;
import com.example.Library.enums.Roles;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

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
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http    .csrf().disable()
                .authorizeRequests()

                .antMatchers("/main","/allBooks","/registration","/registration/save","/email","/email/send").permitAll()
                .antMatchers(HttpMethod.GET).hasAuthority(Permission.ADMIN_READ.getPermission())
                .antMatchers(HttpMethod.POST).permitAll()
//                .antMatchers(HttpMethod.DELETE).hasAuthority(Permission.ADMIN_UPDATE.getPermission())
//                .antMatchers(HttpMethod.PUT).hasAuthority(Permission.ADMIN_UPDATE.getPermission())
                .anyRequest().authenticated()
//                .and().httpBasic();

                .and()
                .formLogin()
                .loginPage("/auth")
                .permitAll()
                .defaultSuccessUrl("/auth/success")
                .failureUrl("/auth/wrong")
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/auth/logout"))
//                .invalidateHttpSession(true)
//                .clearAuthentication(true)
//                .deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/auth");

    }


}
