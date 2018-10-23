package org.superbiz.struts;

import com.opensymphony.module.sitemesh.filter.PageFilter;
import com.opensymphony.sitemesh.webapp.SiteMeshFilter;
import org.apache.struts2.dispatcher.ActionContextCleanUp;
import org.apache.struts2.dispatcher.FilterDispatcher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.support.ErrorPageFilter;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class Application {

    public static void main(String... args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public FilterRegistrationBean struts2RegistrationBean() {
        Map<String, String> initParam = new HashMap<String, String>();
        initParam.put("actionPackages","com.lq");
        FilterRegistrationBean result = new FilterRegistrationBean(new FilterDispatcher());
        result.setInitParameters(initParam);
        result.setName("struts2");
        result.addUrlPatterns("/*");
        result.setOrder(1);
        return result;
    }


    @Bean
    public FilterRegistrationBean strutsCleanUpRegistrationBean() {
        FilterRegistrationBean result = new FilterRegistrationBean(new ActionContextCleanUp());
        result.setName("struts-cleanup");
        result.addUrlPatterns("/*");
        result.setOrder(2);
        return result;
    }

    @Bean
    public FilterRegistrationBean sitemeshRegistrationBean() {
        FilterRegistrationBean result = new FilterRegistrationBean(new PageFilter());
        result.setName("sitemesh");
        result.addUrlPatterns("/*");
        result.setOrder(3);
        return result;
    }


}
