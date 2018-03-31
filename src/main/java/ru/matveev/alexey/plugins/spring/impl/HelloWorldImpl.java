package ru.matveev.alexey.plugins.spring.impl;

import com.atlassian.jira.config.ConstantsManager;
import com.atlassian.jira.security.JiraAuthenticationContext;
import com.atlassian.sal.api.ApplicationProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.matveev.alexey.plugins.spring.api.HelloWorld;

public class HelloWorldImpl implements HelloWorld {
    private static final Logger LOG = LoggerFactory.getLogger(HelloWorldImpl.class);
    private String message = "Hello World!!!";
    private final ApplicationProperties applicationProperties;
    private final ConstantsManager constantsManager;
    private final JiraAuthenticationContext jiraAuthenticationContext;

    public HelloWorldImpl(ApplicationProperties applicationProperties, JiraAuthenticationContext jiraAuthenticationContext, ConstantsManager constantsManager) {
        this.applicationProperties = applicationProperties;
        this.constantsManager = constantsManager;
        this.jiraAuthenticationContext = jiraAuthenticationContext;
    }

    public String getMessage() {
        LOG.debug("getMessage executed");
        return applicationProperties.getDisplayName() + " logged user: " + jiraAuthenticationContext.getLoggedInUser().getName() + " default priority: " + constantsManager.getDefaultPriority().getName() + " " + this.message;
    }

    public void setMessage(String value) {
        LOG.debug("setMessage executed");
        message = value;
    }
}
