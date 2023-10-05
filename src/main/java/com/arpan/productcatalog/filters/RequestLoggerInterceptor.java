package com.arpan.productcatalog.filters;

import com.arpan.productcatalog.config.UserInfoHolder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RequestLoggerInterceptor implements HandlerInterceptor {
    private final UserInfoHolder userInfoHolder;

    @Value("${app.default.user.username}")
    private String defaultUsername;
    @Value("#{'${app.default.user.roles}'.split(',')}")
    private List<String> defaultRoles;

    public RequestLoggerInterceptor(UserInfoHolder userInfoHolder) {
        this.userInfoHolder = userInfoHolder;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        processUsernameFromRequestHeader(request);
        processRolesFromRequestHeader(request);
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }


    private void processUsernameFromRequestHeader(HttpServletRequest request) {
        String username = null;
        if (request.getHeader("username") != null) {
            username = request.getHeader("username");
        } else if (defaultUsername != null) {
            username = defaultUsername;
        } else {
            username = null;
        }
        userInfoHolder.setUsername(username);
    }

    private void processRolesFromRequestHeader(HttpServletRequest request) {
        List<String> roles = new ArrayList<>();
        if (request.getHeader("roles") != null) {
            roles = Arrays.stream(request.getHeader("roles").split(",")).toList();
        } else if (defaultUsername != null) {
            roles = defaultRoles;
        } else {
            roles = new ArrayList<>();
        }
        userInfoHolder.setRoles(roles);
    }
}
