package com.jennifer.config.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Filter each time request is received
 */

public class SessionTimeoutCookieFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(SessionTimeoutCookieFilter.class);

    @Override
    public void init(FilterConfig config) throws ServletException {
        //log.info("Initialization SessionTimeoutCookieFilter");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse httpResp = (HttpServletResponse) resp;
        HttpServletRequest httpReq = (HttpServletRequest) req;

        long currTime = System.currentTimeMillis();
        String expiryTime = Long.toString(currTime + httpReq.getSession().getMaxInactiveInterval() * 1000);

        //log.info("----------------------------");
        //log.info("currentTimeMillis: "+currTime);
        //log.info("getMaxInactiveInterval: "+httpReq.getSession().getMaxInactiveInterval());
        //log.info("Filtering ! Time: " + expiryTime);


        long oneDay = 86400000;
        HttpSession session = httpReq.getSession(false);

        if ((System.currentTimeMillis() - session.getCreationTime()) > oneDay) {
            session.invalidate();
        } else {
            Cookie cookie = new Cookie("serverTime", Long.toString(currTime));
            cookie.setPath("/");
            httpResp.addCookie(cookie);
            if (httpReq.getRemoteUser() != null) {
                cookie = new Cookie("sessionExpiry", expiryTime);
            }
            cookie.setPath("/");
            httpResp.addCookie(cookie);

            filterChain.doFilter(req, resp);
        }
    }

    @Override
    public void destroy() {

    }
}
