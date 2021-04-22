package com.kq.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import javax.sql.DataSource;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 没有以ROLE_开头  会报403   Access Denied
        // 加拉 ROLE_会启不动  需要调试
        http.authorizeRequests().antMatchers("/admin/api/**").hasRole("ADMIN")
                .antMatchers("/user/api/**").hasRole("USER")
//                .antMatchers("/user/api/**").hasAuthority("USER")
                .antMatchers("/app/api/**").permitAll()
                .and().formLogin().loginPage("/login").permitAll() //登录页不设权限访问
//                .anyRequest().authenticated().and().csrf().disable()
                ;
    }

    @Autowired
    private DataSource dataSource;


    // 内存
//    @Bean
//    @Override
//    public UserDetailsService userDetailsService() {
//
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(User.withUsername("user").password("123456").roles("USER").build());
//        manager.createUser(User.withUsername("admin").password("123456").roles("USER","ADMIN").build());
//
//        return manager;
//    }


    // 默认spring-security提供的表
    @Bean
    @Override
    public UserDetailsService userDetailsService() {

        JdbcDaoImpl jdbcDao = new JdbcDaoImpl();
        // 这里设置了 数据库就直接USER、ADMIN
        jdbcDao.setRolePrefix("ROLE_");
//        jdbcDao.setEnableGroups(true);
//        jdbcDao.setEnableAuthorities(true);
        jdbcDao.setDataSource(dataSource);


        //下面可以设置自己提供的表sql语句
//        jdbcDao.setUsernameBasedPrimaryKey();
//        jdbcDao.setAuthoritiesByUsernameQuery();
//        jdbcDao.setGroupAuthoritiesByUsernameQuery();

        return jdbcDao;

    }



    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

}
