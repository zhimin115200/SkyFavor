
package com.zhimin115200.test.SkyFavor.common.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class ResponseFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");

        httpServletResponse
                .setHeader(
                        "Access-Control-Allow-Headers",
                        "User-Agent,Origin,Cache-Control,Content-type,Date,Server,withCredentials,AccessToken");

        httpServletResponse.setHeader("Access-Control-Allow-Credentials",
                "true");

        httpServletResponse.setHeader("Access-Control-Allow-Methods",
                "GET, POST, PUT, DELETE, OPTIONS, HEAD");

        httpServletResponse.setHeader("Access-Control-Max-Age", "1209600");

        httpServletResponse.setHeader("Access-Control-Expose-Headers",
                "accesstoken");

        httpServletResponse.setHeader("Access-Control-Request-Headers",
                "accesstoken");

        httpServletResponse.setHeader("Content-Security-Policy",
                "default-src 'self';img-src *");

        httpServletResponse.setHeader("Expires", "-1");

        httpServletResponse.setHeader("Cache-Control", "no-cache");

        httpServletResponse.setHeader("pragma", "no-cache");

        httpServletResponse.setHeader("serverTime",
                Long.toString(System.currentTimeMillis()));

        if (chain != null) {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }

}
