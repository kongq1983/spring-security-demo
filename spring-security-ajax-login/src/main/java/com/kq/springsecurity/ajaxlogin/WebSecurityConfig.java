package com.kq.springsecurity.ajaxlogin;

import com.kq.springsecurity.ajaxlogin.config.AjaxJsonUsernamePasswordAuthenticationFilter;
import com.kq.springsecurity.ajaxlogin.config.LoginUrlAndAjaxAuthenticationEntryPoint;
import com.kq.springsecurity.ajaxlogin.config.MyAuthenticationManager;
import com.kq.springsecurity.ajaxlogin.dto.DtoResult;
import com.kq.springsecurity.util.JacksonUtil;
import com.kq.springsecurity.util.ResponseUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletResponse;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // 这个也可以使用@Bean来实现
        // 替换过滤器
        AjaxJsonUsernamePasswordAuthenticationFilter ajaxJsonLoginFilter = new AjaxJsonUsernamePasswordAuthenticationFilter();
        MyAuthenticationManager authenticationManager = new MyAuthenticationManager();
        //设置要处理的登陆请求的路径
        ajaxJsonLoginFilter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/mylogin", "POST"));
        // 设置authenticationManager
        ajaxJsonLoginFilter.setAuthenticationManager(authenticationManager);
        //替换掉旧的默认的登陆处理过滤器
        http.addFilterAt(ajaxJsonLoginFilter, UsernamePasswordAuthenticationFilter.class);

        //处理认证成功之后的返回
        ajaxJsonLoginFilter.setAuthenticationSuccessHandler((request, response, authentication) -> {
            DtoResult result = new DtoResult();

            ResponseUtil.writeJson(JacksonUtil.toJSon(result),response);

        });

        //处理认证失败之后的返回
        ajaxJsonLoginFilter.setAuthenticationFailureHandler((request, response, authException) -> {
            DtoResult result = new DtoResult();
            result.setCode(HttpServletResponse.SC_UNAUTHORIZED+"");
            if(authException!=null) {
                result.setResult(authException.getMessage());
            }
            ResponseUtil.writeJson(JacksonUtil.toJSon(result),response);
        });

        // 没有以ROLE_开头  会报403   Access Denied
        // 加拉 ROLE_会启不动  需要调试

        String loginPage = "/mylogin";
        http.exceptionHandling().authenticationEntryPoint(new LoginUrlAndAjaxAuthenticationEntryPoint(loginPage)).and()
        .authorizeRequests().antMatchers("/admin/api/**").hasRole("ADMIN")
                .antMatchers("/user/api/**").hasRole("USER")
                .antMatchers("/app/api/**").permitAll()
                .and().formLogin().loginPage(loginPage).permitAll() //自定义登录页
        .and().authorizeRequests().and().csrf().disable();  // 自定义界面 先关闭csrf
//                .anyRequest().authenticated().and().csrf().disable();

    }


    @Bean
    @Override
    public UserDetailsService userDetailsService() {

        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("user").password("123456").roles("USER").build());
        manager.createUser(User.withUsername("admin").password("123456").roles("USER","ADMIN").build());

        return manager;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

}
