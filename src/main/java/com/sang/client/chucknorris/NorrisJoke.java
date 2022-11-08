package com.sang.client.chucknorris;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NorrisJoke {
	public String id;
	@JsonProperty("icon_url")
	public String iconUrl;
	public String url;
	public String value;
}
