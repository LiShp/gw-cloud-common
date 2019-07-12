package com.gw.cloud.common.core.interceptor;

import com.gw.cloud.common.core.handler.BaseExceptionHandler;
import com.gw.cloud.common.core.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 基本拦截器
 *
 * @author WRQ
 * @date 2019/6/26
 * @since 1.0.0
 */
public class BaseInterceptor implements HandlerInterceptor {

    protected static final Logger LOGGER = LoggerFactory.getLogger(BaseExceptionHandler.class);

    private static final String STR_UNKNOWN = "unknown";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (handler instanceof HandlerMethod) {
            getIpAddress(request);
            HandlerMethod method = (HandlerMethod) handler;
            LOGGER.info("Requested method: " + method.getMethod().getName());
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
    }

    /**
     * 获取请求主机IP地址，如果通过代理进来则透过防火墙获取真实IP地址
     *
     * @param request 请求
     * @return IP地址
     */
    private static String getIpAddress(HttpServletRequest request) {

        String ip = request.getHeader("X-Forwarded-For");

        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("getIpAddress(HttpServletRequest) - X-Forwarded-For - String ip=" + ip);
        }

        if (ip == null || ip.length() == 0 || STR_UNKNOWN.equalsIgnoreCase(ip)) {
            if (ip == null || ip.length() == 0 || STR_UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
                if (LOGGER.isInfoEnabled()) {
                    LOGGER.info("getIpAddress(HttpServletRequest) - Proxy-Client-IP - String ip=" + ip);
                }
            }
            if (ip == null || ip.length() == 0 || STR_UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
                if (LOGGER.isInfoEnabled()) {
                    LOGGER.info("getIpAddress(HttpServletRequest) - WL-Proxy-Client-IP - String ip=" + ip);
                }
            }
            if (ip == null || ip.length() == 0 || STR_UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
                if (LOGGER.isInfoEnabled()) {
                    LOGGER.info("getIpAddress(HttpServletRequest) - HTTP_CLIENT_IP - String ip=" + ip);
                }
            }
            if (ip == null || ip.length() == 0 || STR_UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
                if (LOGGER.isInfoEnabled()) {
                    LOGGER.info("getIpAddress(HttpServletRequest) - HTTP_X_FORWARDED_FOR - String ip=" + ip);
                }
            }
            if (ip == null || ip.length() == 0 || STR_UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
                if (LOGGER.isInfoEnabled()) {
                    LOGGER.info("getIpAddress(HttpServletRequest) - getRemoteAddr - String ip=" + ip);
                }
            }
        } else if (ip.length() > 15) {
            String[] ips = ip.split(StringUtil.STR_COMMA);
            for (int index = 0; index < ips.length; index++) {
                String strIp = (String) ips[index];
                if (!(STR_UNKNOWN.equalsIgnoreCase(strIp))) {
                    ip = strIp;
                    break;
                }
            }
        }
        return ip;
    }
}
