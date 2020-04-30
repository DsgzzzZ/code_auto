package com.win;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName MyApplication
 * @Description TODO(启动类)
 * @author huiziqin
 * @Date 2018年2月7日 下午5:56:25
 * @version 1.0.0
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class })
@Configuration
public class CodeAuto extends SpringBootServletInitializer implements WebMvcConfigurer {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(CodeAuto.class, args);
    }

}
