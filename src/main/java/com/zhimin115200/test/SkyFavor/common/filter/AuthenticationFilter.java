package com.zhimin115200.test.SkyFavor.common.filter;

import com.zhimin115200.test.SkyFavor.common.util.SpringContextUtil;
import com.zhimin115200.test.SkyFavor.service.UserTokenService;
import com.zhimin115200.test.SkyFavor.service.impl.UserTokenServiceImpl;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;


public class AuthenticationFilter implements Filter {

    private static final Log logger = LogFactory.getLog(AuthenticationFilter.class);
    private static final long TIME_DIFFERENCE = 5 * 60 * 1000L;
    private final  UserTokenService tokenService = (UserTokenService) SpringContextUtil.getBean(UserTokenService.class);

    //不走鉴权的接口
    public static final List<String> IGNORE_URL_LIST = Arrays.asList(
            "/user/validateAccount".toLowerCase(),
            "/user/sendVerify".toLowerCase(),
            "/user/regist".toLowerCase()
    );
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
        // 权限检查
        if (isIgnore((HttpServletRequest) request) || isPermit((HttpServletRequest) request)) {
            chain.doFilter(request, response);
        } else {
            HttpServletResponse resp = (HttpServletResponse) response;
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            resp.flushBuffer();
        }
    }

    @Override
    public void destroy() {

    }

    private boolean isPermit(HttpServletRequest request) {
        String queryString = request.getQueryString();
        if (StringUtils.isBlank(queryString)) {
            logger.warn("Query is blank:" + getFullRequest(request));
            return false;
        }

        String email = null;
        String sign = null;
        String timeStamp = null;

        String[] params = queryString.split("&");
        for (String param : params) {
            if (param.startsWith("email=")) {
                email = param.substring("email=".length()).trim();
            }
            if (param.startsWith("ts=")) {
                timeStamp = param.substring("ts=".length()).trim();
            }
            if (param.startsWith("s=")) {
                sign = param.substring("s=".length()).trim();
            }
        }
        // 验证参数如果为空，验证失败
        if (StringUtils.isBlank(email) || StringUtils.isBlank(sign)) {
            logger.warn("Query's params is blank:" + getFullRequest(request));
            return false;
        }

        // 如果时间差大于5分钟，验证失败
        long now = System.currentTimeMillis();
        long time = Long.parseLong(timeStamp);
        if ((now - time) >= TIME_DIFFERENCE) {
            logger.warn("Request timed out:" + getFullRequest(request));
            return false;
        }
        // 取得tokenstr
        String tokenstr = tokenService.get(email);
        if (StringUtils.isBlank(tokenstr)) {
            logger.warn("clientId is blank:" + getFullRequest(request));
            return false;
        }

        // 验证签名
        String verifySign = getSign(params, tokenstr);
        if (StringUtils.isBlank(verifySign) || !verifySign.equalsIgnoreCase(sign)) {
            logger.warn("Sing is wrong:" + getFullRequest(request));
            return false;
        }

        return true;
    }

    private String getSign(String[] params, String tokenstr) {
        StringBuilder basestring = new StringBuilder();

        for (String param : params) {
            if (!param.startsWith("s=")) {
                basestring.append(param);
            }
        }
        basestring.append(tokenstr);
        logger.info(basestring.toString());
        logger.info(DigestUtils.md5Hex(basestring.toString()));
        return DigestUtils.md5Hex(basestring.toString());
    }

    private String getFullRequest(HttpServletRequest request) {
        String pathInfo = request.getRequestURI();
        String queryString = request.getQueryString();

        if (StringUtils.isNotBlank(queryString)) {
            return pathInfo.trim() + "?" + queryString.trim();
        } else {
            return pathInfo.trim();
        }
    }

    private boolean isIgnore(HttpServletRequest request) {
        String uri = request.getRequestURI().trim().toLowerCase();
        for (String item : IGNORE_URL_LIST) {
            if (uri.contains(item)) {
                return true;
            }
        }
        return false;
    }
}
