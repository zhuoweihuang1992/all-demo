package com.alog.dataservice.config;

import com.alog.dataservice.filter.AuthorizationFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Resource
    public void configGlobal(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.inMemoryAuthentication().withUser("java").password("hello")
                .roles("USER");
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .authorizeRequests()
                //测试用资源，需要验证了的用户才能访问
                .antMatchers("/**").authenticated()
                //需要角色为admin才能删除资源
                //.antMatchers(HttpMethod.DELETE,"/tasks/**").hasAuthority("ROLE_ADMIN")
                //.antMatchers(HttpMethod.POST,"/tasks/**").hasRole("ADMIN")
                //.antMatchers(HttpMethod.PUT,"/tasks/**").hasRole("USER")
                //其他都放行
                .anyRequest().permitAll()
                .and()
                //.addFilter(new AuthenticationFilter(authenticationManager()))
                .addFilter(new AuthorizationFilter(authenticationManager()))
                //不需要session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and();
        // 所有的Rest服务一定要设置为无状态，以提升操作性能
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}


