package com.jennifer.config.handler;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextListener;

import javax.servlet.annotation.WebListener;

/**
 * Custom Request Context Listener
 */

@Configuration
@WebListener
public class CustomRequestContextListener extends RequestContextListener {
}
