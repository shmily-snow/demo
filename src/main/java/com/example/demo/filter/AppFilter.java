package com.example.demo.filter;

import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @projectName: peixun
 * @author: caog
 * @date: 2020年05月07日 16:55
 * @version: 1.0
 */
public class AppFilter  extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String auth= request.getHeader("auth");
        if(!StringUtils.isEmpty(auth)){
            if("123".equals(auth)){
                System.out.println("认证通过");
                filterChain.doFilter(request,response);
            }
        }
        filterChain.doFilter(request,response);
        System.out.println("认证不通过");
        return;
    }
}
