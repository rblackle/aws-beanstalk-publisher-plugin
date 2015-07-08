package org.jenkinsci.plugins.awsbeanstalkpublisher.extensions.envCreate;

import hudson.model.AbstractBuild;

import java.util.List;

import org.eclipse.aether.util.StringUtils;
import org.jenkinsci.plugins.awsbeanstalkpublisher.AWSEBUtils;
import org.kohsuke.stapler.DataBoundConstructor;

import com.amazonaws.services.elasticbeanstalk.model.ConfigurationOptionSetting;
import com.amazonaws.services.elasticbeanstalk.model.CreateEnvironmentRequest;

public class AWSEBCreateEnvironment {
    
    private final List<ConfigurationOptionSetting> optionSettings;

    private final String environmentName;
    private final String applicationName;
    private final String cNAMEPrefix;
    private final String description;
    private final String solutionStackName;
    
    @DataBoundConstructor
    public AWSEBCreateEnvironment(
            String environmentName,
            String applicationName,
            String solutionStackName,
            String cNAMEPrefix,
            String description,
            List<ConfigurationOptionSetting> optionSettings) {
        this.environmentName = environmentName;
        this.applicationName = applicationName;
        this.cNAMEPrefix = cNAMEPrefix;
        this.description = description;
        this.solutionStackName = solutionStackName;
        this.optionSettings = optionSettings;
    }
    
    public void createEnv(AbstractBuild<?, ?> build) {
        CreateEnvironmentRequest createRequest = new CreateEnvironmentRequest();
        
        createRequest.withEnvironmentName(AWSEBUtils.replaceMacros(build, environmentName))
            .withApplicationName(AWSEBUtils.replaceMacros(build, applicationName))
            .withCNAMEPrefix(AWSEBUtils.replaceMacros(build, cNAMEPrefix))
            .withDescription(AWSEBUtils.replaceMacros(build, description))
            .withSolutionStackName(solutionStackName)
            .withOptionSettings(optionSettings);
    }

}
