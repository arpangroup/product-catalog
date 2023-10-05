package com.arpan.productcatalog.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;


@Slf4j
public class OUFilter implements Filter {

    @Value("${app.mode}")
    private String appMode;


    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        log.debug("Application mode is: " + appMode);
        if (appMode != null && !"".equals(appMode.trim())) {
            if (req instanceof HttpServletRequest) {
                HttpServletRequest request = (HttpServletRequest) req;
                String uri = request.getRequestURI();

                if ("UI".equals(appMode)) {

                } else if ("API".equals(appMode)) {
                    if (uri.contains("/resource/*")) {
                        HttpServletResponse resp = (HttpServletResponse) res;
                        resp.reset();
                        resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
                        return;
                    }
                }
            }
        }
        chain.doFilter(req, res);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
