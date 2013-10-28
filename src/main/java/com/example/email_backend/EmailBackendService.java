package com.example.email_backend;

import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;

public class EmailBackendService extends Service<EmailBackendConfiguration> {
    public static void main(String[] args) throws Exception {
        new EmailBackendService().run(args);
    }

    @Override
    public void initialize(Bootstrap<EmailBackendConfiguration> bootstrap) {
        bootstrap.setName("email-backend");
    }

    @Override
    public void run(EmailBackendConfiguration configuration,
                    Environment environment) {
    }

}