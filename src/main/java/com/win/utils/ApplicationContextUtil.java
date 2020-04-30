package com.win.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @ClassName ApplicationContextUtil
 * @Description TODO(全局上下文工具类)
 * @author huiziqin
 * @Date 2018年5月23日 上午11:24:10
 * @version 1.0.0
 */
@Component
public class ApplicationContextUtil {

    private static ApplicationContext context;

    @Autowired
    public ApplicationContextUtil(ApplicationContext ac) {
        context = ac;
    }

    public static ApplicationContext getContext() {
        return context;
    }

}
