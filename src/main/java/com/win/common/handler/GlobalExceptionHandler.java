package com.win.common.handler;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.win.common.CommonResultVO;
import com.win.utils.ResultUtil;

@ControllerAdvice
@SuppressWarnings("rawtypes")
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 没有权限的异常处理
     */
    @ExceptionHandler(value = AuthorizationException.class)
    @ResponseBody
    public CommonResultVO authErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        logger.debug("authErrorHandler once: " + req.getRequestURI());
        return ResultUtil.noAuth();
    }

    /**
     * 输入校验失败
     */
    @ExceptionHandler(value = BindException.class)
    @ResponseBody
    public CommonResultVO validErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        BindException exception = (BindException) e;
        StringBuffer sb = new StringBuffer();
        for (ObjectError error : exception.getBindingResult().getAllErrors()) {
            sb.append(error.getDefaultMessage()).append(",");
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return ResultUtil.fail(sb.toString());
    }

    /**
     * 通用异常处理
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public CommonResultVO defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        logger.error("defaultErrorHandler", e);
        return ResultUtil.fail(e.getMessage());
    }
}
