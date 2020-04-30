package com.win.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;


/**
 * 
 * @ClassName WebLoggerInterceptor
 * @Description TODO(请求日志信息)
 * @author  huiziqin
 * @Date 2018年2月7日 下午6:31:53
 * @version 1.0.0
 */
public class WebLoggerInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(WebLoggerInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o)
            throws Exception {
        log("request url: " + httpServletRequest.getRequestURI());
        for (Map.Entry<String, String[]> param : httpServletRequest.getParameterMap().entrySet()) {
            log(param.getKey() + ": " + "," + param.getValue());
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o,
            ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
            Object o, Exception e) throws Exception {

    }

    private void log(String s) {
        logger.debug(s);
    }
}
