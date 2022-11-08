package com.sang;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.Configuration;
import io.dropwizard.client.JerseyClientConfiguration;

public class GrabJokesConfiguration extends Configuration {
		@Valid
	    @NotNull
	    private JerseyClientConfiguration jerseyClient = new JerseyClientConfiguration();

		@NotEmpty
	    private String norrisBaseUrl;
		
		@JsonProperty("jerseyClient")
	    public JerseyClientConfiguration getJerseyClientConfiguration() {
	        return jerseyClient;
	    }

	    @JsonProperty("jerseyClient")
	    public void setJerseyClientConfiguration(JerseyClientConfiguration jerseyClient) {
	        this.jerseyClient = jerseyClient;
	    }
	    
	    @JsonProperty("norrisBaseUrl")
	    public String getNorrisBaseUrl() {
			return norrisBaseUrl;
		}

	    @JsonProperty("norrisBaseUrl")
		public void setNorrisBaseUrl(String norrisBaseUrl) {
			this.norrisBaseUrl = norrisBaseUrl;
		}
}
