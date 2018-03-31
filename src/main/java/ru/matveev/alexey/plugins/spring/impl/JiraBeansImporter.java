package ru.matveev.alexey.plugins.spring.impl;

import com.atlassian.jira.config.ConstantsManager;
import com.atlassian.jira.security.JiraAuthenticationContext;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import com.atlassian.sal.api.ApplicationProperties;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class JiraBeansImporter {
@Inject
    public JiraBeansImporter(@ComponentImport ApplicationProperties applicationProperties,
                             @ComponentImport JiraAuthenticationContext jiraAuthenticationContext,
                             @ComponentImport ConstantsManager constantsManager
                           ) {
    }

}
