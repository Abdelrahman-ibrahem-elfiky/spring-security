package com.local.spring_security.filter;

import jakarta.servlet.*;

import java.io.IOException;

public class FilterBefore implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        System.out.println("inside filter before");
        //important step
        filterChain.doFilter(servletRequest,servletResponse);

    }
}
