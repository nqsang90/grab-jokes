package com.sang;

import javax.ws.rs.client.Client;

import com.sang.client.chucknorris.NorrisClient;
import com.sang.resources.GrabJokesResource;

import io.dropwizard.Application;
import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class GrabJokesApplication extends Application<GrabJokesConfiguration> {

    public static void main(final String[] args) throws Exception {
        new GrabJokesApplication().run(args);
    }

    @Override
    public String getName() {
        return "GrabJokes";
    }

    @Override
    public void initialize(final Bootstrap<GrabJokesConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final GrabJokesConfiguration configuration,
                    final Environment environment) {
    	final Client client = new JerseyClientBuilder(environment).using(configuration.getJerseyClientConfiguration())
                .build(getName());
    	final NorrisClient norrisClient = new NorrisClient(client, configuration.getNorrisBaseUrl());
    	environment.jersey().register(new GrabJokesResource(norrisClient));
    }
}
