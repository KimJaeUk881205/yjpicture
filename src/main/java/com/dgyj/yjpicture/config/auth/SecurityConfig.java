package com.dgyj.yjpicture.config.auth;

import com.dgyj.yjpicture.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.firewall.DefaultHttpFirewall;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserLoginSuccessHandler successHandler;
    private final UserLoginFailureHandler failureHandler;
    private final UserService userService; // 3

    private final UserLoginExpiredStrategy userLoginExpiredStrategy;

    @Override
    public void configure(WebSecurity web) { // 4
        web.ignoring().antMatchers("/assets/**","/dext5editor/**","/dist/**","/error/**","/css/**", "/js/**", "/img/**", "/h2-console/**").requestMatchers(PathRequest.toStaticResources().atCommonLocations());
        web.httpFirewall(defaultHttpFirewall());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement().sessionFixation().changeSessionId()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .headers().frameOptions().disable()
                .and()
                .csrf().disable()
                .authorizeRequests() // 6
                .antMatchers("/picture/pictureInfo","/sample","/login").permitAll()
                .anyRequest().authenticated() // ????????? ???????????? ????????? ????????? ?????? ?????? ????????? ????????? ?????? ??????
                //.anyRequest().permitAll()
                .and()
                .formLogin() // 7
                .loginPage("/login") // ????????? ????????? ??????
                .loginProcessingUrl("/login")
                .usernameParameter("loginId")
                .passwordParameter("password")
                .defaultSuccessUrl("/loginSuccess") // ????????? ?????? ??? ??????????????? ??????
                .successHandler(successHandler)
                .failureHandler(failureHandler)
                .and()
                .logout() // 8
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/logoutSuccess") // ???????????? ????????? ??????????????? ??????
                .invalidateHttpSession(true)// ?????? ?????????
                .deleteCookies("JSESSIONID") /*?????? ??????*/
                .clearAuthentication(true) /*???????????? ??????*/
                .permitAll()
                .and()
                .sessionManagement()
                //.invalidSessionUrl("/logininvalid") //????????? ????????????????????? ???????????? ????????????  maxSessionsPreventsLogin true?????? ????????????.
                .maximumSessions(1) /* session ?????? ?????? */
                .maxSessionsPreventsLogin(false) /* ????????? ????????? ???????????? x, false ??? ?????? ?????? ????????? session ??????*/
                .expiredSessionStrategy(this.userLoginExpiredStrategy.setDefaultUrl("/logininvalid"))

        ;
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception { // 9
        auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Bean
    public HttpFirewall defaultHttpFirewall() {
        return new DefaultHttpFirewall();
    }


}