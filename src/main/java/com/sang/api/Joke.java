package com.sang.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Joke {
	public String id;
	public String value;
	@JsonProperty("icon_url")
	public String iconUrl;
}
