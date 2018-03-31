package ru.matveev.alexey.plugins.spring.config;


import com.atlassian.jira.config.ConstantsManager;
import com.atlassian.jira.security.JiraAuthenticationContext;
import org.springframework.context.annotation.Scope;
import ru.matveev.alexey.plugins.spring.aop.HijackAroundMethod;
import ru.matveev.alexey.plugins.spring.aop.HijackBeforeMethod;
import com.atlassian.sal.api.ApplicationProperties;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.matveev.alexey.plugins.spring.api.HelloWorld;
import ru.matveev.alexey.plugins.spring.impl.HelloWorldImpl;

import javax.inject.Named;

@Named
@Configuration
public class Config{


    @Bean(name = "helloWorld")
    @Scope("prototype")
    public HelloWorld helloWorld(ApplicationProperties applicationProperties,
                                 JiraAuthenticationContext jiraAuthenticationContext,
                                 ConstantsManager constantsManager) {
        return new HelloWorldImpl(applicationProperties, jiraAuthenticationContext, constantsManager);
    }

    @Bean(name="hijackBeforeMethodBean")
    public HijackBeforeMethod hijackBeforeMethod() {
        return new HijackBeforeMethod();
    }

    @Bean(name="hijackAroundMethodBean")
    public HijackAroundMethod hijackAroudnMethod() {
        return new HijackAroundMethod();
    }

    @Bean (name = "helloWorldBeforeProxy")
    @Scope("prototype")
    public ProxyFactoryBean proxyBeforeFactoryBean(ApplicationProperties applicationProperties,
                                                   JiraAuthenticationContext jiraAuthenticationContext,
                                                   ConstantsManager constantsManager) {
        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        proxyFactoryBean.setTarget(helloWorld(applicationProperties,jiraAuthenticationContext,constantsManager));
        proxyFactoryBean.setProxyTargetClass(true);
        proxyFactoryBean.setInterceptorNames("hijackBeforeMethodBean");
        return proxyFactoryBean;
    }

    @Bean (name = "helloWorldAroundProxy")
    @Scope("prototype")
    public ProxyFactoryBean proxyAroundFactoryBean(ApplicationProperties applicationProperties,
                                                   JiraAuthenticationContext jiraAuthenticationContext,
                                                   ConstantsManager constantsManager) {
        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        proxyFactoryBean.setTarget(helloWorld(applicationProperties,jiraAuthenticationContext,constantsManager));
        proxyFactoryBean.setProxyTargetClass(true);
        proxyFactoryBean.setInterceptorNames("hijackAroundMethodBean");
        return proxyFactoryBean;
    }


}
