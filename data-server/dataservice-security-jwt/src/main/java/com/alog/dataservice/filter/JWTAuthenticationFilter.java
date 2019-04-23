package com.alog.dataservice.filter;

import com.alog.dataservice.entity.LoginUser;
import com.alog.dataservice.service.impl.JwsUser;
import com.alog.dataservice.utils.JwtTokenUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private ThreadLocal<Integer> rememberMe = new ThreadLocal<>();
    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager){
        this.authenticationManager = authenticationManager;
        super.setFilterProcessesUrl("/auth/login");
    }

    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)throws AuthenticationException {
        //从输入流中获取到登录信息
        try{
            LoginUser loginUser = new ObjectMapper().readValue(request.getInputStream(), LoginUser.class);
            rememberMe.set(loginUser.getRememberMe());
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword(), new ArrayList<>())
            );
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    //成功验证后调用的方法
    //如果验证成功，就生成token并返回
    protected  void successfulAuthentication(HttpServletRequest request,
                                             HttpServletResponse response,
                                             FilterChain chain,
                                             Authentication authResult)throws IOException, ServletException {


        //查看源码会发现调用getPrincipal()方法会返回一个实现了UserDetails接口的对象
        //所以就是JwtUser
        JwsUser jwsUser = (JwsUser) authResult.getPrincipal();
        System.out.println("jwtUser:" + jwsUser.toString());
        boolean isRemember = rememberMe.get() == 1;
        String role = "";
        String token = "";
        Collection<? extends GrantedAuthority> authorities = jwsUser.getAuthorities();
        for(GrantedAuthority authority : authorities){
            role = authority.getAuthority();
        }
        token = JwtTokenUtils.createToken(jwsUser.getUsername(),role,isRemember);

        Claims tokenBody = JwtTokenUtils.getTokenBody(token);
        System.out.println(tokenBody);


        //String token = JwtTokenUtils.createToken(jwsUser.getUsername(),role,isRemember);
        //返回创建成功的token
        //但是这里创建的token只是单纯的token
        //按照jwt的规定，最后请求的格式应该是'Bearer token'
        response.setHeader("token",JwtTokenUtils.TOKEN_PREFIX + token);
}

    //这里是验证失败时候调用的方法
    protected  void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed)  throws IOException, ServletException {

        response.getWriter().write("authentication failed,reason:" + failed.getMessage());
    }


}
