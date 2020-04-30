package com.win.utils;

import com.win.common.CommonResultVO;

/**
 * 
 * @ClassName ResultUtil
 * @Description TODO(封装常用的返回对象)
 * @author  huiziqin
 * @Date 2018年2月7日 下午6:29:54
 * @version 1.0.0
 */
@SuppressWarnings("rawtypes")
public class ResultUtil {

    public static final int SUCCESS = 0;
    public static final int FAIL = -1;
    // 没有权限
    public static final int NO_AUTH = -2;
    // 没有获取到当前登录用户
    public static final int NO_LOGIN = -3;

    public static CommonResultVO success() {
        CommonResultVO vo = new CommonResultVO();
        vo.setCode(SUCCESS);
        vo.setMessage("成功");
        return vo;
    }

    public static CommonResultVO success(String message) {
        CommonResultVO vo = new CommonResultVO();
        vo.setCode(SUCCESS);
        vo.setMessage(message);
        return vo;
    }

    @SuppressWarnings("unchecked")
    public static CommonResultVO success(String message, Object data) {
        CommonResultVO vo = success(message);
        vo.setData(data);
        return vo;
    }

    public static CommonResultVO fail(String message) {
        CommonResultVO vo = new CommonResultVO();
        vo.setCode(FAIL);
        vo.setMessage(message);
        return vo;
    }

    public static CommonResultVO noAuth() {
        CommonResultVO vo = new CommonResultVO();
        vo.setCode(NO_AUTH);
        vo.setMessage("没有权限访问");
        return vo;
    }

    public static CommonResultVO noLogin() {
        CommonResultVO vo = new CommonResultVO();
        vo.setCode(NO_LOGIN);
        vo.setMessage("获取不到当前登录用户");
        return vo;
    }
}
