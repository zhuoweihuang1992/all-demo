package com.alog.dataservice.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

public class ResultFilter extends ZuulFilter  {

    private static Logger log = LoggerFactory.getLogger(ResultFilter.class);
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        log.info("进入结果处理的过滤器！");
        log.info("===============");
        log.info(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));
        System.out.println(request.getRequestURL());
        String token = request.getHeader("token");
        System.out.println(token);
        log.info("access token ok");
        return null;
    }
}
